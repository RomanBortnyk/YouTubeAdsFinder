package youtubeadsfinder;

import org.junit.Test;
import youtubeadsfinder.generators.VideoLinksGenerator;

/**
 * Created by romanb on 2/7/17.
 */
public class VideoLinksGeneratorTest {

    @Test
    public void generate() throws Exception {



        VideoLinksGenerator linksGenerator = new VideoLinksGenerator();

        linksGenerator.generate(100);

        System.out.println();

    }

}