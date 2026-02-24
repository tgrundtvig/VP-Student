package dk.ek.evu.vpf26.txtadv.user;

public interface TextAppUser
{
    //Output
    void print(String text);
    void println(String text);
    void println();

    //Input
    String readLine(String prompt);
    int readInt(String prompt);
    int readInt(String prompt, int min, int max);
    float readFloat(String prompt);
    float readFloat(String prompt, float min, float max);
    int choose(String[] choices, String prompt);
}
