package youtubeadsfinder;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by romanb on 2/10/17.
 */
public class VideoLinkRepositoryTest {




    @Test
    public void isExist() throws Exception {

        VideoLinksRepository repository = new VideoLinksRepository("AdLinks");

        ArrayList<String> test = repository.getLinks();

        assertTrue(repository.isExist("https://www.youtube.com/watch?v=yZWeB4A9bjg"));
        assertFalse(repository.isExist("https://www.youtube.com/watch?v=yZWeB4A8bjg"));


    }

}