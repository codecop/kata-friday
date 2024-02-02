package theatricalplays;

public class Play {

    enum Type {
        TRAGEDY("tragedy"), 
        COMEDY("comedy") {
            @Override
            protected double extraCredits(int audience) {
                return Math.floor(audience / 5);
            }
        },
        HISTORY("history"),
        PASTORAL("pastoral");

        private String name;

        Type(String name) {
            this.name = name;
        }

        protected double extraCredits(int audience) {
            return 0;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public String name;
    public final Type type2;

    public Play(String name, Type nameEnum) {
        this.name = name;
        this.type2 = nameEnum;
    }
}
