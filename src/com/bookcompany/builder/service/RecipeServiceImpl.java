package com.bookcompany.builder.service;

import com.bookcompany.builder.io.FileInputOutput;
import com.bookcompany.builder.model.Directory;
import com.bookcompany.builder.model.Ingredient;
import com.bookcompany.builder.model.Recipe;
import com.bookcompany.builder.repository.Repository;
import com.bookcompany.builder.repository.RepositoryImpl;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class RecipeServiceImpl implements RecipeService{
    @Override
    public Repository<Recipe> loadRecipes(String recipeName, Repository<Ingredient> listOfIngredients) throws IOException {
        List<String> lines = FileInputOutput.readFile(Directory.FILE_DIRECTORY.getPath()
                + "data" + File.separator + "input" + File.separator + recipeName + ".txt");
        List<String> listIngredients = new ArrayList<>();
        for (Ingredient ingredient : listOfIngredients.read())
            listIngredients.add(ingredient.getName());
        Repository<Recipe> listOfRecipes = new RepositoryImpl<>();
        for (String line: lines) {
            boolean ingredientNotExists = false;
            List<Ingredient> allIngredients = new ArrayList<>();
            String[] items = line.split(",");
            Recipe recipe = new Recipe();
            recipe.setName(items[0]);
            recipe.setPreparationTime(Integer.parseInt(items[1]));
            for (int i = 2; i < items.length; i++) {
                if (!listIngredients.contains(items[i])) {
                    System.err.println("Ingredient:" + items[i] + " is not found.");
                    ingredientNotExists = true;
                } else {
                for (Ingredient ingredient : listOfIngredients.read())
                    if (Objects.equals(ingredient.getName(), items[i]))
                        allIngredients.add(ingredient);
                }
            }
            if (!ingredientNotExists) {
                recipe.setListOfIngredients(allIngredients);
                listOfRecipes.create(recipe);
            }
        }
        return listOfRecipes;
    }

    @Override
    public void printRecipesByName(Repository<Recipe> listOfRecipes) {
        if (listOfRecipes == null) {
            System.out.println("There are no recipes.");
        } else {
            List<String> listRecipesByName = new ArrayList<>();
            for (Recipe recipe : listOfRecipes.read())
                listRecipesByName.add(recipe.getName());
            Collections.sort(listRecipesByName);
            for (String str : listRecipesByName)
                System.out.println(str);
        }
    }

    @Override
    public void printRecipesByPreparationTime(Repository<Recipe> listOfRecipes) {
        if (listOfRecipes == null) {
            System.out.println("There are no recipes.");
        } else {
//            List<String> listRecipesByPreparationTime = new ArrayList<>();
            List<Integer> listOfPreparationTimes = new ArrayList<>();
            for (Recipe recipe : listOfRecipes.read())
                listOfPreparationTimes.add(recipe.getPreparationTime());
            int minPreparationTime = Collections.min(listOfPreparationTimes);
            int maxPreparationTime = Collections.max(listOfPreparationTimes);
            for (int i = minPreparationTime; i <= maxPreparationTime; i++) {
                for (Recipe recipe : listOfRecipes.read())
                    if (recipe.getPreparationTime() == i)
                        System.out.println(recipe.getName());
            }
        }
    }

    @Override
    public List<String> printVegetarianRecipes(Repository<Recipe> listOfRecipes) {
        if (listOfRecipes == null) {
            System.out.println("There are no recipes.");
            return null;
        } else {
            boolean isVegetarian;
            List<String> listToOutput = new ArrayList<>();
            for (Recipe recipe : listOfRecipes.read()) {
                isVegetarian = true;
                for (Ingredient ingredient : recipe.getListOfIngredients()) {
                    if (Objects.equals(ingredient.getIsVegetarian(), "n")) {
                        isVegetarian = false;
                        break;
                    }
                }

                if (isVegetarian) {
                    listToOutput.add(recipe.getName());
                    System.out.println(recipe.getName());
                }
                else{
                    System.out.println("There are no vegetarian recipes loaded.");
                    break;
                }
            }
            return listToOutput;
        }

    }

    @Override
    public List<String> returnedRecipes(Repository<Recipe> listOfRecipes, Repository<Ingredient> listOfIngredients, String ingredient) {
        if (listOfRecipes == null) {
            System.out.println("There are no recipes.");
            return null;
        } else {
            boolean isFound = false;
            for (Ingredient ingredientFromList : listOfIngredients.read())
                if (Objects.equals(ingredientFromList.getName(), ingredient)) {
                    isFound = true;
                    break;
                }

            if (!isFound){
                System.out.println("0 recipes.");
                return null;
            } else {
                List<String> listToOutput = new ArrayList<>();
                for (Recipe recipe : listOfRecipes.read())
                    for (Ingredient ingredientOfRecipe : recipe.getListOfIngredients())
                        if (Objects.equals(ingredientOfRecipe.getName(), ingredient)){
                            listToOutput.add(recipe.getName());
                            System.out.println(recipe.getName());
                            break;
                        }
                return listToOutput;
            }
        }
    }

    @Override
    public List<String> searchRangeOfCalories(Repository<Recipe> listOfRecipes, Repository<Ingredient> listOfIngredients, int fromCalories, int toCalories) {
        if (listOfRecipes == null) {
            System.out.println("There are no recipes.");
            return null;
        } else {
            if (fromCalories < 0 && toCalories < 0 || fromCalories > toCalories) {
                System.out.println("Calories must be above 0 or to calories must not exceed from calories.");
                return null;
            }
            else {
                Hashtable<Recipe, Integer> recipesCalories = new Hashtable<>();
                int calories;
                for (Recipe recipe : listOfRecipes.read()) {
                    calories = 0;
                    for (Ingredient ingredient : recipe.getListOfIngredients())
                        calories += ingredient.getCalories();
                    recipesCalories.put(recipe, calories);
                }
                List<String> listToOutput = new ArrayList<>();
                recipesCalories.forEach((k, v) -> {
                    if (v >= fromCalories && v <= toCalories) {
                        listToOutput.add(k.getName());
                        System.out.println(k.getName());
                    }
                });
                return listToOutput;
            }
        }
    }

    @Override
    public void listIngredientsForRecipes(List<Recipe> listWithRecipes, Repository<Ingredient> listOfIngredients) {
        Hashtable<String, Integer> listWithIngredients = new Hashtable<>();
        for (Recipe recipe : listWithRecipes)
            for (Ingredient ingredient : recipe.getListOfIngredients())
                if (listWithIngredients.containsKey(ingredient.getName())){
                    int value = listWithIngredients.get(ingredient.getName());
                    listWithIngredients.put(ingredient.getName(), value + 1);
                } else {
                    listWithIngredients.put(ingredient.getName(), 1);
                }
        listWithIngredients.forEach((k, v) -> System.out.println(k + " = " + v));
    }
}
