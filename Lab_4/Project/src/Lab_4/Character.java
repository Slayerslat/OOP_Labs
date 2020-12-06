package Lab_4;

public abstract class Character extends Interactable
{
    public void attack(Character target)
    {
        System.out.println(shortDescription() + " -> " + target.shortDescription());
        target.takeDamage(damage);
        takeDamage(target.damage);
        System.out.println(shortDescription() + " -- " + target.shortDescription());
    }
    public void heal(int value)
    {
        health += value;
        if (health > maxHealth)
            health = maxHealth;
    }
}
