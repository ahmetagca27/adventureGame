import java.util.NoSuchElementException;
import java.util.Scanner;

public class Game {
    private Scanner input = new Scanner(System.in);
    public void start(){
        System.out.println("Macera Oyununa Hoşgeldiniz !");
        System.out.print("Lütfen bir isim giriniz : ");
        String playerName = input.nextLine();
        Player player =new Player(playerName);
        System.out.println("Sayın " + player.getName() + " bu karanlık ve sisli adaya hoş geldiniz !! Burada yananların hepsi gerçek ");
        player.selectChar();
        Location location = null;

        while (true){
            player.printInfo();
            System.out.println("");
            System.out.println("****************************[Bölgeler]****************************");
            System.out.println("1 - Güvenli Ev -----> Burası sizin için Güvenli bir ev düşman yoktur !");
            System.out.println("2 - Eşya Dükkanı \t  -----> Silah veya Zırh Satın alabilirsiniz.");
            System.out.println("3 - Mağara \t  -----> <Ödül> ~|Yemek|~ , dikkatli ol canavar Çıkabilir !");
            System.out.println("4 - Orman \t   -----> <Ödül> ~|Odun|~ , dikkatli ol canavar Çıkabilir !");
            System.out.println("5 - Nehir  \t  -----> <Ödül> ~|Su|~ , dikkatli ol canavar Çıkabilir !");
            System.out.println("6 - Maden  \t  -----> <Ödül> ~|Ganimet|~ , dikkatli ol canavar Çıkabilir !");
            System.out.println("0 - Çıkış Yap  -----> Oyunu Sonlandır !");
            System.out.println("******************************************************************");
            System.out.println("");
            System.out.print("Lütfen Gitmek istediğiniz Bölgeyi Seçiniz : ");


            String  selectLoc = input.nextLine();

            switch (selectLoc){
                case "0":
                    location = null;
                    break;
                case "1":
                    location = new SafeHouse(player);
                    break;

                case "2":
                    location = new ToolStore(player);
                    break;
                case "3":
                    if(Cave.isCheckloc() != true){
                        location = new Cave(player);
                        break;
                    } else{
                        locClosed();
                        continue;
                    }
                case "4":
                    if (Forest.isCheckloc() != true){
                        location = new Forest(player);
                        break;
                    } else {
                        locClosed();
                        continue;
                    }
                case "5" :
                    if (River.isCheckloc() != true){
                        location = new River(player);
                        break;
                    } else {
                        locClosed();
                        continue;
                    }
                case "6" :
                    if (Coal.isCheckloc() != true){
                        location = new Coal(player);
                        break;
                    } else {
                        locClosed();
                        continue;
                    }
                default:
                    System.out.println("Lütfen Geçerli Bir Değer Giriniz !");
                    System.out.println();
                    continue;
            }
            if(location == null) {
                System.out.println("Bu Karanlık ve sisli Adadan Çabuk vazgeçtin ! ");
                break;
            }
            if (!location.onLocation())
            {
                break;
            }
        }
    }
    public void locClosed(){
        String s="\u2705";
        System.out.println(s.repeat(3)+" Bölge Daha Önce Temizlendi "+s.repeat(3) + "\n  ¨∆¨∆¨∆ * Tekrar Seçilemez * ∆¨∆¨∆¨" );
        System.out.println();
    }

}
