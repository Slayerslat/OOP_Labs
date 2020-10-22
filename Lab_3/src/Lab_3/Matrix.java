package Lab_3;
import java.io.*;
public class Matrix implements Serializable
{
    private int[][] matrix;
    public Matrix()
    {
        System.out.print("Далее строка за строкой введите матрицу размерностью 5x5:\n");
        matrix = new int[5][5];
        for (int i = 0; i < 5; i++)
        {
            int[] temp = ExceptionHelper.readIntArray(5);
            for (int j = 0; j < 5; j++)
                matrix[i][j] = temp[j];
        }
    }
    public Matrix(String name)
    {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(name + ".mx")))
        {
            System.out.print("Файл найден. Начинаю сборку.\n");
            Matrix temp = (Matrix) ois.readObject();
            matrix = temp.matrix;
        }
        catch (Exception exception)
        {
            System.out.print("Объект с указанным именем не найден.\n");
        }
    }
    public void multiplyLine()
    {
        System.out.print("Выберите изменяемую строку(2-5):\n");
        int line = ExceptionHelper.readInt() - 1;
        for (int i = 0; i < 5; i++)
            matrix[line][i] *= matrix[0][i];
    }
    public void printMatrix()
    {
        System.out.print("Ваша матрица:\n");
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 5; j++)
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
