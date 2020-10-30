/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Player p1;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createPlayer();
        createRooms();
        parser = new Parser();
    }

    private void createPlayer() {
        p1 = new Player();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room Entrance, Cave1, L1;
      
        // create the rooms
        Entrance = new Room("You're standing in the Entrance to the dungeon");
        Cave1 = new Room("You enter a cave with 3 openings");
        L1 = new Room("You enter a dark cave, you hear strange sounds coming from the left");
        
        // initialise room exits
        Entrance.setExit("north", Cave1);
        Cave1.setExit("south", Entrance); Cave1.setExit("left", L1);
        L1.setExit("right", Cave1);

        // add items to rooms;
        L1.addItems(new MagicCookie());

        //Sets the starting Room of the player
        p1.setCurrentRoom(Entrance);
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        p1.printLocationInfo();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        switch (commandWord) {
            case "help" -> printHelp();
            case "go" -> p1.goRoom(command);
            case "look" -> p1.look();
            case "eat" -> p1.eat(command);
            case "back" -> p1.back();
            case "take" -> p1.pickUp(command);
            case "drop" -> p1.dropItem(command);
            case "items" -> p1.inventoryToString();
            case "quit" -> wantToQuit = quit(command);
        }

        return wantToQuit;
    }

    // implementations of user commands:
    /**
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */



    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are have entered a dungeon, good luck!");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
        System.out.println(".\n");
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
