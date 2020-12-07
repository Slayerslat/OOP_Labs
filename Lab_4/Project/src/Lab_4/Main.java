package Lab_4;

import java.util.ArrayList;

public class Main
{
    private static Creature student = new Creature("Вежливый студент", 2, 1, 8);
    private static Creature caredOne = new Creature("Любимец преподавателей", 3, 4, 1, true);
    private static Weapon deadline = new Weapon("Пылающий дедлайн", 4, 3, 2);
    private static Hero debtor = new Hero("Должник", 0, 0, 10);
    private static Hero MinObrRF = new Hero("Мин.Обр. РФ", 0, 0, 20);
    private static ArrayList<Character> characters = new ArrayList<Character>();
    public static ArrayList<Creature> enemySide = new ArrayList<Creature>();
    public static ArrayList<Creature> playerSide = new ArrayList<Creature>();
    private static ArrayList<Card> enemyHand = new ArrayList<Card>();
    private static ArrayList<Card> playerHand = new ArrayList<Card>();
    private static int playerHandSize;
    private static int enemyCreaturesCount;
    private static int playerCreaturesCount;
    private static int allTargetsCount;
    public static Hero enemyHero;
    public static Hero playerHero;
    private static int playerMana;
    private static int playerMaxMana;
    private static int enemyMana;
    private static int enemyMaxMana;

