package Lab_3;
import java.io.*;
public class Calculator implements Serializable
{
    private double a;
    private double b;
    private String operation;

    public Calculator(double a, double b, String operation)
    {
        this.a = a;
        this.b = b;
        this.operation = operation;
    }
    public double getA() {
        return a;
    }
    public double getB() {
        return b;
    }
    public String getOperation() {
        return operation;
    }
    public Calculator()
    {
        System.out.print("Через пробел введите два рабочих числа: ");
        double[] temp = ExceptionHelper.readDoubleArray(2);
        a = temp[0];
        b = temp[1];
        System.out.print("Введите знак операции: ");
        operation = ExceptionHelper.readString();
    }
    public Calculator(String name)
    {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(name)))
        {
            Calculator temp = (Calculator)ois.readObject();
            System.out.print("Файл найден. Начинаю сборку.\n");
            a = temp.a;
            b = temp.b;
            operation = temp.operation;
        }
        catch(Exception exception)
        {
            System.out.println("Объект с указанным именем не найден.");
        }
    }
    public void saveAs(String name)
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(name + ".clc")))
        {
            oos.writeObject(this);
        }
        catch(Exception exception)
        {
            System.out.println(exception.getMessage());
        }
    }
    public String answer()
    {
        switch (operation)
        {
            case "+":
                return a + " " + operation + " " + b + " = " + (a + b) + '\n';
            case "-":
                return a + " " + operation + " " + b + " = " + (a - b) + '\n';
            case "*":
                return a + " " + operation + " " + b + " = " + (a * b) + '\n';
            case "/":
                return a + " " + operation + " " + b + " = " + (a / b) + '\n';
            default:
                return "Операция недействительна\n";
        }
    }
    public boolean filled()
    {
        return operation != null;
    }
}
