public class ToolStore extends NormalLoc {

    public ToolStore(Player player) {
        super(player, "Mağaza");
    }

    @Override
    public boolean onLocation(){
        System.out.println("----------------- [*** Mağazaya Hoşgeldiniz ***] -----------------");
        boolean showMenu = true;
        while (showMenu) {
            System.out.println("1 - Silahlar");
            System.out.println("2 - Zırhlar");
            System.out.println("3 - Çıkış Yap");
            System.out.print("Seçiminiz : ");
            int selectCase = Location.input.nextInt();
            input.nextLine();
            while (selectCase < 1 || selectCase > 3){
                System.out.print("Geçersiz Değer, Tekrar Giriniz ! Lütfen geçerli bir değer giriniz !");
                selectCase = input.nextInt();
            }

            switch (selectCase){
                case 1:
                    printWeapon();
                    buyWeapon();
                    break;
                case 2:
                    printArmor();
                    buyArmor();
                    break;
                case 3:
                    System.out.println("Tekrar Bekleriz.");
                    showMenu = false;
                    break;
                default:

                    showMenu = true;
                    System.out.print("Geçersiz Değer, Tekrar Giriniz ! Lütfen geçerli bir değer giriniz !");

            }
        }
        return true;
    }
    public void printWeapon(){
        System.out.println("#### [Silahlar] ####");
        System.out.println();
        for (Weapon w: Weapon.weapons()){
            System.out.println(w.getId() + " - " + w.getName() +
                    " --->  Para : " + w.getPrice()+"$" + ", Hasar :" + w.getDamage());
        }
        System.out.println("0 - Çıkış Yap !");
    }

    public void buyWeapon(){
        System.out.print("Lütfen Bir Silah Seçiniz :");
        int selectWeaponID = input.nextInt();
        while (selectWeaponID < 0 || selectWeaponID > Weapon.weapons().length){
            System.out.println("Geçersiz Değer ! Tekrar Giriniz : ");
            selectWeaponID = input.nextInt();
        }
        if(selectWeaponID != 0){
            Weapon selectedWeapon = Weapon.getWeaponObjByID(selectWeaponID);

            if(selectedWeapon != null){
                if (selectedWeapon.getPrice() > this.getPlayer().getMoney()){
                    System.out.println("Yeterli Paranız Bulunmamaktadır !");
                }else{
                    System.out.println(selectedWeapon.getName() + " Silahını Satın Aldınız !");
                    int balance = this.getPlayer().getMoney() - selectedWeapon.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Kalan Bakiye : " + this.getPlayer().getMoney());
                    System.out.println("Önceki Silahınız : " + this.getPlayer().getInventory().getWeapon().getName());
                    this.getPlayer().getInventory().setWeapon(selectedWeapon);
                    System.out.println("Yeni Silahınız : " + this.getPlayer().getInventory().getWeapon().getName());
                }
            }
        }

    }

    public void printArmor(){
        System.out.println("#### [Zırhlar] ####");
        System.out.println();
        for (Armor a: Armor.armors()){
            System.out.println(a.getId() +" - " + a.getName() +" --->  Para : " + a.getPrice()+"$" + ", Zırh :" + a.getBlock());
            //System.out.println(a.getId() + " - " + a.getName() + "Para : " + a.getPrice() + " $"+ ", Zırh : " + a.getBlock());
        }
        System.out.println("0 - Çıkış Yap !");
    }

    public void  buyArmor(){
        System.out.print(" Bir Zırh Seçiniz :");
        int selectArmorID = input.nextInt();
        while (selectArmorID < 0 || selectArmorID > Armor.armors().length){
            System.out.println("Geçersiz Değer ! Tekrar Giriniz : ");
            selectArmorID = input.nextInt();
        }

        if(selectArmorID != 0){
            Armor selectedArmor = Armor.getArmorObjByID(selectArmorID);
            if(selectedArmor != null){
                if (selectedArmor.getPrice() > this.getPlayer().getMoney()){
                    System.out.println("Yeterli Bakiye Bulunmamaktadır !");
                }else{
                    System.out.println(selectedArmor.getName() + " Zırhını Satın Aldınız !");
                    int balance = this.getPlayer().getMoney() - selectedArmor.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Kalan Paranız : " + this.getPlayer().getMoney());
                    System.out.println("Önceki Zırhınız : " + this.getPlayer().getInventory().getArmor().getName());
                    this.getPlayer().getInventory().setArmor(selectedArmor);
                    System.out.println("Yeni Zırhınız : " + this.getPlayer().getInventory().getArmor().getName());
                }
            }

        }

    }
}
