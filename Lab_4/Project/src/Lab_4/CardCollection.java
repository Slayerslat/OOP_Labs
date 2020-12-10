package Lab_4;

import java.io.*;
import java.util.ArrayList;

public class CardCollection
{
    public ArrayList<Hero> heroes = new ArrayList<Hero>();
    public ArrayList<Creature> creatures = new ArrayList<Creature>();
    public ArrayList<Weapon> weapons = new ArrayList<Weapon>();
    public ArrayList<Spell> spells = new ArrayList<Spell>();
    public void save()
    {
        try(FileWriter writer = new FileWriter("test.txt", false))
        {
            String text = "Kitty 1 1 1 true/";
            for (Creature creature: creatures)
                text = text + creature.name.replace(" ", "_") + " " + creature.manaCost + " " + creature.damage + " " + creature.maxHealth + " " + creature.divineShield + "/";
            text = text + "Mark:Bone 2 2 2/";
            for (Weapon weapon: weapons)
                text = text + weapon.name.replace(" ", "_") + " " + weapon.manaCost + " " + weapon.damage + " " + weapon.maxHealth + "/";
            writer.write(text);
            writer.flush();
        }
        catch(IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    public static CardCollection load()
    {
        try(FileReader reader = new FileReader("test.txt"))
        {
            CardCollection tempCC = new CardCollection();
            int symbol = reader.read();
            String line = "";
            while(symbol != -1)
            {
                line = line + (char)symbol;
                symbol = reader.read();
            }
            String[] line1 = line.split("Mark:");
            String[] line2 = line1[0].split("/");
            String[] line2x;
            Creature tempCreature;
            for (int i = 0; i < line2.length; i++)
            {
                line2x = line2[i].split(" ");
                tempCreature = new Creature(line2x[0].replace("_", " "), Integer.parseInt(line2x[1]), Integer.parseInt(line2x[2]), Integer.parseInt(line2x[3]), Boolean.parseBoolean(line2x[4]));
                tempCC.creatures.add(tempCreature);
            }
            String[] line3 = line1[1].split("/");
            String[] line3x;
            Weapon tempWeapon;
            for (int i = 0; i < line3.length; i++)
            {
                line3x = line3[i].split(" ");
                tempWeapon = new Weapon(line3x[0].replace("_", " "), Integer.parseInt(line3x[1]), Integer.parseInt(line3x[2]), Integer.parseInt(line3x[3]));
                tempCC.weapons.add(tempWeapon);
            }
            return tempCC;
        }
        catch(IOException ex)
        {
            System.out.println(ex.getMessage());
            return new CardCollection();
        }
    }
}
