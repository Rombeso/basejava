package com.urise.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) {
        String filePath = "./src";
        printAllFiles(new File(filePath));
//        lesson8();
    }

    public static void printAllFiles(File dir) {

        if (dir.isDirectory() && dir.list() == null) {
            return;
        }

        if (dir.isDirectory()) {
            File[] listFiles = dir.listFiles();
            for (File file : listFiles) {
                if (file.isDirectory()) {
                    printAllFiles(file);
                } else {
                    System.out.println(file.getName());
                }
            }
        }
    }

    public static void lesson8 () {
        System.out.println("=== lesson8 ===");
        String filePath = ".\\.gitignore";

        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File dir = new File("./src/com/urise/webapp");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("=== lesson8 ===");
    }
}
