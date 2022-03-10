package com.bookcompany.builder.service;

import com.bookcompany.builder.io.FileInputOutput;
import com.bookcompany.builder.model.Directory;
import com.bookcompany.builder.model.Ingredient;
import com.bookcompany.builder.model.Recipe;
import com.bookcompany.builder.repository.Repository;
import com.bookcompany.builder.repository.RepositoryImpl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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
                    System.out.println("Ingredient:" + items[i] + " is not found.");
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
            return;
        } else {
            List<String> listRecipesByName = new ArrayList<>();
            for (Recipe recipe : listOfRecipes.read())
                listRecipesByName.add(recipe.getName());
            Collections.sort(listRecipesByName);
            for (String str : listRecipesByName)
                System.out.println(str);
        }
    }
}
