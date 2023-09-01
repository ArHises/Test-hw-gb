import Toys.Toy;

public class ToyInfo<T extends Toy> {

    private T toy;
    private int amount;
    private int drawCounter;
    private float dropRate;

    public ToyInfo(T toy, int amount) {
        this.toy = toy;
        this.amount = amount;
        this.drawCounter = 0;
        this.dropRate = 0f;
    }

    public T getToy() {
        return toy;
    }

    public int getAmount() {
        return amount;
    }

    public int getDrawCounter() {
        return drawCounter;
    }

    public float getDropRate() {
        return dropRate;
    }

    public void addCounter(){
        this.drawCounter++;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setDropRate(float dropRate) {
        this.dropRate = dropRate;
    }

    @Override
    public String toString() {
        return  toy.getName().toUpperCase() +
                " { toyId = " + toy.getId() +
                ", amount = " + amount +
                ", drawCounter = " + drawCounter +
                ", dropRate = "  + dropRate * 100 + "% " +
                "} \n";
    }
}
