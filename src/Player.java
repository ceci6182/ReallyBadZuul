import java.util.ArrayList;
import java.util.List;

public class Player {
    private Room currentRoom;
    private List<Room> roomHistory;
    private List <Item> inventory;
    private int roomHistoryIndex = 0;
    private double inventoryCapacity = 100;
    private double currentInventory = 0;


    public Player() {
        roomHistory = new ArrayList<>();
        inventory =new ArrayList<>();
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public void goRoom(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            addToHistory();
            setCurrentRoom(nextRoom);
            printLocationInfo();
        }
    }

    public void look()
    {
        System.out.println(currentRoom.getLongDescription());
    }

    public void pickUp(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("pick up what?");
            return;
        }

        String item = command.getSecondWord();

        // Try to pick up
        Item itemToPickUp = currentRoom.getItemFromString(item);

        if (itemToPickUp == null) {
            System.out.println("That item isn't here");
        }
        else if(!itemToPickUp.canBePickedUp()){
            System.out.println("Item can't be picked up");
        }

        else if (currentInventory + itemToPickUp.getWeight() > inventoryCapacity){
                System.out.println("Item is too heavy");
        }

        else {
            inventory.add(itemToPickUp);
            currentRoom.removeItem(itemToPickUp);
            currentInventory += itemToPickUp.getWeight();
            System.out.println("You've picked up a " + itemToPickUp.getName());
            System.out.println("your inventory capacity is now at " + currentInventory +  "/" + inventoryCapacity);
        }
    }

    public Item getItemFromString(String item) {
        if(!inventory.isEmpty()) {
            Item tempItem;
            for (int i = 0; i< inventory.size(); i++) {
                tempItem = inventory.get(i);
                if(tempItem.getName().equals(item)) {
                    return tempItem;
                }
            }
        }
        return null;
    }

    public void dropItem(Command command) {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("drop what?");
            return;
        }

        String item = command.getSecondWord();

        // Try to drop
        Item itemToDrop = getItemFromString(item);

        if (itemToDrop == null) {
            System.out.println("That item isn't here");
        }

        else {
            inventory.remove(itemToDrop);
            currentRoom.addItems(itemToDrop);
            currentInventory -= itemToDrop.getWeight();
            System.out.println("You've dropped a " + itemToDrop.getName());
            System.out.println("your inventory capacity is now at " + currentInventory +  "/" + inventoryCapacity);

        }

    }

    public void eat(Command command) {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("eat what?");
            return;
        }

        String item = command.getSecondWord();

        // Try to eat
        Item itemToEat = getItemFromString(item);

        if (itemToEat == null) {
            System.out.println("That item isn't here");
        }
        else if (!itemToEat.canBeEaten()) {
            System.out.println("This item can't be eaten");
        }
        else {
            if (itemToEat.getClass() == MagicCookie.class){
                ((MagicCookie) itemToEat).getEffect(this);
                System.out.println(((MagicCookie) itemToEat).getEffectDescription());
            }
            inventory.remove(itemToEat);
            currentInventory -= itemToEat.getWeight();
            System.out.println("You've eaten a " + itemToEat.getName());
            System.out.println("your inventory capacity is now at " + currentInventory +  "/" + inventoryCapacity);


        }
    }

    public double getInventoryCapacity() {
        return inventoryCapacity;
    }

    public void setInventoryCapacity(double inventoryCapacity) {
        this.inventoryCapacity = inventoryCapacity;
    }

    public void inventoryToString() {
        String inventoryString = "Your inventory contains:";
        if (inventory.isEmpty()){
           inventoryString =  "Your inventory is empty";
        }
        else{
            for (int i =0; i<inventory.size(); i++) {
                inventoryString += "\n" +inventory.get(i).getName();
            }
        }
        inventoryString += "\n" + "your inventory capacity is now at " + currentInventory +  "/" + inventoryCapacity;
        System.out.println(inventoryString);
    }

    public void printLocationInfo() {
        System.out.println(currentRoom.getLongDescription());
    }

    public void addToHistory(){
        roomHistory.add(currentRoom);
        roomHistoryIndex ++;
    }

    public void back() {
        currentRoom = roomHistory.get(roomHistoryIndex-1);
        roomHistory.remove(roomHistoryIndex-1);
        roomHistoryIndex --;
        printLocationInfo();
    }
}
