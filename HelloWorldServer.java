import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;

public class HelloWorldServer {
    public static void main(String[] args) throws IOException {
        // Create an HTTP server instance
        HttpServer server = HttpServer.create(new java.net.InetSocketAddress(9000), 0);

        // Create a context for the root path "/"
        server.createContext("/", (HttpExchange exchange) -> {
            String response = "Hello, World!";

            // Set the response headers
            exchange.getResponseHeaders().set("Content-Type", "text/plain");
            exchange.sendResponseHeaders(200, response.length());

            // Send the response
            OutputStream outputStream = exchange.getResponseBody();
            outputStream.write(response.getBytes());
            outputStream.close();
        });

        // Start the server
        server.start();

        System.out.println("Server started on port 9000");

        // Create a JAR file of the program
        String jarName = "HelloWorldServer.jar";
        String mainClass = HelloWorldServer.class.getName();

        try {
            Runtime.getRuntime().exec(String.format("jar cfe %s %s %s.class", jarName, mainClass.replace(".", "/"), mainClass));
            System.out.println("JAR file created: " + jarName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

