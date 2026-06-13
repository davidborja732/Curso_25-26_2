import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
public class AemetClient {
    private static final String API_KEY = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkYm9yamFtQGllc2NoLm9yZyIsImp0aSI6ImZhYjE2MTRkLWQwMTctNDUxZi1hNmE2LWQxNzcyMDY0NThhMSIsImlzcyI6IkFFTUVUIiwiaWF0IjoxNzYzMDQ1Njc5LCJ1c2VySWQiOiJmYWIxNjE0ZC1kMDE3LTQ1MWYtYTZhNi1kMTc3MjA2NDU4YTEiLCJyb2xlIjoiIn0.cUMq4Wmtc4D0wbqt6OAau5R8NslbMeWhzMP2O1hRw7Q";
    private static final String URL =
            "https://opendata.aemet.es/opendata/api/valores/climatologicos/inventarioestaciones/todasestaciones/?api_key=" + API_KEY;
    public static void main(String[] args) {
        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL))
                    .header("cache-control", "no-cache")
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            System.out.println("Código HTTP: " + response.statusCode());
            System.out.println("Cuerpo de la respuesta:");
            System.out.println(response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
