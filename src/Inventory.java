public class Inventory {
    private Weapon weapon;
    private Armor armor;
    private int awardNumber = 0 ;



    public Inventory() {
        this.weapon = new Weapon("Yumruk", -1, 0, 0);
        this.armor = new Armor(-1,"Zırh Yok !", 0, 0);
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }
    public int onAwardNumber(int a){
        this.awardNumber += a;
        return awardNumber;
    }

    public int getAwardNumber() {
        return awardNumber;
    }

    public void setAwardNumber(int awardNumber) {
        this.awardNumber = awardNumber;
    }
}
