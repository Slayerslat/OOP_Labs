package Lab_4;

public class Hero extends Character
{
    private int armor;
    private Weapon equippedWeapon;
    public Hero cloneCard()
    {
        Hero hero = new Hero(name, manaCost, damage, maxHealth, armor);
        if (equippedWeapon != null)
            hero.equipWeapon(equippedWeapon.cloneCard());
        return hero;
    }
    public Hero(String name, int cost, int damage, int health)
    {
        this.name = name;
        this.manaCost = cost;
        this.health = health;
        this.maxHealth = health;
        this.damage = damage;
    }
    public Hero(String name, int cost, int damage, int health, int armor)
    {
        this.name = name;
        this.manaCost = cost;
        this.health = health;
        this.maxHealth = health;
        this.damage = damage;
        this.armor = armor;
    }
    public void takeDamage(int value)
    {
        if (armor > value)
            armor -= value;
        else
        {
            health -= value - armor;
            armor = 0;
        }
    }
    public boolean canAttack()
    {
        if (equippedWeapon != null)
            return canAttack && equippedWeapon.damage > 0;
        else
            return false;
    }
    public void equipWeapon(Weapon weapon)
    {
        equippedWeapon = weapon;
    }
    public void attack(Character target)
    {
        System.out.println(shortDescription() + " -> " + target.shortDescription());
        if (equippedWeapon != null)
        {
            target.takeDamage(damage + equippedWeapon.damage);
            equippedWeapon.takeDamage(1);
            if (equippedWeapon.health < 1)
                equippedWeapon = null;
        }
        else
            target.takeDamage(damage);
        takeDamage(target.damage);
        System.out.println(shortDescription() + " -- " + target.shortDescription());
    }
    public void onPlay()
    {
        Main.setHero(this);
    }
    public String toString()
    {
        if (equippedWeapon == null)
        {
            if (armor < 1)
                return name + ". Стоймость " + manaCost + ". Атака " + damage + ". Здоровье " + health + ".";
            else
                return name + ". Стоймость " + manaCost + ". Атака " + damage + ". Здоровье " + health + ". Броня " + armor + ".";
        }
        else
        {
            if (armor < 1)
                return name + ". Стоймость " + manaCost + ". Атака " + damage + ". Экипировано оружие" + equippedWeapon.shortDescription() + ". Здоровье " + health + ".";
            else
                return name + ". Стоймость " + manaCost + ". Атака " + damage + ". Экипировано оружие" + equippedWeapon.shortDescription() + ". Здоровье " + health + ". Броня " + armor + ".";
        }
    }
    public String shortDescription()
    {
        if (health > 0)
        {
            if (equippedWeapon == null)
            {
                if (armor > 0)
                    return "(" + name + " (" + damage + "/" + health + "+" + armor + "))";
                else
                    return "(" + name + " (" + damage + "/" + health + "))";
            }
            else
            {
                if (armor > 0)
                    return "(" + name + " (" + damage + "/" + health + "+" + armor + ")" + " + W(" + equippedWeapon.damage + "/" + equippedWeapon.health + "))";
                else
                    return "(" + name + " (" + damage + "/" + health + ")" + " + W(" + equippedWeapon.damage + "/" + equippedWeapon.health + "))";
            }
        }
        else
            return "(" + name + " (отчислен))";
    }
}
