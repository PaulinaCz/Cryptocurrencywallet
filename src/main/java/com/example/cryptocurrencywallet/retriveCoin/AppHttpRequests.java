package com.example.cryptocurrencywallet.retriveCoin;

import com.example.cryptocurrencywallet.retriveCoin.model.CryptoCurrency;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

public class AppHttpRequests {
    private static final String POSTS_API_URL = "https://api.nomics.com/v1/currencies/ticker?key=";
    private static final String KEY = "0e3cdb66fd5f50dd96f21ece8c02468d";

    public static List<CryptoCurrency> requestCoin(String name) throws IOException, InterruptedException {
        var client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(String.format(POSTS_API_URL + KEY + "&ids=%s", name)))
                .timeout(Duration.ofSeconds(10)) // HttpTimeoutException
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // parse JSON into object

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<CryptoCurrency> temp = gson.fromJson(response.body(), new TypeToken<List<CryptoCurrency>>() {
        }.getType());

        temp.forEach(System.out::println);

        return temp;
    }


    public static void main(String[] args) {

        try {
            List<CryptoCurrency> coin = requestCoin("BTC,ETH,XRP");
            coin.forEach(System.out::println);
        } catch (Exception exc) {
            exc.printStackTrace();
        }

    }
}
//<dependency>
//<groupId>com.google.code.gson</groupId>
//<artifactId>gson</artifactId>
//</dependency>
//
