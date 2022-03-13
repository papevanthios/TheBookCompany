/**
 * The ingredient class is the model of every ingredient created in the program.
 *
 * @author Evanthios Papadopoulos
 * @since 12-Mar-22
 */

package com.bookcompany.builder.model;

import java.math.BigDecimal;

public class Ingredient extends Entity{
    private BigDecimal costOfUnit;
    private int calories;
    private String isVegetarian;

    public BigDecimal getCostOfUnit() {
        return costOfUnit;
    }

    public void setCostOfUnit(BigDecimal costOfUnit) {
        this.costOfUnit = costOfUnit;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getIsVegetarian() {
        return isVegetarian;
    }

    public void setIsVegetarian(String isVegetarian) {
        this.isVegetarian = isVegetarian;
    }
}
