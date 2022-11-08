package Characters;

public class Warriors {
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
}
