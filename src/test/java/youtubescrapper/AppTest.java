package youtubescrapper;


import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void VideoToFileWriterTest (){

        VideoToFileWriter writer = new VideoToFileWriter();
        Parser parser = new Parser();

        Video video = new Video("https://www.youtube.com/watch?v=GWBncy3rydw");
        parser.parse(video);

        writer.write(video);
    }

}


