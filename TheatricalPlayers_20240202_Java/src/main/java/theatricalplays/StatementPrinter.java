package theatricalplays;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class StatementPrinter {

    public String print(Invoice invoice, Map<String, Play> plays) {
        List<Performance> performances = invoice.performances;

        var volumeCredits = volumeCreditsFor(performances, plays);
        
        var totalAmount = 0; // Peter 3rd: multiple accumulator variables in single loop (SRP) -> split loop
        for (var perf : performances) {
            var play = plays.get(perf.playID);

            // print line for this order
            var thisAmount = play.type.amount(perf.audience);
            totalAmount += thisAmount;
        }
        
        NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);
        var result = String.format("Statement for %s\n", invoice.customer);
        for (var perf : performances) {
            var play = plays.get(perf.playID);

            // print line for this order
            var thisAmount = play.type.amount(perf.audience);
            result += String.format("  %s: %s (%s seats)\n", play.name, frmt.format(thisAmount / 100), perf.audience);
        }
        result += String.format("Amount owed is %s\n", frmt.format(totalAmount / 100));
        result += String.format("You earned %s credits\n", volumeCredits);
        return result;
    }

    private int volumeCreditsFor(List<Performance> performances, Map<String, Play> plays) {
        var volumeCredits = 0; 
        for (var perf : performances) {
            volumeCredits += volumeCreditisFor(perf, plays);
        }
        return volumeCredits;
    }

    private double volumeCreditisFor(Performance perf, Map<String, Play> plays) {
        var play = plays.get(perf.playID);
        return perf.volumeCredits(play.type);
    }

}
