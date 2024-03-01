package theatricalplays;

import theatricalplays.Play.Type;

public class Performance {

    public String playID;
    public int audience;

    public Performance(String playID, int audience) {
        this.playID = playID;
        this.audience = audience;
    }

    public double volumeCredits(Type playType) {
        return Math.max(audience - 30, 0) + playType.extraCredits(audience);
    }
}
