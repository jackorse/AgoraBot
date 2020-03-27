package AgoraBot.crawler;

import AgoraBot.crawler.course.Course;

import java.util.List;

public interface CrawlerService {
    List<Course> fetchCourses();
    void makeGetRequest(String courseHref);
}
