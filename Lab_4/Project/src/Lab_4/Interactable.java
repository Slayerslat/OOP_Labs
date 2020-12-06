package Lab_4;

public abstract class Interactable extends Card
{
    public int health;
    public int maxHealth;
    public int damage;
    public String shortDescription()
    {
        if (health > 0)
            return "(" + name + " (" + damage + "/" + health + "))";
        else
            return "(" + name + " (отчислен))";
    }
    public void setHealth(int value)
    {
        if (value <= 0)
            die();
        else
            health = value;
    }
    public void setMaxHealth(int value)
    {
        if (value <= 0)
            die();
        else
        {
            maxHealth = value;
            if (health > maxHealth)
                health = maxHealth;
        }
    }
    public void setDamage(int value)
    {
        if (value > 0)
            damage = value;
        else
            damage = 0;
    }
    public void takeDamage(int value)
    {
        health -= value;
        if (health <= 0)
            die();
    }
    public void die()
    {
    }
}
