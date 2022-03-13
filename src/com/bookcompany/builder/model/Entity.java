/**
 * The entity class is the superclass for model classes Ingredient and Recipe. It keeps the name of each of them.
 *
 * @author Evanthios Papadopoulos
 * @since 12-Mar-22
 */

package com.bookcompany.builder.model;

public class Entity {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
