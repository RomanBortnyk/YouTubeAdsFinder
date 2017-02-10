package youtubescrapper;

import junit.framework.TestCase;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by romanb on 2/7/17.
 */
public class VideoLinksGeneratorTest {

    @Test
    public void testGetVideoIds() throws Exception {

        VideoLinksGenerator linksGenerator = new VideoLinksGenerator(100);
        linksGenerator.generate();

    }

}