package theatricalplays;

public class Play {

    enum Type {
        TRAGEDY("tragedy") {
            @Override
            public int amount(int audience) {
                int base = 40000;
                int audienceOverThirty = Math.max(0, audience - 30);
                int progression = 1000 * audienceOverThirty;
                return base + progression;
            }
        },
        COMEDY("comedy") {
            @Override
            protected double extraCredits(int audience) {
                // add extra credit for every ten comedy attendees
                return Math.floor(audience / 5);
            }

            @Override
            public int amount(int audience) {
                int base = 30000;

                int audienceOverTwenty = Math.max(0, audience - 20);
                int progression = 300 * audience + 500 * audienceOverTwenty;

                int isAudienceOverTwenty = (int) Math.signum(audienceOverTwenty);
                int bonusOverTwenty = 10000 * isAudienceOverTwenty;

                return base + progression + bonusOverTwenty;
            }

        },
        HISTORY("history"), 
        PASTORAL("pastoral");

        private String name;

        Type(String name) {
            this.name = name;
        }

        @SuppressWarnings("unused")
        protected double extraCredits(int audience) {
            return 0;
        }

        @Override
        public String toString() {
            return name;
        }

        public int amount(@SuppressWarnings("unused") int audience) {
            throw new Error("unknown type: " + this);
        }
    }

    public final String name;
    public final Type type;

    public Play(String name, Type type) {
        this.name = name;
        this.type = type;
    }
}
