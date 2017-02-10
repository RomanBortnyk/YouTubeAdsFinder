package youtubeadsfinder;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;


import static org.junit.Assert.*;

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