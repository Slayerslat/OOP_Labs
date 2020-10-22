package Lab_3;
import java.util.Scanner;

public class ExceptionHelper
{
    private static Scanner input = new Scanner(System.in);
    public static String readString()
    {
        String temp = input.nextLine();
        if (temp != "")
            return temp;
        else
        {
            System.out.print("Пустой ввод. Повторите ввод строки.\n");
            return readString();
        }
    }
    public static int readInt()
    {
        try
        {
            int temp = Integer.parseInt(readString());
            return temp;
        }
        catch (Exception e)
        {
            System.out.print("Ошибка при обработке числа. Повторите ввод целого числа.\n");
            return readInt();
        }
    }
    public static double readDouble()
    {
        try
        {
            double temp = Double.parseDouble(readString());
            return temp;
        }
        catch (Exception e)
        {
            System.out.print("Ошибка при обработке числа. Повторите ввод числа.\n");
            return readDouble();
        }
    }
    public static int[] readIntArray(int size)
    {
        int counter = 0;
        String[] buffer = readString().split(" ");
        for (int i = 0; i < buffer.length; i++)
            if (buffer[i].length() >= 1)
            {
                buffer[counter] = buffer[i];
                counter++;
            }
        if (counter == size)
        {
            try
            {
                int[] temp = new int[counter];
                for (int i = 0; i < counter; i++)
                    temp[i] = Integer.parseInt(buffer[i]);
                return temp;
            }
            catch (Exception e)
            {
                System.out.print("Ошибка при обработке значений. Повторите ввод с [" + size + "] целочисленными аргументами.\n");
                return readIntArray(size);
            }
        }
        else
        {
            System.out.print("Неверное количество значений. Повторите ввод с [" + size + "] целочисленными аргументами.\n");
            return readIntArray(size);
        }
    }
    public static double[] readDoubleArray(int size)
    {
        int counter = 0;
        String[] buffer = readString().split(" ");
        for (int i = 0; i < buffer.length; i++)
            if (buffer[i].length() >= 1)
            {
                buffer[counter] = buffer[i];
                counter++;
            }
        if (counter == size)
        {
            try
            {
                double[] temp = new double[counter];
                for (int i = 0; i < counter; i++)
                    temp[i] = Double.parseDouble(buffer[i]);
                return temp;
            }
            catch (Exception e)
            {
                System.out.print("Ошибка при обработке значений. Повторите ввод с [" + size + "] численными аргументами.\n");
                return readDoubleArray(size);
            }
        }
        else
        {
            System.out.print("Неверное количество значений. Повторите ввод с [" + size + "] численными аргументами.\n");
            return readDoubleArray(size);
        }
    }
}
