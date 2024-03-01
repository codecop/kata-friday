package theatricalplays;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class StatementPrinter {

    public String print(Invoice invoice, Map<String, Play> plays) {
        List<Performance> performances = invoice.performances;

        String customer = invoice.customer;
        List<StatementLine> lines = statementLinesFor(performances, plays);
        var totalAmount = totalAmountFor(performances, plays);
        var volumeCredits = volumeCreditsFor(performances, plays);
        
        var result = String.format("Statement for %s\n", customer);
        for (var line : lines) {
            result += format(line);
        }
        result += String.format("Amount owed is %s\n", frmt.format(totalAmount / 100));
        result += String.format("You earned %s credits\n", volumeCredits);
        return result;
    }

    private List<StatementLine> statementLinesFor(List<Performance> performances, Map<String, Play> plays) {
        return performances.stream(). //
                map(perf -> statementLineFor(perf, plays)). //
                collect(Collectors.toList());
    }

    private StatementLine statementLineFor(Performance perf, Map<String, Play> plays) {
        var play = plays.get(perf.playID);
        return perf.line(play);
    }

    private int totalAmountFor(List<Performance> performances, Map<String, Play> plays) {
        return performances.stream(). //
                mapToInt(perf -> amountFor(perf, plays)). //
                sum();
    }

    private int amountFor(Performance perf, Map<String, Play> plays) {
        var play = plays.get(perf.playID);
        return perf.amount(play.type);
    }

    private int volumeCreditsFor(List<Performance> performances, Map<String, Play> plays) {
        return (int) performances.stream(). //
                mapToDouble(perf -> volumeCreditsFor(perf, plays)). //
                sum();
    }

    private double volumeCreditsFor(Performance perf, Map<String, Play> plays) {
        var play = plays.get(perf.playID);
        return perf.volumeCredits(play.type);
    }

    NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

    private String format(StatementLine line) {
        return String.format("  %s: %s (%s seats)\n", //
                line.name, //
                frmt.format(line.amount / 100), //
                line.audience);
    }

}
