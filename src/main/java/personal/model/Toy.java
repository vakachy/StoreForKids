package personal.model;

public class Toy extends Merchandise {
    protected double weight;

    public Toy(String name, double weight) {
        super(name);
        this.weight = weight;
    }

    public int getID() {
        return super.merch_id;
    }

    public String getName() {
        return this.name;
    }

    public double getWeight() {
        return this.weight;
    }

    @Override
    public void getInfo() {
        System.out.println("Merch_id= " + merch_id + " Name= " + name + " Quantity= " + quantity + " Weight= " + weight);
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
