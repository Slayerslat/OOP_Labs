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
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("cardsCollection.cc")))
        {
            oos.writeObject(this);
        }
        catch(Exception exception)
        {
            System.out.println(exception.getMessage());
        }
    }
    public static CardCollection load()
    {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("cardsCollection.cc")))
        {
            return (CardCollection)ois.readObject();
        }
        catch (Exception exception)
        {
            return new CardCollection();
        }
    }
}
