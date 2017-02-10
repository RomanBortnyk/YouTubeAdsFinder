package youtubeadsfinder;

import org.junit.Test;

import javax.print.Doc;
import java.util.ArrayList;

/**
 * Created by romanb on 2/7/17.
 */
public class ParserTest {

    @Test
    public void parse(){

//        VideoLinksGenerator linksGenerator = new VideoLinksGenerator(10);
//        ArrayList<String> links = linksGenerator.generate();

        VideoToFileWriter writer = new VideoToFileWriter();

        Parser parser = new Parser();

        Video video1 = new Video("https://www.youtube.com/watch?v=x559i-sS9oM");
        parser.parse(video1);

        System.out.println();

    }
}
