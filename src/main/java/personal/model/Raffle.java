package personal.model;

public class Raffle {
    protected static int id;
    protected int raffle_id;
    protected int toy_id;

    static {
        Raffle.id = 0;
    }

    public Raffle(int toy_id) {
        this.raffle_id = ++Raffle.id;
        this.toy_id = toy_id;
    }
}
