package youtubeadsfinder;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by romanb on 2/10/17.
 */
public class VideoToFileWriterTest {

    @Test
    public void write() throws Exception {

        VideoToFileWriter writer = new VideoToFileWriter();
        Parser parser = new Parser();

        Video video = new Video("https://www.youtube.com/watch?v=GWBncy3rydw");
        parser.parse(video);

        writer.write(video);
    }

}