import Products.BottleOfWater;
import Products.HotDrink;
import Products.Product;
import VendingMachines.VendingMachine;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JLabel depositLabel;
    private Double deposit;

    public MainFrame() {
        // Создание окна JFrame
        super("Vending Machine");

        Product item1 = new Product("Cola",88.0);
        item1.setPrice(98.0);

        VendingMachine itemMachine = new VendingMachine(300);
        itemMachine.addProduct(item1);
        itemMachine.addProduct(new Product("Чипсы", 60.0));
        itemMachine.addProduct(new Product("Масло", 50.0));
        itemMachine.addProduct(new Product("Хлеб", 40.0));
        itemMachine.addProduct(new Product("Снек", 20.0));
        itemMachine.addProduct(new BottleOfWater("Cola", 88.0, 500 ));
        itemMachine.addProduct(new BottleOfWater("Water", 188.0, 1500 ));
        itemMachine.addProduct(new HotDrink("Латте", 299.99, 37));
        itemMachine.addProduct(new HotDrink("Чай Зелёный", 70.0, 37));
        itemMachine.addProduct(new HotDrink("Чай Чёрный", 50.0, 37));
        itemMachine.addProduct(new HotDrink("Глинтвейн", 100.0, 40));


        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        deposit = 0.0;

        // Создание JPanel для размещения элементов интерфейса
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));

        // Создание плиточной таблицы для отображения товаров
        JPanel productsPanel = new JPanel(new GridLayout(6, 1, 10, 10));
        productsPanel.setBorder(BorderFactory.createTitledBorder("Товары"));

        for (Product prod: itemMachine.getProdAll())
        {
            addProduct(productsPanel, prod.getName(), prod.getPrice());
        }

        // Добавление плиточной таблицы на JPanel
        panel.add(productsPanel);

        // Создание JLabel для отображения депозита
        JPanel depositPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        depositPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        JLabel depositTextLabel = new JLabel("Депозит ₽: ");
        depositLabel = new JLabel(deposit.toString());
        depositPanel.add(depositTextLabel);
        depositPanel.add(depositLabel);

        // Добавление панели с депозитом на JPanel
        panel.add(depositPanel);

        // Создание поля ввода для пополнения депозита
        JPanel depositControlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        depositControlPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        JTextField depositField = new JTextField();
        depositField.setPreferredSize(new Dimension(100, 30));
        JButton depositButton = new JButton("Внести депозит");
        depositButton.addActionListener(e -> depositButtonClicked(depositField));
        depositControlPanel.add(depositField);
        depositControlPanel.add(depositButton);

        // Добавление панели управления депозитом на JPanel
        panel.add(depositControlPanel);

        // Добавление JPanel на JFrame
        add(panel);

        // Отображение окна
        setVisible(true);
    }

    // Метод для добавления продукта на плиточную таблицу
    private void addProduct(JPanel productsPanel, String name, Double price) {
        JPanel productPanel = new JPanel(new BorderLayout(10, 10));
        productPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel priceLabel = new JLabel(price.toString());
        JButton buyButton = new JButton(name + " - " + price + " ₽");
        buyButton.addActionListener(e -> buyButtonClicked(name, price));
        productPanel.add(priceLabel, BorderLayout.CENTER);
        productPanel.add(buyButton, BorderLayout.SOUTH);
        productsPanel.add(productPanel);
    }

    private void buyButtonClicked(String name, Double price) {
        if (deposit-price >= 0) {
            deposit = deposit - price;
            depositLabel.setText(deposit.toString());
            System.out.println("Куплен продукт: " + name + " за " + price.toString());
            JOptionPane.showMessageDialog(this, "Продукт " + name + " куплен!", "Спасибо за покупку", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Недостаточно средств. Пополните депозит.", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void depositButtonClicked(JTextField depositField) {
        try {
            double amount = Double.parseDouble(depositField.getText());
            // Добавление кода для пополнения депозита на заданную сумму
            System.out.println("Депозит пополнен " + deposit);
            deposit = deposit + amount;
            depositField.setText("");
            depositLabel.setText(String.format("%.2f", deposit));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Введите правильную сумму", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
