package theatricalplays;

public class Play {

    enum Type {
        TRAGEDY("tragedy"), 
        COMEDY("comedy");

        private String name;

        Type(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public String name;
    public String type;
    public final Type type2;

    public Play(String name, String type) {
        this.name = name;
        this.type2 = Type.valueOf(name.toUpperCase());
        this.type = type;
    }

    public Play(String name, Type nameEnum) {
        this.name = name;
        this.type2 = nameEnum;
        this.type = nameEnum.toString();
    }
}
