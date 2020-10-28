public class MagicCookie extends Item {

    private String effectDescription = "You just got stronger and are able to carry more!"+ "\n";

    public MagicCookie() {
        super();
        this.setName("cookie");
        this.setWeight(2);
        this.setCanBePickedUp(true);
        this.setDescription("This is a cookie, unlike most. it's larger than you would expect and also it's blue and magic.");
        this.setCanBeEaten(true);
    }

    public void getEffect(Player player){
        player.setInventoryCapacity(player.getInventoryCapacity()+10.0);
    }

    public String getEffectDescription() {
        return effectDescription;
    }
}
