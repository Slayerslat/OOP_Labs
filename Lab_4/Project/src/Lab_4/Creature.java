package Lab_4;

public class Creature extends Character
{
    public Creature(String name, int damage, int health)
    {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.damage = damage;
    }
    public Creature(String name, int damage, int health, boolean shielded)
    {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.damage = damage;
        divineShield = shielded;
    }
    public boolean divineShield;
    public void takeDamage(int value)
    {
        if (divineShield)
            divineShield = false;
        else
            super.takeDamage(value);
    }
    public String toString()
    {
        if (divineShield)
            return name + ". Атака " + damage + ". Здоровье " + health + ". Имеет божественный щит.";
        else
            return name + ". Атака " + damage + ". Здоровье " + health + ".";
    }
    public Creature clone()
    {
        return new Creature(name, damage, health, divineShield);
    }
    public String shortDescription()
    {
        if (health > 0)
        {
            if (divineShield)
                return "(" + name + " (" + damage + "/" + health + ") Sh)";
            else
                return "(" + name + " (" + damage + "/" + health + "))";
        }
        else
            return "(" + name + " (отчислен))";
    }
}
