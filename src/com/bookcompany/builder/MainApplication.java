/**
 * The MainApplication class starts the BookCase class from package use-cases.
 *
 * @author Evanthios Papadopoulos
 * @since 08-Mar-22
 */

package com.bookcompany.builder;

import com.bookcompany.builder.usecases.BookCase;

import java.io.IOException;

public class MainApplication {
    public static void main(String[] args) {
        BookCase.userInterface();
    }
}
