//https://us.edstem.org/courses/631/discussion/187715

import java.util.*;
import java.io.*;
import java.net.*;
import java.nio.file.*;

import com.sun.net.httpserver.*;

public class Server {
    private static final String QUERY_TEMPLATE = "{\"items\":[%s]}";

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // Step 0: Initialize data for the algorithm
        ChemistryMemes meme = new ChemistryMemes();
        // Create an HttpServer instance on port 8000 accepting up to 100 concurrent connections
        HttpServer server = HttpServer.create(new InetSocketAddress(8001), 100); //8000 port number
        // Return the index.html file when the browser asks for the web app
        server.createContext("/", (HttpExchange t) -> {
            String html = Files.readString(Paths.get("index.html"));
            send(t, "text/html; charset=utf-8", html);
        });

        // written server?
        server.createContext("/endpoint2", (HttpExchange t) -> {
            String result = "Hello world!"; //take in input heeeeeeere and have an output :')))
            send(t, "text/html; charset=utf-8", result);
        });

        server.createContext("/test1", (HttpExchange t) -> {
            String s = parse("s", t.getRequestURI().getQuery().split("&"));
            if (s.equals("")) {
                send(t, "application/json", String.format(QUERY_TEMPLATE, ""));
                return;
            }
            // Step 1: Return the top 5 matches as a JSON object (dict)
            List<String> matches = meme.allMatches(s);
            if (matches.size() > 5) {
                matches = matches.subList(0, 5);
            }
            Map<String, Integer> result = new LinkedHashMap<>();
            for (String city : matches) {
                result.put(city, autocomplete.getWeight(city));
            }
            String test = json(result.entrySet());
            System.out.println(test);
            send(t, "application/json", String.format(QUERY_TEMPLATE, test));
        });

        /*
        server.createContext("/random", (HttpExchange t) -> {
            // Step 2: Remove this API endpoint
            send(t, "application/json", "{\"s\":\"" + result + "\"}");
        });
        */
        
        server.setExecutor(null);
        server.start();
    }

    private static String parse(String key, String... params) {
        for (String param : params) {
            String[] pair = param.split("=");
            if (pair.length == 2 && pair[0].equals(key)) {
                return pair[1];
            }
        }
        return "";
    }

    private static void send(HttpExchange t, String contentType, String data)
        throws IOException, UnsupportedEncodingException {
    t.getResponseHeaders().set("Content-Type", contentType);
    byte[] response = data.getBytes("UTF-8");
    t.sendResponseHeaders(200, response.length);
    try (OutputStream os = t.getResponseBody()) {
        os.write(response);
    }
    }

    private static String json(Iterable<Map.Entry<String, Integer>> matches) {
        StringBuilder results = new StringBuilder();
        for (Map.Entry<String, Integer> entry : matches) {
            if (results.length() > 0) {
                results.append(',');
            }
            // Step 1: Return the top 5 matches as a JSON object (dict)
            results.append('{');
            results.append("\"query\":\"").append(entry.getKey()).append("\",");
            results.append("\"weight\":").append(entry.getValue());
            results.append('}');
        }
        return results.toString();
    }

}