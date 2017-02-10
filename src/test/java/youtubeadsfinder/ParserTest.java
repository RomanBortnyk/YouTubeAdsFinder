package youtubeadsfinder;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.print.Doc;
import java.util.ArrayList;

/**
 * Created by romanb on 2/7/17.
 */
public class ParserTest {

    private static Parser parser;

    @BeforeClass
    public static void initialize (){

        parser = new Parser();

    }

    @Test
    public void parse(){

//        VideoLinksGenerator linksGenerator = new VideoLinksGenerator(10);
//        ArrayList<String> links = linksGenerator.generate();

        VideoToFileWriter writer = new VideoToFileWriter();

        Video video1 = new Video("https://www.youtube.com/watch?v=x559i-sS9oM");
        parser.parse(video1);

        System.out.println();

    }

    @Test
    public void getVideoDurationInSeconds (){

        VideoLinkRepository repository = new VideoLinkRepository("AdLinks");

        int commonDuration= 0;

       for (String link: repository.getLinks()){

           commonDuration+= parser.getVideoDurationInSeconds(link);
           System.out.println("current duration " +(double)commonDuration/3600 + " hours");
       }

        System.out.println((double)commonDuration/3600+" hours");


    }
}
