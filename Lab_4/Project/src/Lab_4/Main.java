package Lab_4;

import java.util.ArrayList;

public class Main
{
    public static Creature student = new Creature("Вежливый студент", 1, 8);
    public static Creature caredOne = new Creature("Любимец преподавателей", 4, 1, true);
    public static Weapon deadline = new Weapon("Пылающий дедлайн", 3, 2);
    public static Hero debtor = new Hero("Должник", 0, 10);
    public static ArrayList<Character> Characters = new ArrayList<Character>();
    public static void main(String[] args)
    {
        Characters.add(student.clone());
        Characters.add(caredOne.clone());
        Characters.add(debtor);
        debtor.equipWeapon(deadline.clone());

        System.out.println(Characters.get(0).toString());
        System.out.println(Characters.get(1).toString());
        System.out.println(Characters.get(2).toString());
        System.out.println();

        attack(Characters.get(1), Characters.get(0));
        System.out.println();
        attack(Characters.get(2), Characters.get(1));
        System.out.println();
    }
    public static void attack(Character attacker, Character target)
    {
        attacker.attack(target);
        if (attacker.health < 1)
        {
            Characters.remove(attacker);
        }
        if (target.health < 1)
        {
            Characters.remove(target);
        }
    }
}
