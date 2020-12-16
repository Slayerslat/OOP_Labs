package Interface;

import Lab_3.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Controller
{
    private final MainFrame View = new MainFrame();
    private File file;
    private boolean isThereAnyFrames = false;
    private Matrix firstTask = null;
    private Calculator secondTask = null;
    private Words thirdTask = null;
    public Controller()
    {
        View.setVisible(true);
        boolean exit = true;
        // Добавляем действие кнопки "Решить задание"
        View.doTaskButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                if (!View.validateFields())
                {
		            isThereAnyFrames = true;
                    createDialog(View, "Поле пустое или содержит ошибку ввода!", 350);
                    return;
                }
                doTask();
            }
        });
        // действие кнопки сохранения
        View.saveButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                switch (View.chooseTaskComboBox.getSelectedIndex())
                {
                    case 0:
                    {
                        if (firstTask != null)
                        {
                            View.fileChooser.showSaveDialog(View);
                            firstTask.saveAs(View.fileChooser.getSelectedFile().getAbsolutePath());
                        }
                        else
                        {
		    	            isThereAnyFrames = true;
                            createDialog(View, "Задание не было выполнено!", 200);
                        }
                        break;
                    }
                    case 1:
                    {
                        if (secondTask != null)
                        {
                            View.fileChooser.showSaveDialog(View);
                            secondTask.saveAs(View.fileChooser.getSelectedFile().getAbsolutePath());
                        }
                        else
                        {
		    	            isThereAnyFrames = true;
                            createDialog(View, "Задание не было выполнено!", 200);
                        }
                        break;
                    }
                    case 2:
                    {
                        if (thirdTask != null)
                        {
                            View.fileChooser.showSaveDialog(View);
                            thirdTask.saveAs(View.fileChooser.getSelectedFile().getAbsolutePath());
                        }
                        else
                        {
		     	            isThereAnyFrames = true;
                            createDialog(View, "Задание не было выполнено!", 200);
                        }
                        break;
                    }
                }
            }
        });
        // Действие кнопки загрузки
        View.loadButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                View.fileChooser.showOpenDialog(View);
                file = View.fileChooser.getSelectedFile();
                switch (View.chooseTaskComboBox.getSelectedIndex())
                {
                    case 0:
                    {
                        if (!file.getAbsolutePath().endsWith(".mx"))
                        {
		   	                isThereAnyFrames = true;
                            createDialog(View, "Неправильный тип файла", 150);
                            return;
                        }
                        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file.getAbsolutePath())))
                        {
                            firstTask = (Matrix) ois.readObject();
                            View.matrHeight.setText(String.valueOf(firstTask.getHeight()));
                            View.matrWidth.setText(String.valueOf(firstTask.getWidth()));
                            int[][] temp = firstTask.getMatrix();
                            String tempLine = "";
                            for (int i = 0; i < firstTask.getHeight(); i++)
                                for (int j = 0; j < firstTask.getWidth(); j++)
                                    tempLine = tempLine + temp[i][j] + " ";
                            View.matrNumbers.setText(tempLine);
                        }
                        catch(Exception exception)
                        {
                            System.out.println(exception.getMessage());
                        }
                        break;
                    }
                    case 1:
                    {
                        if (!file.getAbsolutePath().endsWith(".clc"))
                        {
		   	                isThereAnyFrames = true;
                            createDialog(View, "Неправильный тип файла", 150);
                            return;
                        }
                        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file.getAbsolutePath())))
                        {
                            secondTask = (Calculator) ois.readObject();
                            View.calcFirstNumber.setText(String.valueOf(secondTask.getA()));
                            View.calcSecondNumber.setText(String.valueOf(secondTask.getB()));
                            View.calcOperation.setText(secondTask.getOperation());
                        }
                        catch(Exception exception)
                        {
                            System.out.println(exception.getMessage());
                        }
                        break;
                    }
                    case 2:
                    {
                        if (!file.getAbsolutePath().endsWith(".ws"))
                        {
		   	                isThereAnyFrames = true;
                            createDialog(View, "Неправильный тип файла", 150);
                            return;
                        }
                        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file.getAbsolutePath())))
                        {
                            thirdTask = (Words) ois.readObject();
                            View.wordSentece.setText(thirdTask.getLine());
                        }
                        catch(Exception exception)
                        {
                            System.out.println(exception.getMessage());
                        }
                        break;
                    }
                }
            }
        });
    }
//	Выполнение задания в зависимости от выбранного задания
    private void doTask()
    {
        switch (View.chooseTaskComboBox.getSelectedIndex())
        {
            case 0:
            {
                firstTask = new Matrix
                (
                    Integer.parseInt(View.matrHeight.getText()),
                    Integer.parseInt(View.matrWidth.getText()),
                    View.matrNumbers.getText()
                );
                View.resultSentenceTextLabel.setText(firstTask.doTask());
                break;
            }
            case 1:
            {
                secondTask = new Calculator
                (
                    Double.parseDouble(View.calcFirstNumber.getText()),
                    Double.parseDouble(View.calcSecondNumber.getText()),
                    View.calcOperation.getText()
                );
                View.resultSentenceTextLabel.setText(secondTask.answer());
                break;
            }
            case 2:
            {
                thirdTask = new Words(View.wordSentece.getText());
                View.resultSentenceTextLabel.setText(thirdTask.doTask());
                break;
            }
        }
    }
    //    Создание диалогового окна
    private void createDialog(JFrame mainFrame, String text, int width)
    {
        final JDialog temp = new JDialog(mainFrame, "Ошибка");
        temp.add(new JLabel(text));
        temp.setLayout(new FlowLayout());
        JButton okBtn = new JButton("Ok");
        temp.add(okBtn);
        okBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                isThereAnyFrames = false;
                temp.dispose();
            }
        });
        temp.setLocationRelativeTo(mainFrame);
        temp.setLocation(temp.getX() - width / 2, temp.getY() - 50);
        temp.setSize(width, 90);
        temp.setVisible(true);
    }
}
