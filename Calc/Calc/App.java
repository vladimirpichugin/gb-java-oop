import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

interface ComplexCalculator {
    Complex add(Complex a, Complex b);
    Complex multiply(Complex a, Complex b);
    Complex divide(Complex a, Complex b);
}

class Complex {
    private double real;
    private double imaginary;

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public double getReal() {
        return real;
    }

    public double getImaginary() {
        return imaginary;
    }
}

class BasicComplexCalculator implements ComplexCalculator {
    @Override
    public Complex add(Complex a, Complex b) {
        double real = a.getReal() + b.getReal();
        double imaginary = a.getImaginary() + b.getImaginary();
        return new Complex(real, imaginary);
    }

    @Override
    public Complex multiply(Complex a, Complex b) {
        double real = (a.getReal() * b.getReal()) - (a.getImaginary() * b.getImaginary());
        double imaginary = (a.getReal() * b.getImaginary()) + (a.getImaginary() * b.getReal());
        return new Complex(real, imaginary);
    }

    @Override
    public Complex divide(Complex a, Complex b) {
        double denominator = (b.getReal() * b.getReal()) + (b.getImaginary() * b.getImaginary());
        double real = ((a.getReal() * b.getReal()) + (a.getImaginary() * b.getImaginary())) / denominator;
        double imaginary = ((a.getImaginary() * b.getReal()) - (a.getReal() * b.getImaginary())) / denominator;
        return new Complex(real, imaginary);
    }
}

class Calculator extends JFrame {
    private JTextField realField1;
    private JTextField imaginaryField1;
    private JTextField realField2;
    private JTextField imaginaryField2;
    private JButton addButton;
    private JButton multiplyButton;
    private JButton divideButton;
    private JLabel resultLabel;

    private ComplexCalculator calculator;
    //private Logger logger;

    public Calculator() {
        calculator = new BasicComplexCalculator();
        logger = LogManager.getLogger(Calculator.class);

        setTitle("Калькулятор");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        realField1 = new JTextField();
        imaginaryField1 = new JTextField();
        realField2 = new JTextField();
        imaginaryField2 = new JTextField();
        addButton = new JButton("+");
        multiplyButton = new JButton("*");
        divideButton = new JButton("/");
        resultLabel = new JLabel();

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Complex a = createComplexNumber(realField1.getText(), imaginaryField1.getText());
                Complex b = createComplexNumber(realField2.getText(), imaginaryField2.getText());
                Complex result = calculator.add(a, b);
                resultLabel.setText(formatComplexNumber(result));
                logger.info("Сложение");
            }
        });

        multiplyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Complex a = createComplexNumber(realField1.getText(), imaginaryField1.getText());
                Complex b = createComplexNumber(realField2.getText(), imaginaryField2.getText());
                Complex result = calculator.multiply(a, b);
                resultLabel.setText(formatComplexNumber(result));
                logger.info("Умножение");
            }
        });

        divideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Complex a = createComplexNumber(realField1.getText(), imaginaryField1.getText());
                Complex b = createComplexNumber(realField2.getText(), imaginaryField2.getText());
                Complex result = calculator.divide(a, b);
                resultLabel.setText(formatComplexNumber(result));
                logger.info("Деление");
            }
        });

        add(new JLabel("Комплексное число 1 (a + bi)"));
        add(new JLabel("Комплексное число 2 (a + bi)"));
        add(new JLabel("Реальная часть:"));
        add(realField1);
        add(realField2);
        add(new JLabel("Мнимая часть:"));
        add(imaginaryField1);
        add(imaginaryField2);
        add(addButton);
        add(multiplyButton);
        add(divideButton);
        add(resultLabel);

        pack();
        setVisible(true);
    }

    private Complex createComplexNumber(String realPart, String imaginaryPart) {
        double real = Double.parseDouble(realPart);
        double imaginary = Double.parseDouble(imaginaryPart);
        return new Complex(real, imaginary);
    }

    private String formatComplexNumber(Complex number) {
        return number.getReal() + " + " + number.getImaginary() + "i";
    }
}


public class App {
    public static void main(String[] args) {
        System.setProperty("logFilename", "logs.txt");

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Calculator();
            }
        });
    }
}

