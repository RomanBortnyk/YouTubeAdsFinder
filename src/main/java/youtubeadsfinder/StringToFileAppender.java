package youtubeadsfinder;

import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by romanb on 2/10/17.
 */

public class StringToFileAppender {

    private String path;

    public StringToFileAppender(String pathToFile){
        path = pathToFile;
    }


    public void appendString(String string){

        File file = new File(path);

        try (FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            bw.write(string+"\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

