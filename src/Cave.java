public class Cave extends BattleLoc{
    private static boolean checkloc = false;
    public Cave(Player player) {
        super(player, "Mağara", new Zombie(), "Food",3);
    }
    public static boolean isCheckloc() {
        return checkloc;
    }

    public static void setCheckloc(boolean checkloc) {
        Cave.checkloc = checkloc;
    }
}
