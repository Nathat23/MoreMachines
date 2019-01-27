package uk.antiperson.moremachines.compat;

public enum Compat {
    WORLDGUARD("WorldGuard");

    public final String plName;
    Compat(String plName){
        this.plName = plName;
    }

    public String getPlName() {
        return plName;
    }
}
