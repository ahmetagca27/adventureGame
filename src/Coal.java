public class Coal extends BattleLoc{
    private static boolean checkloc = false;
    public Coal(Player player){
        super(player ,"Maden" , new Snake() ,null,5 );
    }

    public static boolean isCheckloc() {
        return checkloc;
    }

    public static void setCheckloc(boolean checkloc) {
        Coal.checkloc = checkloc;
    }
}
