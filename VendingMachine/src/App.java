import Products.HotDrink;
import Products.Product;
import Products.BottleOfWater;
import VendingMachines.VendingMachine;

public class App {
    public static void main(String[] args) throws Exception {

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

        for (Product prod: itemMachine.getProdAll())
        {
            System.out.println(prod.toString());
        }
       
    }
}
