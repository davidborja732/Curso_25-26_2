package service;

import modelo.Persona;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Collections;

public class GuardaDatos {
    public String guarda(Persona persona){
        Path path= Paths.get("archivo.txt");
        try {
            Files.write(path, Collections.singletonList(persona.toString()),
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );
            return "Texto introducido";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
