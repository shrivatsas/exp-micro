package io.helidon.examples.quickstart.se;

import com.google.gson.Gson;
import io.helidon.webserver.Routing;
import io.helidon.webserver.ServerRequest;
import io.helidon.webserver.ServerResponse;
import io.helidon.webserver.Service;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.net.http.HttpClient;

public class QuoteService implements Service {

    private static final JsonBuilderFactory JSON = Json.createBuilderFactory(Collections.emptyMap());
    private final HttpClient client = HttpClient.newHttpClient();
    private final HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://api.quotable.io/random"))
            .build();

    @Override
    public void update(Routing.Rules rules) {
        rules
            .get("/", this::getRandomQuoteHandler);
    }

    private void getRandomQuoteHandler(ServerRequest serverRequest, ServerResponse serverResponse) {
        String res = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();
        Quote q = new Gson().fromJson(res, Quote.class);
        sendResponse(serverResponse, q.content);
    }

    private void sendResponse(ServerResponse response, String msg) {
        JsonObject returnObject = JSON.createObjectBuilder()
                .add("message", msg)
                .build();
        response.send(returnObject);
    }
}
