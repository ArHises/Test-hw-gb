import Toys.Toy;

public class Store<T extends Toy> {

    private final Storage<T> storage;

    public Store() {
        this.storage = new Storage<T>();
    }

    public void addToys(ToyInfo<T> toyInfo){
        storage.addToys(toyInfo);
    }

    public void drawToy(){
        T toy = storage.drawToy(); // May return null if the "toyStorage" Map is empty
        if(toy == null){
            System.err.println("The storage is empty :(");
        } else {
            storage.addPrizeToy(toy);
        }
    }

    public T getPrizeToy(){
        return storage.getPrizeToy();
    }

    public void getSSuppliesInfo() {
        System.out.println(this.storage);
    }
}
