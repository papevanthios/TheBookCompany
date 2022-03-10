package com.bookcompany.builder.service;

import com.bookcompany.builder.io.FileInputOutput;
import com.bookcompany.builder.model.Directory;
import com.bookcompany.builder.model.Ingredient;
import com.bookcompany.builder.repository.Repository;
import com.bookcompany.builder.repository.RepositoryImpl;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class IngredientServiceImpl implements IngredientService{

    @Override
    public Repository<Ingredient> loadIngredients() throws IOException {
        List<String> lines = FileInputOutput.readFile(Directory.FILE_DIRECTORY.getPath()
                + "data" + File.separator + "input" + File.separator + "ingredients.txt");
        Repository<Ingredient> listOfIngredients = new RepositoryImpl<>();
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

    @Override
    public void printListOfIngredients(Repository<Ingredient> listOfIngredients) {
        for (Ingredient ingredient : listOfIngredients.read())
            System.out.print(ingredient.getName() + ", ");
        System.out.println();
    }

    @Override
    public void printListOfVegetarianIngredients(Repository<Ingredient> listOfIngredients) {
        for (Ingredient ingredient : listOfIngredients.read())
            if (Objects.equals(ingredient.getIsVegetarian(), "v"))
                System.out.print(ingredient.getName() + ", ");
        System.out.println();
    }
}