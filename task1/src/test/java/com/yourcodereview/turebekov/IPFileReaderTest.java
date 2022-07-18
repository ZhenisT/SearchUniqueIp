package com.yourcodereview.turebekov;

import com.yourcodereview.turebekov.IPFileReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;

class IPFileReaderTest {
    @Test
    public void IllegalArgumentExceptionWhenPathIsNull() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                IPFileReader.readFile(null, null));

        Assertions.assertEquals("Path cannot be null", exception.getMessage());
    }

    @Test
    public void AccessDeniedException() {
        AccessDeniedException exception = Assertions.assertThrows(AccessDeniedException.class, () ->
                IPFileReader.readFile(Path.of("null"), null));

        Assertions.assertEquals("File access denied", exception.getMessage());
    }

    @Test
    public void findUniqueIPsFromFile() throws IOException {
        Path path = Path.of("src/main/resources/ip_adresses");

        var result = IPFileReader.readFile(path, null);
        Assertions.assertEquals(5, result);
    }

}
