public class Forest extends BattleLoc{
    private static boolean checkloc = false;
    public Forest(Player player) {
        super(player, "Orman", new Vampire(), "firewood" ,3 );
    }

    public static boolean isCheckloc() {
        return checkloc;
    }

    public static void setCheckloc(boolean checkloc) {
        Forest.checkloc = checkloc;
    }

}
