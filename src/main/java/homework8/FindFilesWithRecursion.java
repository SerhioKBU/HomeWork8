package homework8;

import java.io.File;
import java.io.IOException;

public class FindFilesWithRecursion {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) {
        findFiles(new File("F:"));
    }

    public static void findFiles(File filePath) {
        if (!filePath.exists()) {
            System.out.println(ANSI_RED + "Wrong path to Directory. Try again!"+ ANSI_RESET);
            return;
        }
        try {
        File[] files = filePath.listFiles(way -> way
                .getName()
                .endsWith("java") || way.isDirectory());
            assert files != null;
            for (File file : files) {
            if (file.isDirectory()) {
                findFiles(file);
            } else {
                System.out.println("Java file's path: " + file.getCanonicalPath());
            }
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


