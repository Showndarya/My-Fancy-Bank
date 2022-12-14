package utilities;
public class Tuple
{
    private String key;
    private int value;

    public Tuple(String key, int value)
    {
        this.key = key;
        this.value = value;
    }


    public String getKey() { return key; }
    public int getValue() {  return value; }
    @Override
    public String toString() { return key; }
}
