package com.yourcodereview.turebekov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;

class IPFileReaderTest {
    @Test
    public void IllegalArgumentExceptionWhenPathIsNull() {
        IllegalArgumentException exception =
                Assertions.assertThrows(IllegalArgumentException.class, () ->
                IPFileReader.getStreamLinesFromFile(null, null));

        Assertions.assertEquals("Path cannot be null", exception.getMessage());
    }

    @Test
    public void AccessDeniedException() {
        AccessDeniedException exception =
                Assertions.assertThrows(AccessDeniedException.class, () ->
                IPFileReader.getStreamLinesFromFile(Path.of("null"), null));

        Assertions.assertEquals("File access denied", exception.getMessage());
    }

    @Test
    public void findUniqueIPsFromFile() throws IOException {
        Path path = Path.of("src/test/java/resources/ip_adresses");

        UniqueIPNumberFinder uniqueIPNumberFinder = new UniqueIPNumberFinder();

        IPFileReader.getStreamLinesFromFile(path, null)
                .parallel()
                .forEach(uniqueIPNumberFinder::markIPAddress);

        Assertions.assertEquals(5, uniqueIPNumberFinder.getUniqueIpNumber());
    }

}
