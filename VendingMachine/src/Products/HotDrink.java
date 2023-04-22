package Products;

public class HotDrink extends Product {
    private Integer temperature;

    public HotDrink(String name, Double price, Integer temperature) {
        super(name, price);
        this.temperature = temperature;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        if (temperature <= 0) {
            throw new IllegalStateException(String.format("Температура указана некорректно!", temperature));
        }
        this.temperature = temperature;
    }

    /**
     * переопределение вывода продукта
     */
    @Override
    public String toString()
    {
        return  "HotDrink{" +
                "name='" + this.getName() + "'" +
                ", cost=" + this.getPrice() +
                ", temperature=" + temperature +
                "}";
    }
}
