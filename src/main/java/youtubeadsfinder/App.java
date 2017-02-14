package youtubeadsfinder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        SeleniumSearcher searcher = (SeleniumSearcher) applicationContext.getBean("seleniumSearcher");

        searcher.search(100000);


    }
}
