import java.security.PublicKey;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.Random;

public abstract class BattleLoc extends Location{
    Random random = new Random();
    private Obstacle obstacle;
    private String award;
    private int maxObstacle;

    public BattleLoc(Player player, String name, Obstacle obstacle, String award, int maxObstacle) {
        super(player, name);
        this.obstacle = obstacle;
        this.award = award;
        this.maxObstacle = maxObstacle;

    }

    @Override
    public boolean onLocation() {
        int obsNumber = this.randomObstacleNumber();

        System.out.println("Şuan Buradasınız : " + this.getName());
        System.out.println("Dikkatli Ol ! Burada " + obsNumber + " tane  " + this.getObstacle().getName() + " yaşıyor. !");
        System.out.println("<S>avaş veya <K>aç");
        String selectCase = input.nextLine().toUpperCase();

        if(selectCase.equals("S") && combat(obsNumber)){
            System.out.println(this.getName() + "tüm düşmanlardan Temizlendi");
            switch (this.getName()){
                case "Mağara" :
                    Cave.setCheckloc(true);
                    break;
                case "Orman" :
                    Forest.setCheckloc(true);
                    break;
                case "Nehir" :
                    River.setCheckloc(true);
                    break;
                case "Maden":
                    Coal.setCheckloc(true);
                    break;
            }
            if (!this.getObstacle().getName().equals("Yılan")){
                this.getPlayer().getInventory().onAwardNumber(1);
            }
            if (this.getPlayer().getInventory().getAwardNumber() == 3){
                System.out.println();
                System.out.println("Tüm ödülleri topladınız! Oyunu KAZANDINIZ !!!!");
                return false;
            }
            return true;
        }

        if (this.getPlayer().getHealth() <= 0){
            System.out.println("ÖLDÜNÜZ !");
            System.out.println("÷~÷~¨|¨ GAME OVER ¨|¨÷~÷~");
            return false;
        }
        return true;
    }


