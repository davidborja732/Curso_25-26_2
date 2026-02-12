import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class ApiService implements Runnable {

    private static final HttpClient client = HttpClient.newHttpClient();
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {

        System.out.println("\n=== API SERVICE ===");
        System.out.println("Usando URL fija: https://jsonplaceholder.typicode.com/users");

        callApi("https://jsonplaceholder.typicode.com/users");
    }

    public void callApi(String url) {
        try {
            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());

            parseUsers(res.body());

        } catch (Exception e) {
            System.err.println("Error API: " + e.getMessage());
        }
    }

    private void parseUsers(String json) {
        json = json.replace("\n", "").replace("\r", "");

        String[] users = json.split("\\},\\s*\\{");

        System.out.println("\n=== LISTA DE USUARIOS ===");

        for (String user : users) {
            String id = extract(user, "\"id\":", ",");
            String name = extract(user, "\"name\":\"", "\"");
            String email = extract(user, "\"email\":\"", "\"");

            System.out.println("ID: " + id);
            System.out.println("Nombre: " + name);
            System.out.println("Email: " + email);
            System.out.println("---------------------------");
        }
    }

    private String extract(String text, String start, String end) {
        try {
            int i = text.indexOf(start);
            if (i < 0) return "N/A";
            i += start.length();
            int j = text.indexOf(end, i);
            if (j < 0) return "N/A";
            return text.substring(i, j);
        } catch (Exception e) {
            return "N/A";
        }
    }
}
