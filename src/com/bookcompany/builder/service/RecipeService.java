package com.bookcompany.builder.service;

import com.bookcompany.builder.model.Ingredient;
import com.bookcompany.builder.model.Recipe;
import com.bookcompany.builder.repository.Repository;

import java.io.IOException;

public interface RecipeService {
    Repository<Recipe> loadRecipes(String recipeName, Repository<Ingredient> listOfIngredients) throws IOException;
    void printRecipesByName(Repository<Recipe> listOfRecipes);
    void printRecipesByPreparationTime(Repository<Recipe> listOfRecipes);
    void printVegetarianRecipes(Repository<Recipe> listOfRecipes);
    void returnedRecipes(Repository<Recipe> listOfRecipes, Repository<Ingredient> listOfIngredients, String ingredient);
    void searchRangeOfCalories(Repository<Recipe> listOfRecipes, Repository<Ingredient> listOfIngredients, int fromCalories, int toCalories);



}
