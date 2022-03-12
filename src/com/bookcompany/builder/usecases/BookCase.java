/**
 *
 *
 * @author Evanthios Papadopoulos
 * @since 08-Mar-22
 */

package com.bookcompany.builder.usecases;

import com.bookcompany.builder.model.Ingredient;
import com.bookcompany.builder.model.Recipe;
import com.bookcompany.builder.repository.*;
import com.bookcompany.builder.service.IngredientServiceImpl;
import com.bookcompany.builder.service.RecipeServiceImpl;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Stream;

public class BookCase {
    public static final Scanner scanner = new Scanner(System.in);
    public static Repository<Ingredient> listOfIngredients = null;
    public static Repository<Recipe> listOfRecipes = null;

    public static void userInterface() throws IOException {
        // Starting of BookCompany interface.
        System.out.println("--- Welcome to BookCompany ---\n");

        // Ingredients are loaded.
        listOfIngredients = new IngredientServiceImpl().loadIngredients();
        System.out.println("Ingredients are loaded...\n");

        // User options selection.
        String userChoose = null;
        while (true) {
            do {
                System.out.println("\nChoose how you want to start:");
                System.out.println("\tPress a: Load a recipe file.");
                System.out.println("\tPress b: Clear recipes from memory.");
                System.out.println("\tPress c: List of all ingredients.");
                System.out.println("\tPress d: List of all vegetarian ingredients.");
                System.out.println("\tPress e: List all recipes, ordered by their name.");
                System.out.println("\tPress f: List all recipes, ordered by preparation time.");
                System.out.println("\tPress g: List of all vegetarian recipes.");
                System.out.println("\tPress h: Search for recipes with a user ingredient.");
                System.out.println("\tPress i: Search for recipes with a range of calories.");
//            System.out.println("\tPress j: To exit.");
//            System.out.println("\tPress k: To exit.");
//            System.out.println("\tPress l: To exit.");
//            System.out.println("\tPress m: To exit.");
//            System.out.println("\tPress n: To exit.");
                System.out.println("\tPress -1: To exit.");
                try {
                    userChoose = scanner.nextLine();
                } catch (InputMismatchException ex) {
                    String badInput = scanner.next();
                    System.out.println("Bad input: '" + badInput + "' Please try again.\n");
                }
            } while (!Objects.equals(userChoose, "a") && !Objects.equals(userChoose, "b") && !Objects.equals(userChoose, "c")
                    && !Objects.equals(userChoose, "d") && !Objects.equals(userChoose, "e") && !Objects.equals(userChoose, "f")
                    && !Objects.equals(userChoose, "g") && !Objects.equals(userChoose, "h") && !Objects.equals(userChoose, "i")
                    && !Objects.equals(userChoose, "j") && !Objects.equals(userChoose, "k") && !Objects.equals(userChoose, "l")
                    && !Objects.equals(userChoose, "m") && !Objects.equals(userChoose, "n") && !Objects.equals(userChoose, "-1"));

            // Based on the user choose we start generating or exiting the program.
            switch (userChoose) {
                case "a" -> {
                    userChoose = null;
                    do {
                        System.out.println("Give recipe name.");
                        try {
                            userChoose = scanner.nextLine();
                            if (!Objects.equals(userChoose, "egg_recipes") && !Objects.equals(userChoose, "my_recipes"))
                                System.err.println("The recipe you asked does not exist.");
                        } catch (InputMismatchException ex) {
                            String badInput = scanner.next();
                            System.out.println("Bad input: '" + badInput + "' Please try again.\n");
                        }
                    } while (!Objects.equals(userChoose, "egg_recipes") && !Objects.equals(userChoose, "my_recipes"));
                    listOfRecipes = new RecipeServiceImpl().loadRecipes(userChoose, listOfIngredients);
                    System.out.println("Recipes added successfully.");
                }
                case "b" -> {
                    listOfRecipes = null;
                    System.out.println("Recipes cleared.");
                }
                case "c" -> new IngredientServiceImpl().printListOfIngredients(listOfIngredients);
                case "d" -> new IngredientServiceImpl().printListOfVegetarianIngredients(listOfIngredients);
                case "e" -> new RecipeServiceImpl().printRecipesByName(listOfRecipes);
                case "f" -> new RecipeServiceImpl().printRecipesByPreparationTime(listOfRecipes);
                case "g" -> new RecipeServiceImpl().printVegetarianRecipes(listOfRecipes);
                case "h" -> {
                    userChoose = null;
                    do {
                        System.out.println("Give ingredient name.");
                        try {
                            userChoose = scanner.nextLine();
                        } catch (InputMismatchException ex) {
                            String badInput = scanner.next();
                            System.out.println("Bad input: '" + badInput + "' Please try again.\n");
                        }
                    } while (!Objects.equals(userChoose, "lemon") && !Objects.equals(userChoose, "egg")
                            && !Objects.equals(userChoose, "beef") && !Objects.equals(userChoose, "potato")
                            && !Objects.equals(userChoose, "flour") && !Objects.equals(userChoose, "tomato")
                            && !Objects.equals(userChoose, "cheese") && !Objects.equals(userChoose, "ham")
                            && !Objects.equals(userChoose, "bread") && !Objects.equals(userChoose, "mushroom")
                            && !Objects.equals(userChoose, "pasta"));
                    new RecipeServiceImpl().returnedRecipes(listOfRecipes, listOfIngredients, userChoose);
                }
                case "i" -> {
                    String fromCalories = null;
                    do {
                        System.out.println("Give from calories above 0.");
                        try {
                            fromCalories = scanner.nextLine();
                        } catch (InputMismatchException ex) {
                            String badInput = scanner.next();
                            System.out.println("Bad input: '" + badInput + "' Please try again.\n");
                        }
                    } while (Integer.parseInt(Objects.requireNonNull(fromCalories)) < 0);
                    String toCalories = null;
                    do {
                        System.out.println("Give to calories above 0.");
                        try {
                            toCalories = scanner.nextLine();
                        } catch (InputMismatchException ex) {
                            String badInput = scanner.next();
                            System.out.println("Bad input: '" + badInput + "' Please try again.\n");
                        }
                    } while (Integer.parseInt(Objects.requireNonNull(toCalories)) < 0);
                    new RecipeServiceImpl().searchRangeOfCalories(listOfRecipes, listOfIngredients, Integer.parseInt(fromCalories), Integer.parseInt(toCalories));
                }
                case "-1" -> {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
            }
        }
    }

}
