package com.bookcompany.builder.service;

import com.bookcompany.builder.model.Ingredient;
import com.bookcompany.builder.repository.Repository;

import java.io.IOException;

public interface IngredientService {
    Repository<Ingredient> loadIngredients() throws IOException;
    void printListOfIngredients(Repository<Ingredient> listOfIngredients);
    void printListOfVegetarianIngredients(Repository<Ingredient> listOfIngredients);
}