    public boolean combat (int obsNumber){
        for (int i = 1 ; i <= obsNumber ; i++){

            int firstKick = random.nextInt(2);//ilk vuruş için variable declare edildi ve ilk vuruşu yapacak olan belirlendi
            this.getObstacle().setHealth(this.getObstacle().getOrjinalHealth());
            playerStats();
            obstacleStats(i);
            while (this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0){
                System.out.println("<S>avaş ! | <G>eri Çekil ! ");
                String selectCombat = Location.input.nextLine().toUpperCase();
                snakeDamage(); //Yılan Random hasar verecek
                if (selectCombat.equals("S")){

                    if (firstKick == 0){
                        System.out.println();
                        System.out.println("Canavara vurdunuz !");
                        this.getObstacle().setHealth(this.obstacle.getHealth() - this.getPlayer().getTotalDamage());
                        afterHit();
                        if (this.getObstacle().getHealth() > 0){
                            System.out.println();
                            System.out.println(" Canavar size vurdu !");
                            int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                            if (obstacleDamage < 0) {
                                obstacleDamage = 0;
                            }

                            this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                            afterHit();
                        }
                    }else{
                        System.out.println();
                        System.out.println("Canavar size vurdu !");
                        int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                        if (obstacleDamage < 0) {
                            obstacleDamage = 0;
                        }
                        this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                        afterHit();

                        if (this.getPlayer().getHealth() > 0){
                            System.out.println();
                            System.out.println("Canavara vurdunuz !");
                            this.getObstacle().setHealth(this.obstacle.getHealth() - this.getPlayer().getTotalDamage());
                            afterHit();
                        }
                    }
                }else {
                    return false;
                }
            }
            if (this.getObstacle().getHealth() < this.getPlayer().getHealth()){
                System.out.println("Düşmanı yendiniz !");
                System.out.println("Kahramanımız");
                if (!obstacle.getName().equals("Yılan")){
                    System.out.println(this.getObstacle().getAwardCoin() + " $ para kazandınız !");
                    this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getAwardCoin());
                    System.out.println("Güncel paranız : " + this.getPlayer().getMoney() + "$");
                }else {
                    randomItemSnake();
                }
            }else {
                return false;
            }
        }
        return true;
    }

    public void afterHit(){
        System.out.println("Canınız : " + this.getPlayer().getHealth());
        System.out.println(getObstacle().getName() + "Canı : " + this.getObstacle().getHealth());
        System.out.println("-----------------------");
    }
    public void playerStats(){
        System.out.println("---------------------------------");
        System.out.println("\t \t \t" + this.getPlayer().getName() + " Değerleri");
        System.out.println("---------------------------------");
        System.out.println("Sağlık  : " + this.getPlayer().getHealth());
        System.out.println("Hasar    : " + this.getPlayer().getTotalDamage());
        System.out.println("Silah   : " + this.getPlayer().getWeapon().getName());
        System.out.println("ZIrh    : " + this.getPlayer().getArmor().getName());
        System.out.println("Bloklama  : " + this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Para    : " + this.getPlayer().getMoney() + "$");
    }

    public void obstacleStats(int i){
        System.out.println("-----------------------------------------");
        System.out.println("\t\t\t " + i + ". "+ this.getObstacle().getName() + "Değerleri");
        System.out.println("-----------------------------------------");
        System.out.println("Sağlık :" + this.getObstacle().getHealth());
        System.out.println("Hasar :" + this.getObstacle().getDamage());
        System.out.println("Ödül :" + this.getObstacle().getAward());
    }

    public int randomObstacleNumber(){
        Random r = new Random();
        return r.nextInt(this.getMaxObstacle()) + 1;
    }
    public void randomItemSnake(){    //Yılan için rastgele hasar tanımlama ve nesne tanımlama
        Weapon rewardWeapon = Weapon.getWeaponObjByID(randomItems());
        Armor rewardArmour = Armor.getArmorObjByID(randomItems());
        randomNumber();

        if (randomNumber() >= 0 && randomNumber() <= 14){

            System.out.println(rewardWeapon.getName() + " yere düştü ! Almak istermisiniz ? ");
            System.out.println("<E>vet / <H>ayır  : ");
            String take = Location.input.nextLine().toUpperCase();
            System.out.println();
            if (take.equals("E")){
                this.getPlayer().getInventory().setWeapon(rewardWeapon);
                System.out.println("");
                System.out.println(rewardWeapon.getName() + " kazandınız !");
                System.out.println("");
            }else {
                System.out.println("Yerdeki nesneyi almadın !!!");
            }

        }else if(randomNumber() >= 15 && randomNumber() <= 29){
            System.out.println(rewardArmour.getName() + " yere düştü ! Almak istermisiniz ? ");
            System.out.println("<E>vet / <H>ayır  : ");
            String take = Location.input.nextLine().toUpperCase();
            if (take.equals("E")){
                this.getPlayer().getInventory().setArmor(rewardArmour);
                System.out.println("");
                System.out.println(rewardArmour.getName() + " kazandınız !");
                System.out.println("");
            }else {
                System.out.println("Yerdeki nesneyi almadın !!!");
            }

        }else if(randomNumber() >= 30 && randomNumber() <= 54){
            this.getPlayer().setMoney(this.getPlayer().getMoney() + randomMoney());
            System.out.println("");
            System.out.println(randomMoney() + " para kazandınız.");
            System.out.println("");

        }else{
            System.out.println("");
            System.out.println(" Burada hiçbir şey yok Üzgünüm !!!");
            System.out.println("");
        }
    }
    public int snakeDamage(){
        if (this.getObstacle().getName().equals("Yılan")){
            int randomDamage = random.nextInt(4) + 3;
            obstacle.setDamage(randomDamage);
        }
        return obstacle.getDamage();
    }
    public int randomNumber(){
        int randomNumber = random.nextInt(99);
        return randomNumber;
    }


    public int randomItems(){
        int input = random.nextInt(99);

        if (input >= 0 && input <= 19){
            return 3;
        }else if (input >= 20 && input <= 49){
            return 2;
        }else if (input >= 50 && input <= 99){
            return 1;
        }
        return 0;
    }

    public int randomMoney(){
        int input = random.nextInt(99);
        if (input >= 0 && input <= 19){
            return 10;
        }else if (input >= 20 && input <= 49){
            return 5;
        }else if (input >= 50 && input <= 99){
            return 1;
        }
        return 0;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }
}
