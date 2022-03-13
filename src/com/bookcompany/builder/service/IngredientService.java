/**
 * The ingredientService interface, is created in order to have the available services that are related to ingredients.
 *
 * @author Evanthios Papadopoulos
 * @since 12-Mar-22
 */

package com.bookcompany.builder.service;

import com.bookcompany.builder.model.Ingredient;
import com.bookcompany.builder.model.Recipe;
import com.bookcompany.builder.repository.Repository;

import java.io.IOException;
import java.util.List;

public interface IngredientService {
    Repository<Ingredient> loadIngredients() throws IOException;
    void printListOfIngredients(Repository<Ingredient> listOfIngredients);
    void printListOfVegetarianIngredients(Repository<Ingredient> listOfIngredients);
    void printListOfRecipesWithAvailableIngredients(Repository<Ingredient> listOfIngredients, Repository<Recipe> listOfRecipes);
    void printListOfRecipesWithGivenIngredients(List<Ingredient> listOfIngredients, Repository<Recipe> listOfRecipes);
}
