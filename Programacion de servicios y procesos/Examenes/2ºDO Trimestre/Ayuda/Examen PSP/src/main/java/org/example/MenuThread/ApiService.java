package org.example.MenuThread;

import javax.json.*;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ApiService implements Runnable {

    private static final HttpClient client = HttpClient.newHttpClient();
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {

        System.out.println("\n=== API SERVICE ===");
        System.out.println("Descargando usuarios desde JSONPlaceholder...");

        List<Persona> personas = callApi("https://jsonplaceholder.typicode.com/users");

        boolean exit = false;

        while (!exit) {
            System.out.println("\n=== MENÚ API ===");
            System.out.println("1. Ver todas las personas");
            System.out.println("2. Buscar por nombre");
            System.out.println("0. Salir");
            System.out.print("Opción: ");

            String op = scanner.nextLine().trim();

            switch (op) {

                case "1" -> {
                    System.out.println("\n=== LISTA COMPLETA ===");
                    personas.forEach(System.out::println);
                }

                case "2" -> {
                    System.out.println("Introduce el nombre a buscar:");
                    String nombre = scanner.nextLine();

                    List<Persona> resultado = buscarPorNombre(personas, nombre);

                    System.out.println("\n=== RESULTADO ===");
                    resultado.forEach(System.out::println);
                }

                case "0" -> exit = true;

                default -> System.out.println("Opción no válida");
            }
        }
    }

    public List<Persona> callApi(String url) {
        try {
            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());

            return parseUsers(res.body());

        } catch (Exception e) {
            System.err.println("Error API: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private List<Persona> parseUsers(String json) {

        List<Persona> personas = new ArrayList<>();

        JsonReader reader = Json.createReader(new StringReader(json));
        JsonArray array = reader.readArray();

        for (JsonValue val : array) {

            JsonObject obj = val.asJsonObject();

            int id = obj.getInt("id");
            String name = obj.getString("name");
            String email = obj.getString("email");

            personas.add(new Persona(id, name, email));
        }

        return personas;
    }


    public List<Persona> buscarPorNombre(List<Persona> personas, String nombreBuscado) {

        List<Persona> resultado = new ArrayList<>();

        for (Persona p : personas) {
            if (p.getNombre().equalsIgnoreCase(nombreBuscado)) {
                resultado.add(p);
            }
        }

        if (resultado.isEmpty()) {
            System.out.println("No se encontro el nombre");
            return resultado;
        }

        return resultado;
    }
}
