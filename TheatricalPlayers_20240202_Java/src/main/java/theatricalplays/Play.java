package theatricalplays;

public class Play {

    enum Type {
        TRAGEDY("tragedy") {
            @Override
            public int amount(int audience) {
                var thisAmount = 0;
                thisAmount = 40000;
                if (audience > 30) {
                    thisAmount += 1000 * (audience - 30);
                }
                return thisAmount;
            }
        },
        COMEDY("comedy") {
            @Override
            protected double extraCredits(int audience) {
                return Math.floor(audience / 5);
            }
            
            @Override
            public int amount(int audience) {
                var thisAmount = 0;
                thisAmount = 30000;
                if (audience > 20) {
                    thisAmount += 10000 + 500 * (audience - 20);
                }
                thisAmount += 300 * audience;
                return thisAmount;
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
