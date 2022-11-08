package Characters;

import java.util.Random;

public class Specials
{
    public static class archer extends baseChar
    {
        public archer() {
            super("Archer",-30, 5, 2);
        }
        @Override
        public void ability(baseChar opponent, baseChar[] ally_team, baseChar[] enemy_team) {
            System.out.println("Critical(Has small chance to instantly but -4 speed): ");
            int dmg_flag = getDmg();
            setSpeed(getSpeed() - 4);
            if(Math.random() < 0.1)
                setDmg(1000);
            basicAttack(opponent);
            //
            setDmg(dmg_flag);
            setSpeed(getSpeed() + 3);
        }
    }
    public static class kamikaze extends baseChar
    {
        public kamikaze() {
            super("Kamikaze", 0 , 10, 5);
        }
        @Override
        public void ability(baseChar opponent, baseChar[] ally_team, baseChar[] enemy_team) {
            System.out.println("Kamikaze: Kills himself and an enemy(may fail)");
            setDmg(100);
            basicAttack(opponent);
            setHealth(0);
            setAlive(false);
            System.out.println("Kamikaze has died.");
        }
    }
    public static class lepricon extends baseChar
    {
        public lepricon() {
            super("Lepricon", -20 , -10, 4);
        }
        @Override
        public void ability(baseChar opponent, baseChar[] ally_team, baseChar[] enemy_team) {
            System.out.println("Lucky combo: keep hitting until cannot.");
            if(basicAttack(opponent))
            {
                for (baseChar c: enemy_team)
                {
                    if(c.isAlive())
                    {
                        if(!basicAttack(c)){
                            break;
                        }
                    }
                }
            }
        }
    }
    public static class zombie extends baseChar
    {
        public zombie() {
            super("Zombie", -20 , 20, 0);
        }
        @Override
        public void ability(baseChar opponent, baseChar[] ally_team, baseChar[] enemy_team) {
            System.out.println("Infected: turns opponent into zombie if hit");
            if(basicAttack(opponent))
                opponent = new zombie();
        }
    }
    public static class puppeteer extends baseChar
    {
        public puppeteer() {
            super("Puppeteer", 0 , 5, 2);
        }
        @Override
        public void ability(baseChar opponent, baseChar[] ally_team, baseChar[] enemy_team) {
            System.out.println("Attack with your random teammate.");
            Random r = new Random();
            int a = r.nextInt(ally_team.length);
            ally_team[a].basicAttack(opponent);
            basicAttack(opponent);
        }
    }
    public static class priest extends baseChar
    {
        public priest() {
            super("Priest", 0 , -20, 3);
        }
        @Override
        public void ability(baseChar opponent, baseChar[] ally_team, baseChar[] enemy_team) {
            System.out.println("Heals someone random.");
            Random r = new Random();
            int n = ally_team.length + enemy_team.length;
            int rn = r.nextInt(n);
            int i = r.nextInt(n/2);
            if (rn <= n / 2)
            {
                ally_team[i].setHealth(ally_team[i].getHealth() + 30);
            }
            else {
                enemy_team[i].setHealth(enemy_team[i].getHealth() + 30);
            }
        }
    }
    public static class evilpriest extends baseChar
    {
        public evilpriest() {
            super("Evil Priest", 0 , 20, -2);
        }
        @Override
        public void ability(baseChar opponent, baseChar[] ally_team, baseChar[] enemy_team) {
            System.out.println("Kills someone random.");
            Random r = new Random();
            int n = ally_team.length + enemy_team.length;
            int rn = r.nextInt(n);
            int i = r.nextInt(n/2);
            if (rn <= n / 2)
            {
                ally_team[i].setHealth(0);
                ally_team[i].setAlive(false);
            }
            else {
                enemy_team[i].setHealth(0);
                enemy_team[i].setAlive(false);
            }
        }
    }
}
