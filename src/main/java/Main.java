import AgoraBot.CommandLine;
import AgoraBot.CommandLineModule;
import com.google.inject.Guice;
import AgoraBot.client.HttpClientModule;
import AgoraBot.cookie.CookieModule;
import AgoraBot.crawler.CrawlerModule;

public class Main {
    public static void main(String[] args) {
        Guice.createInjector(new CommandLineModule(), new HttpClientModule(), new CookieModule(), new CrawlerModule())
                .getInstance(CommandLine.class)
                .start();
    }
}
