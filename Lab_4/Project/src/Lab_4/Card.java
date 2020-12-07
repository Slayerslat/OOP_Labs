package Lab_4;

public abstract class Card
{
    public String name;
    public int manaCost;
    public void onPlay(){}
    public void onPlay(Character target){}
    public boolean needsTarget;
}
