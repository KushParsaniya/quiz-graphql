package dev.kush.quizgraphql.service;

import dev.kush.quizgraphql.config.ApiKey;
import dev.kush.quizgraphql.model.Question;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class GenerateQuestionServiceImpl implements GenerateQuestionService {

    private static final String URL = "https://generativelanguage.googleapis.com/v1beta3/models/text-bison-001:generateText";
    private final Gson gson;
    private final ApiKey apiKeyComp;
    private final QuestionService questionService;

    @Autowired
    public GenerateQuestionServiceImpl(Gson gson, ApiKey apiKeyComp, QuestionServiceImpl questionService) {
        this.gson = gson;
        this.apiKeyComp = apiKeyComp;
        this.questionService = questionService;
    }

    @Override
    public Question generateQuestion(String language,String type) throws IOException, InterruptedException {


        final String apiKey = apiKeyComp.getApiKey(); // Replace with your actual API key

        // Construct the request body in the required JSON format
        Map<String, String> prompt = new HashMap<>();
        prompt.put("text",
                String.format("give me a multiple choice question on %s language and topic is %s ,give me answer with question, four options and answer and give me response in json format",language,type));
//                String.format("Generate a %s language multiple choice questions on %s with four options and answer and give me response in json format.",language,type));

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("prompt", prompt);
        requestBody.put("model", "text-bison-001");

        // Convert the request body to JSON
        String jsonBody = gson.toJson(requestBody);

        // Build the HTTP request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL + "?key=" + apiKey))
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody, StandardCharsets.UTF_8))
                .header("Content-Type", "application/json")
                .build();

        // Send the request and get the response
//        HttpClient client = HttpClient.newHttpClient();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Check for successful response
        if (response.statusCode() != 200) {
            throw new IOException("Error! Status code: " + response.statusCode());
        }

        JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();

        // Get the "candidates" array
        JsonArray candidates = jsonObject.getAsJsonArray("candidates");

        // Access the first element of "candidates" array
        JsonObject firstCandidate = candidates.get(0).getAsJsonObject();

        // Access the "output" field
        String output = firstCandidate.get("output").getAsString();

        // Remove the markdown syntax from the string
        String innerJsonString = output.replaceAll("```json", "").replaceAll("```", "");

        // Parse the inner JSON string to get the question
        JsonObject innerJsonObject = JsonParser.parseString(innerJsonString).getAsJsonObject();

        // Extract the field from the inner JSON object
        String question = innerJsonObject.get("question").getAsString();
//        String newQuestion = question.replace("\n", "<br/>");
        var options = innerJsonObject.get("options").getAsJsonArray();
        String answer = innerJsonObject.get("answer").getAsString();

        // save Question use questionService method and return it

//        if (question == null || options.size() < 4 || answer == null) {
//            throw new NullPointerException("Please try again");
//        }

        return questionService.addQuestion(
                language,
                question,
                options.get(0).getAsString(),
                options.get(1).getAsString(),
                options.get(2).getAsString(),
                options.get(3).getAsString(),
                answer,
                "medium"
        );

    }
}
