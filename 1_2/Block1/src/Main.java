/*
Implement the core of some game. An example would be a card rpg game: a hierarchy of
characters and/or abilities based on class inheritance; grouping characters into a team;
team interaction; simple AI. A graphical interface is not required. Requirements: Hierarchy
of at least 20 classes, with its description in the form of a UML diagram. Separation of
the project by files. Separate classes for play (with the choice of playing against human,
against AI or AI against AI) and players.
 */
import java.util.Random;
import java.util.Scanner;

//TODO: Wrong input handling.
/*
Couldn't test all the chars but:
Known Bugs:
-Zombie doesn't turn people into zombies.
 */
public class Main {
    private static final String list_of_all_chars = "1.Warrior 2.Mage 3.Archer 4.Berserker 5.Vampire\n" +
            "6.Assassin 7.Cleric 8.Monk 9.Warlock 10.Tank\n" +
            "11.Kamikaze 12.Necromancer 13.Pyromancer 14.Lepricon 15.Zombie\n" +
            "16.Puppeteer 17.Witch 18.Priest 19.Evil Priest 20.Basic";
    public static void main(String[] args)
    {
        menu();
    }
    public static void menu()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Menu:\n1.PvP\n2.PvE\n3.AIvAI\n4.Exit");
        int selection = sc.nextInt();

        //TODO: Move prints to their own functions
        switch (selection) {
            case 1 -> {
                System.out.println("Starting Player versus Player!");
                pvp();
            }
            case 2 -> {
                System.out.println("Starting Player versus Environment!");
                pve();
            }
            case 3->
            {
                System.out.println("Starting AI vs AI");
                aivai();
            }
            case 4 -> {
                System.out.println("Exiting the game!");
                System.exit(0);
            }
            default -> System.out.println("Enter one of the options: 1-2-3-4");
        }
    }
    public static void play(boolean rand1, boolean rand2)
    {
        Scanner sr = new Scanner(System.in);
        System.out.println("Enter number of chars in a team:");
        int n = sr.nextInt();
        //TODO: Arbitrary number of teams.
        baseChar[] team1 = teamBuilder(n);
        baseChar[] team2 = teamBuilder(n);
        int turns = 1;
        while(isTeamAlive(team1) && isTeamAlive(team2)) {
            turns++;
            if(turns % 2 == 0)
            {
                System.out.println("Turn " + turns / 2);
                System.out.println("Team1's turn");
                at_op(team1,team2,rand1);
            }
            else {
                System.out.println("Team2's turn");
                at_op(team2,team1,rand2);
                System.out.println();
                System.out.println();
            }
        }
        if (isTeamAlive(team1))
            System.out.println("Team1 has won!");
        else
            System.out.println("Team2 has won!");
    }
    public static boolean isTeamAlive(baseChar[] team){
        for (baseChar baseChar : team) {
            if (baseChar.isAlive()) {
                return true;
            }
        }
        return false;
    }
    public static void at_op(baseChar[] attacker, baseChar[] opponent, boolean isRandom)
    {
        int select_attack = 1, select_attacker = 1, select_opponent = 1;
        Scanner sr = new Scanner(System.in);
        if (!isRandom) {
            listAlive(attacker);
            System.out.println("Select attacker: ");
            select_attacker = sr.nextInt() - 1;
            listAlive(opponent);
            System.out.println("Select opponent: ");
            select_opponent = sr.nextInt() - 1;
            System.out.println("Select attack: \n1.(Basic attack) 2.(Ability)");
            select_attack = sr.nextInt();
        }
        if(isRandom)
        {
            Random ran = new Random();
            select_attacker = ran.nextInt(attacker.length);
            select_opponent = ran.nextInt(opponent.length);
            select_attack = ran.nextInt(2) + 1;

        }
        //attack
        if (select_attack == 1)
            attacker[select_attacker].basicAttack(opponent[select_opponent]);
        else if (select_attack == 2)
            attacker[select_attacker].ability(opponent[select_opponent], attacker, opponent);
        else
            System.out.println("Input 1 or 2");

    }
    public static baseChar addChar(int selection)
    {
        baseChar newChar = null;
        switch (selection) {
            //Warrior
            case 1 -> newChar = new Heroes.warrior();
            case 2 -> newChar = new Heroes.mage();
            case 3 -> newChar = new Heroes.archer();
            case 4 -> newChar = new Heroes.berserker();
            case 5 -> newChar = new Heroes.vampire();
            case 6 -> newChar = new Heroes.assassin();
            case 7 -> newChar = new Heroes.cleric();
            case 8 -> newChar = new Heroes.monk();
            case 9 -> newChar = new Heroes.warlock();
            case 10 -> newChar = new Heroes.tank();
            case 11 -> newChar = new Heroes.kamikaze();
            case 12 -> newChar = new Heroes.necromancer();
            case 13 -> newChar = new Heroes.pyromancer();
            case 14 -> newChar = new Heroes.lepricon();
            case 15 -> newChar = new Heroes.zombie();
            case 16 -> newChar = new Heroes.puppeteer();
            case 17 -> newChar = new Heroes.witch();
            case 18 -> newChar = new Heroes.priest();
            case 19 -> newChar = new Heroes.evilpriest();
            case 20 -> newChar = new Heroes.base();
            default -> System.out.println("Wrong Char input");
        }
        return newChar;
    }
    public static baseChar[] teamBuilder(int n)
    {
        int select;
        baseChar[] ary = new baseChar[n];
        System.out.println(list_of_all_chars);
        System.out.println("Select heroes in order:");
        //TODO: Random team building
        Scanner sr = new Scanner(System.in);

        for (int i = 0; i < n; i++) {
            select = sr.nextInt();
            ary[i] = addChar(select);
        }
        return ary;
    }
    public static void listAlive(baseChar[] team)
    {
        String list = "[ ";
        int i = 1;
        for(baseChar c:team)
        {
            list += i + ".";
            i++;
            if (c.isAlive())
            {
                list += c.getName() + " ";
            }
            else list += "-- ";
        }
        list += "]";

        System.out.println(list);
    }
    public static void pvp(){
        play(false, false);
    }
    public static void pve(){
        play(false, true);
    }
    public static void aivai() {
        play(true, true);
    }
}