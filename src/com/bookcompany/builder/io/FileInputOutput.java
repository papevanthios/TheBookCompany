package com.bookcompany.builder.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileInputOutput {
    public static List<String> readFile(String filename) throws IOException {
        List<String> lines = new ArrayList<>();
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
        while (true) {
            String s = in.readLine();
            if (s == null)
                break;
            lines.add(s);
        }
        in.close();
        return lines;
    }

    public static void writeFile(List<String> lines, String filePath) {
        try (OutputStream os = new FileOutputStream(filePath);
             ObjectOutputStream oos = new ObjectOutputStream(os)){
//            oos.write(lines);
        } catch (IOException ioException) {
            System.out.println("Something went wrong during writing.");
            System.out.println(ioException.getMessage());
            System.out.println(ioException.getClass());
        }
    }
}
