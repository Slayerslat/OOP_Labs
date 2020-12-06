package Lab_4;

public class Weapon extends Interactable
{
    public Weapon(String name, int damage, int health)
    {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.damage = damage;
    }
    public String toString()
    {
        return name + ". Атака " + damage + ". Прочность " + health + ".";
    }
    public Weapon clone()
    {
        return new Weapon(name, damage, health);
    }
}
