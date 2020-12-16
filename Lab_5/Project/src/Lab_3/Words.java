package Lab_3;
import java.io.*;
public class Words implements Serializable
{
    private String line;
    private String[] array;
    public Words()
    {
        System.out.print("Введите обрабатываемые слова через пробел или запятую: ");
        line = ExceptionHelper.readString();
        doTask();
    }
    public Words(String sentence)
    {
        line = sentence;
    }
    public void saveAs(String name)
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(name + ".ws")))
        {
            oos.writeObject(this);
        }
        catch(Exception exception)
        {
            System.out.println(exception.getMessage());
        }
    }
    public String getLine()
    {
        return line;
    }
    public String doTask()
    {
        String[] buffer = line.split("[, ]");
        String tempLine = "";
        for (int i = 0; i < buffer.length; i++)
            if (buffer[i].length() == 1)
                tempLine = tempLine + buffer[i] + " ";
        return tempLine;
    }
    public void print()
    {
        System.out.print("Все односимвольные слова: ");
        for (int i = 0; i < array.length; i++)
            System.out.print(array[i] + ' ');
        System.out.println();
    }
    public boolean filled()
    {
        return line != null;
    }
    public void setLine(String line) {
        this.line = line;
    }
}
