package com.kevinkoleck.io.service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.kevinkoleck.io.model.DuplicateEmail;
import com.kevinkoleck.io.model.EmailAndUniqueCharacterCount;
import com.kevinkoleck.io.model.Person;
import com.kevinkoleck.io.model.UniqueCharacterCount;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.*;

/**
 * Created by kevinkoleck on 1/19/20.
 */
public class ApplicationService {

    private final String API_KEY;
    private final OkHttpClient httpClient = new OkHttpClient();
    private final static double SIMILARITY_THRESHOLD = 0.8;

    public ApplicationService(String apiKey ){
        API_KEY = apiKey;
    }

    public Optional<List<Person>> getListOfPeople() {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<EmailAndUniqueCharacterCount> getEmailAndUniqueCharacterAndCount() {
        Optional<List<Person>> personList = getListOfPeople();
        List<EmailAndUniqueCharacterCount> mapList = new ArrayList<>();

        for(Person person: personList.get()) {
            List<UniqueCharacterCount> tempUniqueCharactersAndCount = getUniqueCharactersAndCount(person.getEmailAddress());
            EmailAndUniqueCharacterCount tempEmailUniqueCharactersAndCount = new EmailAndUniqueCharacterCount(person.getEmailAddress(), tempUniqueCharactersAndCount);
            mapList.add(tempEmailUniqueCharactersAndCount);
        }
        return mapList;
    }

    public List<DuplicateEmail> getDuplicateEmail() {
        List<Person> personList = getListOfPeople().get();
        List<DuplicateEmail> duplicateEmails = new ArrayList<>();

        for (int i = 0; i < personList.size(); i++) {
            for (int j = i + 1; j < personList.size(); j++) {
                double similarity = similarity(personList.get(i).getEmailAddress(), personList.get(j).getEmailAddress());

                if (similarity >= SIMILARITY_THRESHOLD) {
                    duplicateEmails.add(new DuplicateEmail(personList.get(i).getEmailAddress(), personList.get(j).getEmailAddress(), similarity));
                }
            }
        }
        return duplicateEmails;
    }

    private List<UniqueCharacterCount> getUniqueCharactersAndCount(String string){
        List<UniqueCharacterCount> uniqueCharacterCountList = new ArrayList<>();

        string.chars().distinct().forEach(x -> {
            uniqueCharacterCountList.add(
                    new UniqueCharacterCount(
                            Character.toString((char) x),
                            Math.toIntExact(
                                    string.chars().filter(y -> y == x).count()
                            )
                    )
            );
        });
        return uniqueCharacterCountList;
    }

    //Simple implementation of the Levenshtein Edit Distance
    private double similarity(String s1, String s2) {
        String longer = s1, shorter = s2;
        if (s1.length() < s2.length()) {
            longer = s2; shorter = s1;
        }
        int longerLength = longer.length();
        if (longerLength == 0) {
            return 1.0;
        }
        return (longerLength - editDistance(longer, shorter)) / (double) longerLength;

    }

    private int editDistance(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();

        int[] costs = new int[s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            int lastValue = i;
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0)
                    costs[j] = j;
                else {
                    if (j > 0) {
                        int newValue = costs[j - 1];
                        if (s1.charAt(i - 1) != s2.charAt(j - 1))
                            newValue = Math.min(Math.min(newValue, lastValue),
                                    costs[j]) + 1;
                        costs[j - 1] = lastValue;
                        lastValue = newValue;
                    }
                }
            }
            if (i > 0)
                costs[s2.length()] = lastValue;
        }
        return costs[s2.length()];
    }

}
