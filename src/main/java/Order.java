import java.util.List;

public class Order {
    private List<String> ingredients;

    public Order(List<String> ingredients) { // Конструктор класса с параметрами ингридиенты
        this.ingredients = ingredients;
    }
//сеттеры и геттеры
    public Order() { // Конструктор  без параметров
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}
