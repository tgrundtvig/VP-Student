package dk.ek.evu.vpf26.txtadv;

public class ScriptedTextIn implements TextIn
{
    private final String[] script;
    private int nextCommand;

    public ScriptedTextIn(String[] script)
    {
        this.script = script;
        this.nextCommand = 0;
    }

    @Override
    public String ask(String question)
    {
        if(nextCommand >= script.length)
        {
            return "quit";
        }
        return script[nextCommand++];
    }
}
