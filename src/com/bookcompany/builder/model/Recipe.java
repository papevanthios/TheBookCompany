/**
 * The recipe class is the model of every recipe created in the program.
 *
 * @author Evanthios Papadopoulos
 * @since 12-Mar-22
 */

package com.bookcompany.builder.model;

import java.util.List;

public class Recipe extends Entity{
    private int preparationTime;
    private List<Ingredient> listOfIngredients;

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public List<Ingredient> getListOfIngredients() {
        return listOfIngredients;
    }

    public void setListOfIngredients(List<Ingredient> listOfIngredients) {
        this.listOfIngredients = listOfIngredients;
    }
}
