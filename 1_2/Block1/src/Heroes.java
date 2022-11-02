import java.util.Random;

public class Heroes
{
    public static class warrior extends baseChar
    {
        public warrior() {
            super("Warrior",20, 10, 0);
        }

        @Override
        public void ability(baseChar opponent, baseChar[] ally_team, baseChar[] enemy_team) {
            System.out.printf("Heavy attack(+10 damage -2 speed): ");
            setDmg(getDmg() + 10);
            setSpeed(getSpeed() - 2);
            basicAttack(opponent);
            setDmg(getDmg() - 10);
            setSpeed(getSpeed() + 2);
        }
    }
    public static class mage extends baseChar
    {
        public mage() {
            super("Mage",-20, 30, 1);
        }
        @Override
        public void ability(baseChar opponent, baseChar[] ally_team, baseChar[] enemy_team) {
            System.out.println("Blast(each member of enemy gets damaged): ");
            int dmg_flag = getDmg();
            setDmg(20);
            for (int i = 0; i < enemy_team.length; i++) {
                basicAttack(enemy_team[i]);
            }
            setDmg(dmg_flag);
        }
    }
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
    public static class berserker extends baseChar
    {
        public berserker() {
            super("Berserker", -30 , +30, 0);
        }
        @Override
        public void ability(baseChar opponent, baseChar[] ally_team, baseChar[] enemy_team) {
            System.out.println("Lose 10 hp but gain +10 dmg");
            setDmg(getDmg() + 10);
            basicAttack(opponent);
            if(setHealth(getHealth() - 10) <= 0)
            {
                System.out.println("Berserker killed himself.");
                setAlive(false);
            }
        }
    }
    public static class vampire extends baseChar
    {
        public vampire() {
            super("Vampire", -20 , +5, 2);
        }
        @Override
        public void ability(baseChar opponent, baseChar[] ally_team, baseChar[] enemy_team) {
            System.out.println("Bloodlust(Heal the dmg dealt but -2 speed");
            if(basicAttack(opponent))
                setHealth(getHealth() + getDmg());
        }
    }
    public static class assassin extends baseChar
    {
        public assassin() {
            super("Assassin", 0 , 10, 3);
        }
        @Override
        public void ability(baseChar opponent, baseChar[] ally_team, baseChar[] enemy_team) {
            System.out.println("Attack twice but -3 speed");
            int speed_flag = getSpeed();
            setSpeed(getSpeed() - 3);
            basicAttack(opponent);
            basicAttack(opponent);
            setSpeed(speed_flag);
        }
    }
    public static class cleric extends baseChar
    {
        public cleric() {
            super("Cleric", 0 , 5, 2);
        }
        @Override
        public void ability(baseChar opponent, baseChar[] ally_team, baseChar[] enemy_team) {
            System.out.println("Heal your team by 15 hp");
            for (int i = 0; i < ally_team.length; i++) {
                ally_team[i].setHealth(ally_team[i].getHealth() + 15);
            }
        }
    }
    public static class monk extends baseChar
    {
        public monk() {
            super("Monk", 0 , 0, 0);
        }
        @Override
        public void ability(baseChar opponent, baseChar[] ally_team, baseChar[] enemy_team) {
            System.out.println("Study: Monk has gained +10 dmg");
            setDmg(getDmg() + 10);
        }
    }
    public static class warlock extends baseChar
    {
        public warlock() {
            super("Warlock", 0 , 0, 0);
        }
        @Override
        public void ability(baseChar opponent, baseChar[] ally_team, baseChar[] enemy_team) {
            System.out.println("Kill 'em all: deal 20dmg to your team, and 40 dmg enemy team\n(every attack has hit chance)");
            int dmg_flag = getDmg();
            setDmg(40);
            for (int i = 0; i < enemy_team.length; i++)
                basicAttack(enemy_team[i]);
            setDmg(20);
            for (int i = 0; i < ally_team.length; i++)
                basicAttack(ally_team[i]);
            setDmg(dmg_flag);
        }
    }
    public static class tank extends baseChar
    {
        public tank() {
            super("Tank", 50 , 20, -4);
        }
        @Override
        public void ability(baseChar opponent, baseChar[] ally_team, baseChar[] enemy_team) {
            System.out.println("Lose weight: Tank has exchanged 20hp for +2 speed.");
            setHealth(getHealth() - 20);
            setSpeed(getSpeed() + 2);
            basicAttack(opponent);
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
    public static class necromancer extends baseChar
    {
        public necromancer() {
            super("Necromancer", 10 , 20, 0);
        }
        @Override
        public void ability(baseChar opponent, baseChar[] ally_team, baseChar[] enemy_team) {
            System.out.println("To kill a corpse: if a corpse is killed, attack all enemies");
            if (!opponent.isAlive()) {
                int dmg_flag = getDmg();
                setDmg(getDmg()+10);
                for(baseChar c: enemy_team)
                {
                    if(c.isAlive())
                        basicAttack(c);
                }
                setDmg(dmg_flag);
            }
            else basicAttack(opponent);
        }
    }
    public static class pyromancer extends baseChar
    {
        public pyromancer() {
            super("Pyromancer", -10 , 10, 1);
        }
        @Override
        public void ability(baseChar opponent, baseChar[] ally_team, baseChar[] enemy_team) {
            System.out.println("Burn self majestically: Deal everyone 40 dmg");
            for(baseChar c1: ally_team)
                basicAttack(c1);
            for(baseChar c2: enemy_team)
                basicAttack(c2);
            setHealth(0);
            setAlive(false);
            System.out.println("Pyromancer has died.");
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
    public static class witch extends baseChar
    {
        public witch() {
            super("Witch", -30 , -10, 1);
        }
        @Override
        public void ability(baseChar opponent, baseChar[] ally_team, baseChar[] enemy_team) {
            System.out.println("Sacrifacing..");
            for(baseChar c: ally_team)
            {
                if(c.isAlive())
                {
                    c.setHealth(0);
                    c.setAlive(false);
                    setDmg(getDmg() + 10);
                    setHealth(getHealth() + 10);
                    break;
                }
            }
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
    public static class base extends baseChar
    {
        public base() {
            super("Base", 0 , 0, 0);
        }
        @Override
        public void ability(baseChar opponent, baseChar[] ally_team, baseChar[] enemy_team) {
            basicAttack(opponent);
        }
    }
}
