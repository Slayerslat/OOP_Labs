package Lab_3;
import java.util.Scanner;

public class Menu
{
    private static Scanner input = new Scanner(System.in);
    public static void main()
    {
        System.out.print("\n");
        System.out.print("Доступные действия:\n");
        System.out.print("1. Взаимодействие с задачей матрицы\n");
        System.out.print("2. Взаимодействие с задачей вычислителя\n");
        System.out.print("3. Взаимодействие с задачей слов\n");
        System.out.print("0. Завершение работы\n");
        System.out.print("\nВведите номер действия: ");
        switch (input.nextLine())
        {
            case "1":
                matrixField();
                main();
                break;
            case "2":
                calculatorField();
                main();
                break;
            case "3":
                wordsField();
                main();
                break;
            case "0":
                System.out.print("Завершаю работу.\n");
                break;
            default:
                System.out.print("Действие не распознано. Возвращаюсь к главному меню.\n\n");
                main();
                break;
        }
    }
    //region Matrix
    private static Matrix mObject;
    private static void matrixField()
    {
        System.out.print("\n");
        System.out.print("Доступные действия:\n");
        System.out.print("1. Вывести матрицу\n");
        System.out.print("2. Построить матрицу\n");
        System.out.print("3. Загрузить матрицу\n");
        System.out.print("4. Сохранить текущую матрицу\n");
        System.out.print("5. Обработать матрицу согласно заданию\n");
        System.out.print("0. Вернуться в главное меню\n");
        System.out.print("\nВведите номер действия: ");
        switch (input.nextLine())
        {
            case "1":
                System.out.print("\n");
                if (mObject != null)
                {
                    if (mObject.filled())
                        mObject.printMatrix();
                    else
                        System.out.print("Матрица не определена.\n");
                }
                else
                    System.out.print("Матрица не определена.\n");
                matrixField();
                break;
            case "2":
                System.out.print("\n");
                mObject = new Matrix();
                matrixField();
                break;
            case "3":
                System.out.print("\n");
                System.out.print("Введите название загружаемой матрицы: ");
                mObject = new Matrix(ExceptionHelper.readString());
                matrixField();
                break;
            case "4":
                System.out.print("\n");
                if (mObject != null)
                {
                    if (mObject.filled())
                    {
                        System.out.print("Введите название сохраняемой матрицы: ");
                        mObject.saveAs(ExceptionHelper.readString());
                    }
                    else
                        System.out.print("Матрица не определена.\n");
                }
                else
                    System.out.print("Матрица не определена.\n");
                matrixField();
                break;
            case "5":
                System.out.print("\n");
                if (mObject != null)
                {
                    if (mObject.filled())
                        mObject.multiplyLine();
                    else
                        System.out.print("Матрица не определена.\n");
                }
                else
                    System.out.print("Матрица не определена.\n");
                matrixField();
                break;
            case "0":
                System.out.print("\n");
                System.out.print("Возвращаюсь к главному меню.\n");
                break;
            default:
                System.out.print("\n");
                System.out.print("Действие не распознано. Повторите ввод.\n");
                matrixField();
                break;
        }
    }
    //endregion
    //region Calculator
    private static Calculator cObject;
    private static void calculatorField()
    {
        System.out.print("\n");
        System.out.print("Доступные действия:\n");
        System.out.print("1. Вывести решение\n");
        System.out.print("2. Ввести значения\n");
        System.out.print("3. Загрузить значения\n");
        System.out.print("4. Сохранить текущие значения\n");
        System.out.print("0. Вернуться в главное меню\n");
        System.out.print("\nВведите номер действия: ");
        switch (input.nextLine())
        {
            case "1":
                System.out.print("\n");
                if (cObject != null)
                {
                    if (cObject.filled())
                        System.out.print(cObject.answer());
                    else
                        System.out.print("Вычислитель не определён.\n");
                }
                else
                    System.out.print("Вычислитель не определён.\n");
                calculatorField();
                break;
            case "2":
                System.out.print("\n");
                cObject = new Calculator();
                calculatorField();
                break;
            case "3":
                System.out.print("\n");
                System.out.print("Введите название загружаемого вычислителя: ");
                cObject = new Calculator(ExceptionHelper.readString());
                calculatorField();
                break;
            case "4":
                System.out.print("\n");
                if (cObject != null)
                {
                    if (cObject.filled())
                    {
                        System.out.print("Введите название сохраняемого вычислителя: ");
                        cObject.saveAs(ExceptionHelper.readString());
                    }
                    else
                        System.out.print("Вычислитель не определён.\n");
                }
                else
                    System.out.print("Вычислитель не определён.\n");
                calculatorField();
                break;
            case "0":
                System.out.print("\n");
                System.out.print("Возвращаюсь к главному меню.\n");
                break;
            default:
                System.out.print("\n");
                System.out.print("Действие не распознано. Повторите ввод.\n");
                calculatorField();
                break;
        }
    }
    //endregion
    //region Words
    private static Words wObject;
    private static void wordsField()
    {
        System.out.print("\n");
        System.out.print("Доступные действия:\n");
        System.out.print("1. Вывести изначальную строку\n");
        System.out.print("2. Вывести обработанную строку\n");
        System.out.print("3. Ввести строку\n");
        System.out.print("4. Загрузить строку\n");
        System.out.print("5. Сохранить текущую строку\n");
        System.out.print("0. Вернуться в главное меню\n");
        System.out.print("\nВведите номер действия: ");
        switch (input.nextLine())
        {
            case "1":
                System.out.print("\n");
                if (wObject != null)
                {
                    if (wObject.filled())
                        System.out.print("Изначальная строка: " + wObject.getLine() + "\n");
                    else
                        System.out.print("Строка не определена.\n");
                }
                else
                    System.out.print("Строка не определена.\n");
                wordsField();
                break;
            case "2":
                System.out.print("\n");
                if (wObject != null)
                {
                    if (wObject.filled())
                        wObject.print();
                    else
                        System.out.print("Строка не определена.\n");
                }
                else
                    System.out.print("Строка не определена.\n");
                wordsField();
                break;
            case "3":
                System.out.print("\n");
                wObject = new Words();
                wordsField();
                break;
            case "4":
                System.out.print("\n");
                System.out.print("Введите название загружаемой строки: ");
                wObject = new Words(ExceptionHelper.readString());
                wordsField();
                break;
            case "5":
                System.out.print("\n");
                if (wObject != null)
                {
                    if (wObject.filled())
                    {
                        System.out.print("Введите название сохраняемой строки: ");
                        wObject.saveAs(ExceptionHelper.readString());
                    }
                    else
                        System.out.print("Строка не определена.\n");
                }
                else
                    System.out.print("Строка не определена.\n");
                wordsField();
                break;
            case "0":
                System.out.print("\n");
                System.out.print("Возвращаюсь к главному меню.\n");
                break;
            default:
                System.out.print("\n");
                System.out.print("Действие не распознано. Повторите ввод.\n");
                wordsField();
                break;
        }
    }
    //endregion
}
