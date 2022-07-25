package com.yourcodereview.turebekov;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.stream.Stream;

public final class IPFileReader {
    private IPFileReader() {
        throw new AssertionError("Cannot be instantiated");
    }

    public static Stream<String> getStreamLinesFromFile(Path path, Charset charset) throws IOException {
        if (path == null) {
            throw new IllegalArgumentException("Path cannot be null");
        }

        if (charset == null) {
            charset = StandardCharsets.UTF_8;
        }

        if (!Files.isReadable(path)) {
            throw new AccessDeniedException("File access denied");
        }

        if (!Files.exists(path)) {
            throw new NoSuchFileException("File does not exist");
        }

        return Files.lines(path, charset);
    }
}
