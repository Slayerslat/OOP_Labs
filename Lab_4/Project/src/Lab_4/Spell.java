package Lab_4;

public class Spell extends Card
{
    private String description;
    public String toString()
    {
        return name + ". Стоймость " + manaCost + ". " + description;
    }
}
