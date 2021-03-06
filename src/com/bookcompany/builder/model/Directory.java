/**
 * The enum Directory keeps the path of our folder in order to access the files.
 *
 * @author Evanthios Papadopoulos
 * @since 12-Mar-22
 */

package com.bookcompany.builder.model;

import java.io.File;

public enum Directory {
    FILE_DIRECTORY(System.getProperty("user.home") + File.separator + "IdeaProjects" + File.separator + "TheBookCompany" + File.separator + "src" + File.separator + "com" + File.separator + "bookcompany" + File.separator + "builder" + File.separator);
    private final String path;

    Directory(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
