package AgoraBot;

import AgoraBot.cookie.CookieService;
import AgoraBot.crawler.CrawlerService;
import AgoraBot.crawler.course.Course;
import com.google.inject.Inject;

import java.io.PrintStream;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.IntStream;

public class CommandLine {

    private final Scanner scanner;
    private final PrintStream out;

    private final CrawlerService crawler;
    private final CookieService login;

    private static final int SLEEP_TIME_MIN = 3;
    private static final int SLEEP_TIME = SLEEP_TIME_MIN * 60 * 1000;

    @Inject
    CommandLine(Scanner scanner,
                PrintStream out,
                CrawlerService crawler,
                CookieService login) {
        this.scanner = scanner;
        this.out = out;
        this.crawler = crawler;
        this.login = login;
    }

    public void start() {

        out.print("Username: ");
        final String user = scanner.nextLine();
        out.print("Password: ");
        final String pw = scanner.nextLine();
        login.setCredential(user, pw);

        final List<Course> crawledCourses = crawler.fetchCourses();

        out.println("Select the courses in which you want to be online [0 to stop]: ");

        int[] id = {1};
        crawledCourses.forEach(e -> {
            final String course = e.getTitle();
            final String link = e.getHref();

            out.printf("%d. %s => %s\n", id[0]++, course, link);
        });

        final Set<Integer> selected = new HashSet<>(crawledCourses.size());

        while (true) {
            int n = readInt();
            while (n < 0 || n > crawledCourses.size()) {
                out.printf("Invalid id (min: %d, max: %d)\n", 0, crawledCourses.size());
                n = readInt();
            }

            if (n == 0)
                break;

            out.println("Added " + n);
            selected.add(n);
        }

        out.println("how long do you want to stay online? (in minutes)");
        int time = readInt();
        while (time < 0 || time > 150) {
            if (time > 150)
                out.println("Così spacchi tutto");
            out.printf("Invalid id (min: %d, max: %d)\n", 0, crawledCourses.size());
            time = readInt();
        }


        while (time > 0) {
            IntStream.range(0, crawledCourses.size())
                    .filter(selected::contains)
                    .mapToObj(i -> crawledCourses.get(i - 1))
                    .forEach(course -> crawler.makeGetRequest(course.getHref()));
            try {
                Thread.sleep(SLEEP_TIME);
                time -= SLEEP_TIME_MIN;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private int readInt() {
        while (true) {
            try {
                String s = scanner.nextLine();
                return Integer.parseInt(s);
            } catch (NumberFormatException ignored) {
                out.println("Not a valid number");
            }
        }
    }
}
