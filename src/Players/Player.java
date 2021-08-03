package Players;

public class Player {
    private final boolean color; //false - white, true - black

    public Player(boolean color){
        this.color = color;
    }

    public boolean getColor(){return color;}
}
