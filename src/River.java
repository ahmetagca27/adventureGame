public class River extends BattleLoc{
    private static boolean checkloc = false;
    public River(Player player) {
        super(player, "Nehir", new Bear(), "Water" , 2);
    }

    public static boolean isCheckloc() {
        return checkloc;
    }

    public static void setCheckloc(boolean checkloc) {
        River.checkloc = checkloc;
    }
}
