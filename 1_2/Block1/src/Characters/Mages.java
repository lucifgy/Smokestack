package Characters;

public class Mages {
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
}
