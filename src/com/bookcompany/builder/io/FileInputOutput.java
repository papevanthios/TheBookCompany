/**
 * The FileInputOutput class is created in order to make two methods the readFile and writeFile in order to access the
 * file and to write to a file.
 *
 * @author Evanthios Papadopoulos
 * @since 12-Mar-22
 */

package com.bookcompany.builder.io;

import com.bookcompany.builder.model.Directory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileInputOutput {

    /**
     * ReadFile() it reads the file on the path of filePath parameter and returns is as a list.
     *
     * @param filePath is the path of the file
     * @return a list of strings that represents the content of the file.
     */
    public static List<String> readFile(String filePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))) {
            while (true) {
                String s = in.readLine();
                if (s == null)
                    break;
                lines.add(s);
            }
        }
        catch (IOException ioException) {
            System.out.println("Something went wrong during reading.");
        }
        return lines;
    }

    /**
     * WriteFile() it writes the file on the path of the filePath parameter.
     *
     * @param listToOutput is a list of string to write to a file.
     * @param filePath is the path of the file to be created.
     */
    public void writeFile(List<String> listToOutput, String filePath) {
        String path = Directory.FILE_DIRECTORY.getPath() + "data" + File.separator + "output" + File.separator + filePath;
        if (new File(path).exists())
            System.err.println("Filename already exists.");
        else {
            try (PrintWriter pw = new PrintWriter(new FileOutputStream(path))) {
                for (String str : listToOutput)
                    pw.println(str);
            } catch (IOException ioException) {
                System.out.println("Something went wrong during writing.");
                System.out.println(ioException.getMessage());
                System.out.println(ioException.getClass());
            }
        }
    }
}
