package com.kevinkoleck.io.service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.kevinkoleck.io.model.Person;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Created by kevinkoleck on 1/19/20.
 */
public class ApplicationService {

    private final String API_KEY;
    private final OkHttpClient httpClient = new OkHttpClient();

    public ApplicationService(String apiKey ){
        API_KEY = apiKey;
    }

    public Optional<List<Person>> getListOfPeople() throws IOException{
        Request request = new Request.Builder()
                .url("https://api.salesloft.com/v2/people.json")
                .header("Authorization", "Bearer " + API_KEY)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            JsonElement data = JsonParser.parseString(response.body().string()).getAsJsonObject().get("data");
            Gson gson = new Gson();
            List<Person> personList = gson.fromJson(data, new TypeToken<List<Person>>(){}.getType());
            return Optional.of(personList);
        }
    }

}
