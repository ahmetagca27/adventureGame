public abstract class NormalLoc extends Location{
    @Override
    public boolean onLocation(){
        return true;
    }

    public NormalLoc(Player player, String name) {
        super(player, name);
    }
}
