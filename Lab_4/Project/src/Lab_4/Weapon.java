package Lab_4;

public class Weapon extends Interactable
{
    public Weapon(String name, int cost, int damage, int health)
    {
        this.name = name;
        this.manaCost = cost;
        this.health = health;
        this.maxHealth = health;
        this.damage = damage;
    }
    public String toString()
    {
        return name + ". Стоймость " + manaCost + ". Атака " + damage + ". Прочность " + health + ".";
    }
    public Weapon cloneCard()
    {
        return new Weapon(name, manaCost, damage, health);
    }
    public void onPlay()
    {
        Main.playerHero.equipWeapon(this);
    }
}
