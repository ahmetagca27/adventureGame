public class Obstacle {
    private int id;
    public String name;
    private int damage;
    private int health;
    private int awardCoin;
    private String award;
    private int orjinalHealth;

    public Obstacle(int id, String name, int damage, int health , int awardCoin , String award ) {
        this.id = id;
        this.name = name;
        this.damage = damage;
        this.health = health;
        this.orjinalHealth =health ;
        this.awardCoin = awardCoin;
        this.award = award;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if (health < 0){
            health = 0;
        }
        this.health = health;
    }

    public int getOrjinalHealth() {
        return orjinalHealth;
    }

    public void setOrjinalHealth(int orjinalHealth) {
        this.orjinalHealth = orjinalHealth;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getAwardCoin() {
        return awardCoin;
    }

    public void setAwardCoin(int awardCoin) {
        this.awardCoin = awardCoin;
    }
}
