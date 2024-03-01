package theatricalplays;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class StatementPrinter {

    public String print(Invoice invoice, Map<String, Play> plays) {
        var totalAmount = 0; // Peter 3rd: multiple accumulator variables in single loop (SRP) -> split loop
        var volumeCredits = 0; // Peter 4th: totalAmount+volumeCredits is data clump -> extract class?
        var result = String.format("Statement for %s\n", invoice.customer);

        NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

        for (var perf : invoice.performances) {
            var play = plays.get(perf.playID);
            var thisAmount = extracted(perf, play);

            // add volume credits
            volumeCredits += Math.max(perf.audience - 30, 0);

            // add extra credit for every ten comedy attendees
            volumeCredits += play.type.extraCredits(perf.audience);

            // print line for this order
            result += String.format("  %s: %s (%s seats)\n", play.name, frmt.format(thisAmount / 100), perf.audience);
            totalAmount += thisAmount;
        }
        result += String.format("Amount owed is %s\n", frmt.format(totalAmount / 100));
        result += String.format("You earned %s credits\n", volumeCredits);
        return result;
    }

    private int extracted(Performance perf, Play play) throws Error {

        int audience = perf.audience;
        // Peter 1st: bumpy road -> extract method
        switch (play.type) { // Peter 2nd: switch on type -> polymorphy 
            case TRAGEDY: {
                return Play.Type.TRAGEDY.amount(audience);
            }
            case COMEDY: {
                return Play.Type.COMEDY.amount(audience);
            }
            default:
                throw new Error("unknown type: " + play.type);
        }
    }

}
