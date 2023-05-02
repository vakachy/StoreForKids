package personal.model;

public class Merchandise {
    protected static int id;
    protected int merch_id;
    String name;
    protected int quantity = 1;

    static {
        Merchandise.id = 9999;
    }

    public Merchandise(String name) {
        this.merch_id = ++Merchandise.id;
        this.name = name;
    }

    public Merchandise() {
        this("New Merch");
    }

    public void getInfo() {
        System.out.println("Merch_id=" + merch_id + " " + "Name " + name + " " + "Quantity " + quantity);
    }
}
