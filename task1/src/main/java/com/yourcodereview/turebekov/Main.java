package com.yourcodereview.turebekov;

import java.io.IOException;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        String filename = args.length > 0 ? args[0] : "";
        Path path = Path.of(filename);

        UniqueIPNumberFinder uniqueIPNumberFinder = new UniqueIPNumberFinder();
        try {
            IPFileReader.getStreamLinesFromFile(path,null).parallel().forEach(uniqueIPNumberFinder::markIPAddress);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("File contains " + uniqueIPNumberFinder.getUniqueIpNumber() + " unique IPs");
    }
}










































