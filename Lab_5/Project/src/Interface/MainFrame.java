package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

class MainFrame extends JFrame {

    protected JComboBox<String> chooseTaskComboBox;

    protected JTextField calcFirstNumber;
    protected JTextField calcSecondNumber;
    protected JTextField calcOperation;
    protected JTextField wordSentece;
    protected JTextField matrHeight;
    protected JTextField matrWidth;
    protected JTextField matrNumbers;

    private JPanel fieldsPanel;

    protected JButton doTaskButton;
    protected JMenuItem saveButton;
    protected JMenuItem loadButton;
    protected JFileChooser fileChooser;
    protected JLabel resultSentenceTextLabel;

    protected MainFrame()
    {
        // Инициализация окна
        super("Лабораторная работа №5");
        this.setSize(400, 300);
        this.setLocation(200, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

        // Инициализация меню бара
        var menuBar = new JMenuBar();
        var menu = new JMenu("File");
        saveButton = new JMenuItem("Save");
        loadButton = new JMenuItem("Load");
        JMenuItem exitButton = new JMenuItem("Exit");
        exitButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        menu.add(saveButton);
        menu.add(loadButton);
        menu.add(exitButton);
        menuBar.add(menu);
        this.setJMenuBar(menuBar);

        fileChooser = new JFileChooser();
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new FlowLayout());
        fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new GridLayout(6, 1));

        // Инициализация компонентов ввода
        String[] choices = {"1 Задание (Задание с матрицей)", "2 Задание (Операнд с двумя числами)", "3 Задание (Все слова длиной 1)"};
        chooseTaskComboBox = new JComboBox<>(choices);
        chooseTaskComboBox.setPreferredSize(new Dimension(390, 24));
        calcFirstNumber = new JTextField();
        calcFirstNumber.setPreferredSize(new Dimension(90, 24));
        calcFirstNumber.setToolTipText("Введите первое число");
        calcSecondNumber = new JTextField();
        calcSecondNumber.setPreferredSize(new Dimension(90, 24));
        calcSecondNumber.setToolTipText("Введите второе число");
        calcOperation = new JTextField();
        calcOperation.setPreferredSize(new Dimension(90, 24));
        calcOperation.setToolTipText("Введите операнд");
        wordSentece = new JTextField();
        wordSentece.setPreferredSize(new Dimension(390, 24));
        wordSentece.setToolTipText("Введите слова, разделённые пробелами или запятыми");
        matrHeight = new JTextField();
        matrHeight.setPreferredSize(new Dimension(90, 24));
        matrHeight.setToolTipText("Введите высоту матрицы");
        matrWidth = new JTextField();
        matrWidth.setPreferredSize(new Dimension(90, 24));
        matrWidth.setToolTipText("Введите ширину матрицы");
        matrNumbers = new JTextField();
        matrNumbers.setPreferredSize(new Dimension(180, 24));
        matrNumbers.setToolTipText("Введите матрицу");


        JLabel resultSentenceLabel = new JLabel("Результат: ");
        resultSentenceTextLabel = new JLabel("Нет результата");

        doTaskButton = new JButton("Решить задание");

        chooseTaskComboBox.setSelectedIndex(0);
        repaintFields();

        resultPanel.add(resultSentenceLabel);
        resultPanel.add(resultSentenceTextLabel);

        chooseTaskComboBox.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                repaintFields();
            }
        });

        // Добавление компонентов в окно
        this.add(fieldsPanel);
        this.add(resultPanel);
    }

    private void repaintFields()
    {
        switch (chooseTaskComboBox.getSelectedIndex())
        {
            case 0:
            {
                fieldsPanel.removeAll();
                fieldsPanel.add(chooseTaskComboBox);
                fieldsPanel.add(matrHeight);
                fieldsPanel.add(matrWidth);
                fieldsPanel.add(matrNumbers);
                fieldsPanel.add(doTaskButton);
                this.validate();
                this.repaint();
                break;
            }
            case 1:
            {
                fieldsPanel.removeAll();
                fieldsPanel.add(chooseTaskComboBox);
                fieldsPanel.add(calcFirstNumber);
                fieldsPanel.add(calcSecondNumber);
                fieldsPanel.add(calcOperation);
                fieldsPanel.add(doTaskButton);
                this.validate();
                this.repaint();
                break;
            }
            case 2:
            {
                fieldsPanel.removeAll();
                fieldsPanel.add(chooseTaskComboBox);
                fieldsPanel.add(wordSentece);
                fieldsPanel.add(doTaskButton);
                this.validate();
                this.repaint();
                break;
            }
        }
    }

    // Валидация полей ввода
    public boolean validateFields()
    {
        switch (chooseTaskComboBox.getSelectedIndex())
        {
            case 0:
            {
                if
                (
                        !validateSoloNumber(matrHeight.getText()) || !validateSoloNumber(matrWidth.getText())
                        || !validateNumber(matrNumbers.getText()) || matrHeight.getText().isEmpty()
                        || matrWidth.getText().isEmpty() || matrNumbers.getText().isEmpty()
                )
                    return false;
                break;
            }
            case 1:
            {
                if
                (
                        calcFirstNumber.getText().isEmpty() || calcSecondNumber.getText().isEmpty()
                        || calcOperation.getText().isEmpty() || !validateSoloNumber(calcFirstNumber.getText())
                        || !validateSoloNumber(calcSecondNumber.getText())
                )
                    return false;
                break;
            }
            case 2:
            {
                if (wordSentece.getText().isEmpty())
                    return false;
                break;
            }
        }
        return true;
    }
    public boolean validateNumber(String text)
    {
        return Pattern.matches("[\\d\\s]+", text);
    }
    public boolean validateSoloNumber(String text)
    {
        return Pattern.matches("[\\d\\s]", text);
    }
}
