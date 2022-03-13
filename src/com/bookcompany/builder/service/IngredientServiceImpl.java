/**
 * The ingredientServiceImpl class, is created in order to have the available services that are related to ingredients.
 *
 * @author Evanthios Papadopoulos
 * @since 12-Mar-22
 */

package com.bookcompany.builder.service;

import com.bookcompany.builder.io.FileInputOutput;
import com.bookcompany.builder.model.Directory;
import com.bookcompany.builder.model.Ingredient;
import com.bookcompany.builder.model.Recipe;
import com.bookcompany.builder.repository.Repository;
import com.bookcompany.builder.repository.RepositoryImpl;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Objects;

public class IngredientServiceImpl implements IngredientService{

    /**
     * LoadIngredients() method returns a repository of ingredients from file.
     *
     * @return a repository of ingredients.
     */
    @Override
    public Repository<Ingredient> loadIngredients() {
        // Takes the content of file from filePath.
        List<String> lines = FileInputOutput.readFile(Directory.FILE_DIRECTORY.getPath()
                + "data" + File.separator + "input" + File.separator + "ingredients.txt");

        // Create a repository of ingredients.
        Repository<Ingredient> listOfIngredients = new RepositoryImpl<>();

        // Iterating through content of file and making ingredients and at the end adding them to the repository.
        for (String line: lines){
            String[] items = line.split(",");
            Ingredient ingredient = new Ingredient();
            ingredient.setName(items[0]);
            ingredient.setCostOfUnit(new BigDecimal(items[1]));
            ingredient.setCalories(Integer.parseInt(items[2]));
            ingredient.setIsVegetarian(items[3]);
            listOfIngredients.create(ingredient);
        }
        return listOfIngredients;
    }

    /**
     * PrintListOfIngredients() method prints to user the available list of ingredients.
     *
     * @param listOfIngredients requires a list of ingredients.
     */
    @Override
    public void printListOfIngredients(Repository<Ingredient> listOfIngredients) {
        // Simple iteration of ingredients of names and printing them out.
        for (Ingredient ingredient : listOfIngredients.read())
            System.out.print(ingredient.getName() + ", ");
        System.out.println();
    }

    /**
     * PrintListOfVegetarianIngredients() method prints to user the available list of vegetarian ingredients.
     *
     * @param listOfIngredients requires a list of ingredients.
     */
    @Override
    public void printListOfVegetarianIngredients(Repository<Ingredient> listOfIngredients) {
        // Iteration of ingredients, selecting the vegetarian ingredients to print out to the user.
        for (Ingredient ingredient : listOfIngredients.read())
            if (Objects.equals(ingredient.getIsVegetarian(), "v"))
                System.out.print(ingredient.getName() + ", ");
        System.out.println();
    }

    /**
     * PrintListOfRecipesWithAvailableIngredients() method prints to user the available recipes that can be made with the
     * available ingredients.
     *
     * @param listOfIngredients requires a list of ingredients.
     * @param listOfRecipes requires a list of recipes.
     */
    @Override
    public void printListOfRecipesWithAvailableIngredients(Repository<Ingredient> listOfIngredients, Repository<Recipe> listOfRecipes) {
        // Creating a hashtable of string and a list of recipes.
        Hashtable<String, List<Recipe>> hashtable = new Hashtable<>();

        // Iteration of ingredients, recipes and recipes->list of ingredients in order to find recipes for the ingredients.
        for (Ingredient ingredient : listOfIngredients.read()) {
            List<Recipe> listRecipes = new ArrayList<>();
            for (Recipe recipe : listOfRecipes.read()) {
                for (Ingredient ingredientInRecipe : recipe.getListOfIngredients()) {
                    if (ingredient == ingredientInRecipe) {
                        listRecipes.add(recipe);
                        break;
                    }
                }
            }
            hashtable.put(ingredient.getName(), listRecipes);
        }

        // Iteration of hashtable, in order to print the available recipes for each ingredient.
        hashtable.forEach((k, v) -> {
            StringBuilder str = new StringBuilder();
            for (Recipe recipe : v)
                str.append(recipe.getName()).append(", ");
            System.out.println(k + " -> " + str);
        });
    }

    /**
     * PrintListOfRecipesWithGivenIngredients() method prints to the user the recipes he/she can make with their available
     * ingredients.
     *
     * @param listOfIngredients is a list of ingredients.
     * @param listOfRecipes requires a list of recipes.
     */
    @Override
    public void printListOfRecipesWithGivenIngredients(List<Ingredient> listOfIngredients, Repository<Recipe> listOfRecipes) {
        // Creation of a list of strings with recipes names.
        List<String> listWithRecipes = new ArrayList<>();
        boolean allOk;

        // Iteration of recipes and recipes-> list of ingredients, if the ingredient is not found in the recipe then we
        // move on to the next recipe. If ingredient exists in the recipe then we add it to the list.
        for (Recipe recipe : listOfRecipes.read()) {
            allOk = true;
            for (Ingredient ingredient : recipe.getListOfIngredients()) {

                // If ingredient is not in the list of ingredients then we change the flag to false and move to the next recipe.
                if (!listOfIngredients.contains(ingredient)) {
                    allOk = false;
                    break;
                }
            }

            // If flag is still true then we add it to the list.
            if (allOk)
                listWithRecipes.add(recipe.getName());
        }

        // Prints out the list of recipes.
        listWithRecipes.forEach(System.out::println);
    }
}
