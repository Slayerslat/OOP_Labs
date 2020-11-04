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
    public Words(String name)
    {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(name + ".ws")))
        {
            Words temp = (Words)ois.readObject();
            System.out.print("Файл найден. Начинаю сборку.\n");
            line = temp.line;
        }
        catch(Exception exception)
        {
            System.out.println("Объект с указанным именем не найден. Создаю базовый экземпляр.");
            line = "o,iho n w,e,voj mqi v,bps gb,n";
        }
        finally
        {
            doTask();
        }
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
    private void doTask()
    {
        int counter = 0;
        String[] buffer = line.split("[, ]");
        for (int i = 0; i < buffer.length; i++)
            if (buffer[i].length() == 1)
            {
                buffer[counter] = buffer[i];
                counter++;
            }
        array = new String[counter];
        for (int i = 0; i < counter; i++)
            array[i] = buffer[i];
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
}
