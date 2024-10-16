package model;

import java.util.ArrayList;
import java.util.List;

public class OrderIngredient {
    private List<String> ingredients;

    public OrderIngredient(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public OrderIngredient() {
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}


