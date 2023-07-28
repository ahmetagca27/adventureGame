import java.util.Random;
public class Snake extends Obstacle{

    public Snake(){
        super(4, "Yılan" ,0, 13 , 0, null);
        Random a = new Random();
        int randomDamages = a.nextInt(4) + 3;
        super.setDamage(randomDamages);
    }
}
