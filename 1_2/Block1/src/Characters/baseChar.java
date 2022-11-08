package Characters;

public class baseChar
{
    private int health = 100;
    private int dmg = 25;
    private int speed = 5;
    private final String name;
    private boolean isAlive = true;
    public baseChar(String name, int health, int dmg, int speed) {
        super();
        this.name = name;
        this.health += health;
        this.dmg += dmg;
        this.speed += speed;
    }
    public boolean basicAttack(baseChar opponent) {
        boolean isHit = false;
        if (opponent.isAlive()) {
            if (isHit(opponent)) {
                isHit = true;
                System.out.println(opponent.getHealth() + "hp " + opponent.getName() + " has been attacked by " + getName() + "!");
                if (opponent.setHealth(opponent.getHealth() - getDmg()) <= 0) {
                    opponent.setAlive(false);
                    System.out.println(opponent.getName() + " is dead.");
                } else
                    System.out.println(opponent.getName() + " has " + opponent.getHealth() + " hp left.");
            }
            else System.out.println("Missed.");
        }
        else System.out.println(getName() + " attacked a corpse, and killed!");
        return isHit;
    }
    public void ability(baseChar opponent, baseChar[] ally_team, baseChar[] enemy_team)
    {
    }
    public boolean isHit(baseChar opponent)
    {
        double chance = (double)getSpeed() / (getSpeed() + opponent.getSpeed());
        double a = Math.random();
        //System.out.println(chance+ "\t" + a);
        return chance > a;
    }

    public int getHealth() {
        return health;
    }
    public int setHealth(int health) {
        this.health = health;
        return health;
    }
    public int getDmg() {
        return dmg;
    }
    public int setDmg(int dmg) {
        this.dmg = dmg;
        return dmg;
    }
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public String getName() {
        return name;
    }
    public boolean isAlive() {
        return isAlive;
    }
    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}