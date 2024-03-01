package theatricalplays;

import theatricalplays.Play.Type;

public class Performance {

    public String playID;
    public int audience;

    public Performance(String playID, int audience) {
        this.playID = playID;
        this.audience = audience;
    }

    public int amount(Type playType) {
        return playType.amount(audience);
    }

    public double volumeCredits(Type playType) {
        return Math.max(audience - 30, 0) + playType.extraCredits(audience);
    }

}
