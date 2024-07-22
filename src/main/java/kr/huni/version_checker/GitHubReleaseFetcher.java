package kr.huni.version_checker;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GitHubReleaseFetcher {

  public static final String GITHUB_LATEST_RELEASE_URL =
      "https://github.com/PENEKhun/baekjoon-java-starter/releases/latest";

  private static final String GITHUB_API_LATEST_RELEASE_URL =
      "https://api.github.com/repos/PENEKhun/baekjoon-java-starter/releases/latest";

  public Version fetchLatestRelease() throws IOException, InterruptedException {
    String jsonResponse = sendGetRequest(GITHUB_API_LATEST_RELEASE_URL);
    return parseRelease(jsonResponse);
  }

  private String sendGetRequest(String url) throws IOException, InterruptedException {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .GET()
        .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    return response.body();
  }

  private Version parseRelease(String jsonResponse) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    JsonNode rootNode = mapper.readTree(jsonResponse);
    String tagName = rootNode.path("tag_name").asText();
    return new Version(tagName);
  }
}
