package AgoraBot.client;

import org.apache.http.impl.client.CloseableHttpClient;

public interface HttpClientService {
    MyHttpClientBuilder makeHttpClientBuilder();
    CloseableHttpClient makeHttpClient();
}
