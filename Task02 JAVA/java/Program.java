import Toys.Bear;
import Toys.Bunny;
import Toys.Robot;
import Toys.Toy;

public class Program {
    public static void main(String[] args) {
        // Create the store
        Store<Toy> toyStore = new Store<Toy>();

        // Create 3 types of toys
        ToyInfo<Toy> bearBox = new ToyInfo<>(new Bear(), 20);
        ToyInfo<Toy> bunnyBox = new ToyInfo<>(new Bunny(), 10);
        ToyInfo<Toy> robotBox = new ToyInfo<>(new Robot(), 5);

        // Add this toys to the storage
        toyStore.addToys(bearBox);
        toyStore.addToys(bunnyBox);
        toyStore.addToys(robotBox);

        // Write in console about the supplies in our storage
        toyStore.getSSuppliesInfo();

        // Draw 10 toys
        toyStore.drawToy();
        toyStore.drawToy();
        toyStore.drawToy();
        toyStore.drawToy();
        toyStore.drawToy();
        toyStore.drawToy();
        toyStore.drawToy();
        toyStore.drawToy();
        toyStore.drawToy();
        toyStore.drawToy();

        // Get the prize toy (Add them to txt file)
        toyStore.getPrizeToy();
        toyStore.getPrizeToy();
        toyStore.getPrizeToy();
        toyStore.getPrizeToy();
        toyStore.getPrizeToy();

        // Write in console about the supplies in our storage to track the changes
        toyStore.getSSuppliesInfo();

    }
}
