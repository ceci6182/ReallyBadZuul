public class Item {

    private String name;
    private String description;
    private double weight;
    private boolean canBePickedUp;
    private boolean canBeEaten = false;


    public Item(String name, double weight, boolean canBePickedUp) {
        this.name = name;
        this.weight = weight;
        this.canBePickedUp = canBePickedUp;
    }

    public Item() {

    }

    public boolean canBeEaten() {
        return canBeEaten;
    }

    public void setCanBeEaten(boolean canBeEaten) {
        this.canBeEaten = canBeEaten;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setCanBePickedUp(boolean canBePickedUp) {
        this.canBePickedUp = canBePickedUp;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String itemDescription() {
        String printLine = name + "\n" ;
        if(description != null){
            printLine+= "description: " + description + "\n";
        }
        printLine += "weight: " + weight + " kg";
        return printLine;
    }

    public double getWeight() {
        return weight;
    }

    public boolean canBePickedUp() {
        return canBePickedUp;
    }
}
