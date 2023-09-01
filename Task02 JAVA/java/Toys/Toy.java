package Toys;

public abstract class Toy {

    private final String name;
    private final long id;
    private static int lastId = 0;

    public Toy(String name) {
        this.id = lastId;
        lastId++;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }


}
