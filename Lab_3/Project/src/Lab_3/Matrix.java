package Lab_3;
import java.io.*;
public class Matrix implements Serializable
{
    private int[][] matrix;
    private int height = 0;
    private int width = 0;
    public Matrix()
    {
        System.out.print("Введите высоту матрицы: ");
        height = ExceptionHelper.readInt();
        while (height < 2)
        {
            System.out.print("Пожалуйста, введите число более 1: ");
            height = ExceptionHelper.readInt();
        }
        System.out.print("Введите ширину матрицы: ");
        width = ExceptionHelper.readInt();
        while (width < 2)
        {
            System.out.print("Пожалуйста, введите число более 1: ");
            width = ExceptionHelper.readInt();
        }
        System.out.println("Далее строка за строкой введите матрицу размерностью " + height + "x" + width);
        matrix = new int[height][width];
        for (int i = 0; i < height; i++)
        {
            int[] temp = ExceptionHelper.readIntArray(width);
            for (int j = 0; j < width; j++)
                matrix[i][j] = temp[j];
        }
    }
    public Matrix(String name)
    {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(name + ".mx")))
        {
            Matrix temp = (Matrix)ois.readObject();
            System.out.print("Файл найден. Начинаю сборку.\n");
            matrix = temp.matrix;
            height = temp.height;
            width = temp.width;
        }
        catch (Exception exception)
        {
            System.out.print("Объект с указанным именем не найден.\n");
        }
    }
    public void multiplyLine()
    {
        System.out.print("Выберите изменяемую строку(2-" + height + "): ");
        int line = ExceptionHelper.readInt() - 1;
        while (line < 1 || line + 1 > height)
        {
            System.out.print("Пожалуйста, введите число от 2 до " + height + ": ");
            line = ExceptionHelper.readInt() - 1;
        }
        for (int i = 0; i < width; i++)
            matrix[line][i] *= matrix[0][i];
    }
    public void printMatrix()
    {
        System.out.print("Ваша матрица:\n");
        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
                System.out.print(matrix[i][j] + " ");
            System.out.print('\n');
        }
    }
    public void saveAs(String name)
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(name + ".mx")))
        {
            oos.writeObject(this);
        }
        catch(Exception exception)
        {
            System.out.println(exception.getMessage());
        }
    }
    public boolean filled()
    {
        return matrix != null;
    }
}
