/**
 * The BookCase class is the main use-case of the BookCompany and implements a user interface alongside with the chooses
 * of the user for the requested actions of the functionalities of the classes IngredientServiceImpl and RecipeServiceImpl.
 *
 * @author Evanthios Papadopoulos
 * @since 08-Mar-22
 */

package com.bookcompany.builder.usecases;

import com.bookcompany.builder.io.FileInputOutput;
import com.bookcompany.builder.model.Ingredient;
import com.bookcompany.builder.model.Recipe;
import com.bookcompany.builder.repository.Repository;
import com.bookcompany.builder.service.IngredientServiceImpl;
import com.bookcompany.builder.service.RecipeServiceImpl;

import java.util.*;

public class BookCase {
    public static final Scanner scanner = new Scanner(System.in);
    public static Repository<Ingredient> listOfIngredients = null;
    public static Repository<Recipe> listOfRecipes = null;
    public static List<String> listToOutput = null;

    /**
     * UserInterface() method, will start the program with a welcome note of the BookCompany, and then it will load the
     * Ingredients from the file. After that it will wait fot the user to select the preferred action of functionality or
     * exit the program. The program will never stop if the user not exit it by selecting the "-1" option, and so the user
     * can use all the functionalities of the program in one go.
     *
     */
    public static void userInterface() {
        // Starting of BookCompany interface.
        System.out.println("--- Welcome to BookCompany ---\n");

        // Ingredients are loaded.
        listOfIngredients = new IngredientServiceImpl().loadIngredients();
        System.out.println("Ingredients are loaded...\n");

        // User options selection from the below functionalities.
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
                System.out.println("\tPress j: Save search and give a name.");
                System.out.println("\tPress k: Enter the names of recipes to show the ingredients needed for them.");
                System.out.println("\tPress l: What recipes can i make with the current ingredients?");
                System.out.println("\tPress m: Add new recipe.");
                System.out.println("\tPress n: Ingredients available, what recipes can you make?");
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

                // Asks the user to give the program a recipe name. The loop exists only for an egg_recipes or my_recipes input.
                // After that the program carries the recipe name and initiates the specific function which is to load the recipe.
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

                // Clears the memory of list of recipes so the user can start again.
                case "b" -> {
                    listOfRecipes = null;
                    System.out.println("Recipes cleared.");
                }

                // Initiates the specific function.
                case "c" -> new IngredientServiceImpl().printListOfIngredients(listOfIngredients);

                // Initiates the specific function.
                case "d" -> new IngredientServiceImpl().printListOfVegetarianIngredients(listOfIngredients);

                // Initiates the specific function.
                case "e" -> new RecipeServiceImpl().printRecipesByName(listOfRecipes);

                // Initiates the specific function.
                case "f" -> new RecipeServiceImpl().printRecipesByPreparationTime(listOfRecipes);

                // Initiates the specific function. But before that we clean the list of output because we want to keep the last
                // function that the user asked for action.
                case "g" -> {
                    listToOutput = null;
                    listToOutput = new RecipeServiceImpl().printVegetarianRecipes(listOfRecipes);
                }

                // The program clean the list of output because we want to keep the last function that the user asked
                // for action. Then we ask the user for an ingredient name, it only exits the loop if the ingredient belong
                // to the list of ingredients. And then we initiate the specific function.
                case "h" -> {
                    listToOutput = null;
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
                    listToOutput = new RecipeServiceImpl().returnedRecipes(listOfRecipes, listOfIngredients, userChoose);
                }

                // The program clean the list of output because we want to keep the last function that the user asked
                // for action. Then we ask the user to give us a range of calories first the "from" and then the "to".
                // And then we initiate the specific function.
                case "i" -> {
                    listToOutput = null;
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
                    listToOutput = new RecipeServiceImpl().searchRangeOfCalories(listOfRecipes, listOfIngredients, Integer.parseInt(fromCalories), Integer.parseInt(toCalories));
                }

                // We ask from the user a file name, and then we initiate the specific function.
                case "j" -> {
                    System.out.println("Give file name.");
                    userChoose = scanner.nextLine();
                    new FileInputOutput().writeFile(listToOutput, userChoose);
                }

                // Asking the user for a number of recipes he/she want to check for ingredients needed. After that we ask
                // for the recipe names. And then we initiate the specific function.
                case "k" -> {
                    System.out.println("How many recipes you want to give?");
                    userChoose = scanner.nextLine();
                    List<Recipe> listWithRecipes = new ArrayList<>();
                    int recipesToGive = Integer.parseInt(userChoose);
                    for (int i = 0; i < recipesToGive; i++) {
                        System.out.println("Give recipe name.");
                        userChoose = scanner.nextLine();
                        for (Recipe recipe : listOfRecipes.read())
                            if (Objects.equals(recipe.getName(), userChoose))
                                listWithRecipes.add(recipe);
                    }
                    new RecipeServiceImpl().listIngredientsForRecipes(listWithRecipes, listOfIngredients);
                }

                // Initiates the specific function.
                case "l" -> new IngredientServiceImpl().printListOfRecipesWithAvailableIngredients(listOfIngredients, listOfRecipes);

                // Asking the user of a new recipe as is on the files given to us. And we create the recipe and add it to the
                // current list of recipes. If an ingredient does not exist we don't add the recipe.
                case "m" -> {
                    // Asking the recipe from the user and give them some directions.
                    System.out.println("Give new recipe in one line.");
                    System.out.println("First type the name of the recipe, then the preparation time and last the ingredients.");
                    userChoose = scanner.nextLine();
                    String[] items = userChoose.split(",");

                    // Creating a list of ingredient names in order to check the user recipe->list of ingredients later on.
                    List<String> listIngredients = new ArrayList<>();
                    for (Ingredient ingredient : listOfIngredients.read())
                        listIngredients.add(ingredient.getName());

                    boolean ingredientNotExists = false;

                    // Creating the recipe of the user, checking if the ingredients that he/she gave for the recipe exist.
                    List<Ingredient> allIngredients = new ArrayList<>();
                    Recipe recipe = new Recipe();
                    recipe.setName(items[0]);
                    recipe.setPreparationTime(Integer.parseInt(items[1]));
                    for (int i = 2; i < items.length; i++) {

                        // Checking ingredients from our list.
                        if (!listIngredients.contains(items[i])) {
                            System.err.println("Ingredient:" + items[i] + " is not found.");
                            ingredientNotExists = true;
                        } else {
                            // Adding ingredients to the list of ingredients in order to add this list to the recipe later.
                            for (Ingredient ingredient : listOfIngredients.read())
                                if (Objects.equals(ingredient.getName(), items[i]))
                                    allIngredients.add(ingredient);
                        }
                    }

                    // If all ingredients found then we add the recipe to the list of recipes.
                    if (!ingredientNotExists) {
                        recipe.setListOfIngredients(allIngredients);
                        listOfRecipes.create(recipe);
                    }

                }

                // Asking the user how many ingredients he/she want to add to the function, then we ask to name the specific
                // ingredients one by one, and we add them to a list of ingredients after checking if the ingredient name
                // that the user gave the program exists to our repository or ingredients. If something does not exist
                // we let the user know it and print zero recipes found. Else the case "n" will move on to the function.
                case "n" -> {
                    // Printing to the user how many ingredients in order to ask specific number of ingredients later on.
                    System.out.println("How many ingredients do you have?");
                    userChoose = scanner.nextLine();

                    // A list of ingredients created in order to pass it to the function later on.
                    List<Ingredient> listWithIngredients = new ArrayList<>();
                    int countOfIngredients = Integer.parseInt(userChoose);
                    boolean notExists = false;

                    // Asking the user the names of the ingredient to check if they exist and add them to the list.
                    for (int i = 0; i < countOfIngredients; i++) {
                        System.out.println("Give ingredient name.");
                        userChoose = scanner.nextLine();
                        notExists = false;

                        // Checking if the ingredients exists.
                        for (Ingredient ingredient : listOfIngredients.read())
                            if (Objects.equals(ingredient.getName(), userChoose)){
                                listWithIngredients.add(ingredient);
                            } else {
                                notExists = true;
                            }
                    }

                    // If even on ingredient does not exist we let the user know it, and then we exit.
                    if (!notExists){
                        System.out.println("Some ingredients does not exists.");
                        System.out.println("Zero recipes found.");
                    } else {
                        // If ingredients exists we move on with our function and program.
                        new IngredientServiceImpl().printListOfRecipesWithGivenIngredients(listWithIngredients, listOfRecipes);
                    }
                }

                // Exiting the loops and the terminating the program.
                case "-1" -> {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
            }
        }
    }

}