    public static void main(String[] args)
    {
        enemySide.add(student.clone());
        enemySide.add(caredOne.clone());
        enemySide.add(student.clone());
        setHero(debtor);
        playerHand.add(deadline);
        playerHand.add(caredOne.clone());
        playerHand.add(student.clone());
        playerHand.add(caredOne.clone());
        enemyHero = MinObrRF;
        playerMaxMana = 0;
        enemyMaxMana = 0;
        while (playerHero != null && enemyHero != null)
        {
            if (playerMaxMana < 10)
                playerMaxMana++;
            playerMana = playerMaxMana;
            for (Creature creature: playerSide)
                creature.canAttack = true;
            playerHero.canAttack = true;
            int choice = -1;
            System.out.println("Начинается ваш ход. У вас " + playerMana + " кристаллов маны. Выберите действие.");
            System.out.println("1. Атаковать существом или героем.");
            System.out.println("2. Разыграть карту из руки.");
            System.out.println("0. Закончить ход.");
            System.out.print("Ваш выбор: ");
            while (choice != 0)
            {
                choice = ExceptionHelper.readInt();
                switch (choice)
                {
                    case 1:
                        attackOption();
                        if (playerHero == null || enemyHero == null)
                        {
                            choice = 0;
                            break;
                        }
                        System.out.print("У вас " + playerMana + " из " + playerMaxMana + " кристаллов маны. Ваше следующее действие: ");
                        break;
                    case 2:
                        updateCounters();
                        if (playerHandSize > 0)
                        {
                            playCardOption();
                            if (playerHero == null || enemyHero == null)
                            {
                                choice = 0;
                                break;
                            }
                        }
                        System.out.print("У вас " + playerMana + " из " + playerMaxMana + " кристаллов маны. Ваше следующее действие: ");
                        break;
                    case 0:
                        System.out.println("Ваш ход окончен.");
                        break;
                    default:
                        System.out.print("Действие не распознано, повторите выбор: ");
                        break;
                }
            }
        }
    }
    public static void attackOption()
    {
        System.out.println("Ваши персонажи:");
        updateCounters();
        showPlayerCharacters();
        System.out.println("0. Отмена");
        System.out.print("Выберите, кем хотите атаковать: ");
        int choice = ExceptionHelper.readInt();
        while (choice < 0 || choice > playerCreaturesCount + 1)
        {
            System.out.print("Не существует такого персонажа. Повторите ввод: ");
            choice = ExceptionHelper.readInt();
        }
        Character attacker = null;
        if (choice == 1)
            continueAttackOption(playerHero);
        else if (choice > 1)
            continueAttackOption(playerSide.get(choice - 2));
    }
    public static void continueAttackOption(Character attacker)
    {
        if (!attacker.canAttack || attacker.damage <= 0)
        {
            if (attacker != playerHero || !playerHero.canAttack())
            {
                System.out.println("Выбранный персонаж не может атаковать");
                return;
            }
        }
        System.out.println("Персонажи противника:");
        updateCounters();
        showEnemyCharacters();
        System.out.println("0. Отмена");
        System.out.print("Выберите, кого хотите атаковать: ");
        int choice = ExceptionHelper.readInt();
        while (choice < 0 || choice > enemyCreaturesCount + 1)
        {
            System.out.print("Не существует такого персонажа. Повторите ввод: ");
            choice = ExceptionHelper.readInt();
        }
        if (choice == 1)
            attack(attacker, enemyHero);
        else if (choice > 1)
            attack(attacker, enemySide.get(choice - 2));
    }
    public static void playCardOption()
    {
        System.out.println("Ваши карты:");
        showHand();
        System.out.println("0. Отмена");
        System.out.print("Выберите, какую карту хотите разыграть: ");
        int choice = ExceptionHelper.readInt();
        while (choice < 0 || choice > playerHandSize)
        {
            System.out.print("Не существует такой карты. Повторите ввод: ");
            choice = ExceptionHelper.readInt();
        }
        if (choice != 0)
            playCard(playerHand.get(choice - 1));
    }
    public static void attack(Character attacker, Character target)
    {
        attacker.attack(target);
        attacker.canAttack = false;
        if (attacker.health < 1)
        {
            playerSide.remove(attacker);
            enemySide.remove(attacker);
            if (attacker == playerHero)
            {
                playerHero = null;
                System.out.println("Ваш герой повержен.");
            }
            if (attacker == enemyHero)
            {
                enemyHero = null;
                System.out.println("Вы победили.");
            }
        }
        if (target.health < 1)
        {
            playerSide.remove(target);
            enemySide.remove(target);
            if (target == playerHero)
            {
                playerHero = null;
                System.out.println("Ваш герой повержен.");
            }
            if (target == enemyHero)
            {
                enemyHero = null;
                System.out.println("Вы победили.");
            }
        }
    }
    public static void setHero(Hero hero)
    {
        playerHero = hero;
    }
    public static void summonMinion(Creature minion)
    {
        playerSide.add(minion);
    }
    private static void playCard(Card card)
    {
        if (playerMana >= card.manaCost)
        {
            if (card.needsTarget)
            {
                playerMana -= card.manaCost;
                updateCounters();
                System.out.println("Выберите цель.");
                showTargets();
                int pointer = 0;
                System.out.print("Ваша цель: ");
                pointer = ExceptionHelper.readInt();
                while (pointer < 0 || pointer > allTargetsCount)
                {
                    System.out.println("Не существует такой цели.");
                    System.out.print("Ваша цель: ");
                    pointer = ExceptionHelper.readInt();
                }
                if (pointer == 1)
                    card.onPlay(enemyHero);
                else if (pointer == 2)
                    card.onPlay(playerHero);
                else if (pointer < enemyCreaturesCount + 3)
                    card.onPlay(enemySide.get(pointer - 3));
                else
                    card.onPlay(playerSide.get(pointer - enemyCreaturesCount - 3));
            }
            else
            {
                playerMana -= card.manaCost;
                card.onPlay();
            }
            playerHand.remove(card);
        }
        else
            System.out.println("Не хватает маны.");
    }
    private static void showTargets()
    {
        System.out.println("1. Герой противника. " + enemyHero.shortDescription());
        System.out.println("2. Ваш герой. " + playerHero.shortDescription());
        int i = 3;
        for (Creature creature: enemySide)
        {
            System.out.println(i + ". Существо противника. " + creature.shortDescription());
            i++;
        }
        for (Creature creature: playerSide)
        {
            System.out.println(i + ". Ваше существо. " + creature.shortDescription());
            i++;
        }
    }
    private static void showPlayerCharacters()
    {
        if (playerHero.canAttack)
            System.out.println("1. Ваш герой. " + playerHero.shortDescription());
        else
            System.out.println("1. Ваш герой. " + playerHero.shortDescription() + " Не может атаковать на этом ходу.");
        int i = 2;
        for (Creature creature: playerSide)
        {
            if (creature.canAttack)
                System.out.println(i + ". Ваше существо. " + creature.shortDescription());
            else
                System.out.println(i + ". Ваше существо. " + creature.shortDescription() + " Не может атаковать на этом ходу.");
            i++;
        }
    }
    private static void showEnemyCharacters()
    {
        System.out.println("1. Герой противника. " + enemyHero.shortDescription());
        int i = 2;
        for (Creature creature: enemySide)
        {
            System.out.println(i + ". Существо противника. " + creature.shortDescription());
            i++;
        }
    }
    private static void showHand()
    {
        int i = 1;
        for (Card card: playerHand)
        {
            System.out.println(i + ". " + card.toString());
            i++;
        }
    }
    private static void updateCounters()
    {
        enemyCreaturesCount = 0;
        for (Creature creature: enemySide)
            enemyCreaturesCount++;
        playerCreaturesCount = 0;
        for (Creature creature: playerSide)
            playerCreaturesCount++;
        allTargetsCount = enemyCreaturesCount + playerCreaturesCount + 2;
        playerHandSize = 0;
        for (Card card: playerHand)
            playerHandSize++;
    }
}
