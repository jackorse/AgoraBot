package AgoraBot.crawler;

import AgoraBot.crawler.course.Course;
import AgoraBot.crawler.course.CourseFactory;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class CrawlerModule extends AbstractModule {
    @Override
    protected void configure() {
        super.configure();


        bindConstant().annotatedWith(Domain.class).to("agora.ismonnet");

        bind(CrawlerService.class).to(Crawler.class);

        install(new FactoryModuleBuilder()
                .implement(Course.class, Course.class)
                .build(CourseFactory.class));
    }
}
