public class Item {


    private String description;
    private double weight;

    public Item(String description, double weight) {
        this.description = description;
        this.weight = weight;
    }

    public String itemDescription() {
        String printLine = description;
        printLine += " which weighs ";
        printLine += weight + " kg";
        return printLine;
    }
}
