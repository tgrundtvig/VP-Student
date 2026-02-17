package dk.ek.evu.vpf26.txtadv;

public class Main
{
    public static void main(String[] args)
    {
        String[] script = {"north", "west", "west", "north", "east", "north"};
        //TextIn textIn = new ScriptedTextIn(script);
        TextIn textIn = new ScannerTextIn();
        MyMaceFactory factory = new MyMaceFactory();
        Mace myMace = factory.createMyMace();
        Room start = myMace.getStart();
        Player me = new Player(textIn, start);
        me.moveInMace();
    }
}
