package com.yourcodereview.turebekov;

import java.io.IOException;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        String filename = args.length > 0 ? args[0] : "";
        Path path = Path.of(filename);

        var result = IPFileReader.readFile(path, null);
        System.out.println("File contains " + result + " IPs");
    }
}
