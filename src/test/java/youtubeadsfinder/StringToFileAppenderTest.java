package youtubeadsfinder;

import org.junit.Test;
import youtubeadsfinder.tools.StringToFileAppender;

/**
 * Created by romanb on 2/10/17.
 */
public class StringToFileAppenderTest {
    @Test
    public void appendString() throws Exception {

        StringToFileAppender appender = new StringToFileAppender("AdLinks");

        appender.appendString("string");

    }
}