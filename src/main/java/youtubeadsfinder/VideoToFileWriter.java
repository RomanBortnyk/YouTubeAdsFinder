package youtubeadsfinder;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by romanb on 2/7/17.
 */
public class VideoToFileWriter {


    public void write (Video video){

        try (PrintWriter writer = new PrintWriter("file.txt", "UTF-8") ){

            writer.println("video url: " + video.getVideoUrl() + "\n");

            writer.println("doubleclickLink: " + video.getDoubleclickLink() +  "\n");
            writer.println("ad system: " + video.getAdSystem() + "\n");
            writer.println("script: " + video.getTargetScriptString() + "\n");

        }catch (IOException e){
            e.printStackTrace();
        }






    }
}
