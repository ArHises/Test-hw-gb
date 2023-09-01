import Toys.Toy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Storage <T extends Toy>{

    private Map<Long, ToyInfo<T>> toyStorage = new HashMap<>();

    private final LinkedList<T> prizeList;

    private int totalAmount;
    private int drawCounter;

    public Storage() {
        this.toyStorage = new HashMap<>();
        this.prizeList = new LinkedList<>();
        this.totalAmount = 0;
        this.drawCounter = 0;
    }

    public T drawToy() {
        if(totalAmount == 0){
            return null;
        }
        Random rnd = new Random();
        int draw = rnd.nextInt(0, totalAmount);
        this.drawCounter++;
        this.totalAmount--;
        for (Map.Entry<Long, ToyInfo<T>> entry : toyStorage.entrySet()) {
            if(draw > entry.getValue().getAmount()){
                draw -= entry.getValue().getAmount();
            } else {
                toyStorage.get(entry.getKey()).setAmount(toyStorage.get(entry.getKey()).getAmount() - 1);
                if(entry.getValue().getAmount() <= 0) {
                    toyStorage.remove(entry.getKey());
                    System.out.println("less then 0");
                }
                toyStorage.get(entry.getKey()).addCounter();
                toyStorage.get(entry.getKey()).setDropRate((float) entry.getValue().getDrawCounter() / this.drawCounter);

                System.out.println("DRAW: " + entry.getValue().getToy().getName()); // to see in the console what toy we draw
                return entry.getValue().getToy();
            }
        }
        return null;
    }

    public void addPrizeToy(T toy) {
        System.out.println("Prize Toy ADDED: " + toy.getName()); // to see in a console what toy we add
        prizeList.offer(toy);
    }

    public T getPrizeToy(){
        if(!prizeList.isEmpty()){
            T toy = prizeList.poll();
            saveToFile(toy);
            return toy;
        }
        System.err.println("No more prize toys! => " + prizeList.toString());
        return null;
    }

    private void saveToFile(T toy){
        String fileName = "prizeList.txt";

        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file, true);
            writer.write(toy.getName() + toy.getId() + "\n");

            writer.close();
            System.out.println(toy.getName() + " Saved to prize file");

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public void addToys(ToyInfo<T> toyInfo) {
        if(toyStorage.containsKey(toyInfo.getToy().getId())){
            toyStorage.get(toyInfo.getToy().getId())
                    .setAmount(toyStorage.get(toyInfo.getToy().getId())
                            .getAmount() + toyInfo.getAmount());
        } else {
            toyStorage.put(toyInfo.getToy().getId(), toyInfo);
        }
        this.totalAmount += toyInfo.getAmount();
    }

    @Override
    public String toString() {
        for (Map.Entry<Long, ToyInfo<T>> entry : toyStorage.entrySet()) {
            entry.getValue().setDropRate((float) entry.getValue().getDrawCounter() / this.drawCounter);
        }
        return "Storage{" +
                "toyStorage = " + toyStorage.toString() +
                "\n, prizeList = " + prizeList +
                "\n, totalAmout = " + totalAmount +
                "\n, drawCounter = " + drawCounter +
                '}';
    }
}
