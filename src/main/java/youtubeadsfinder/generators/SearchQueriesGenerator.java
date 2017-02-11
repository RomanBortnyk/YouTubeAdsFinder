package youtubeadsfinder.generators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Created by romanb on 2/7/17.
 */

public class SearchQueriesGenerator {

    private String searchUrl = "https://www.youtube.com/results?search_query=";
    @Autowired
    private Random random;
    private int length;

    public SearchQueriesGenerator(int searchQueryLength) {
        this.length = searchQueryLength;
    }

    public String generate (){

        char [] chars = new char[length];

        for (int i = 0; i < length ; i++) {
            chars[i] = (char)(random.nextInt(26) + 'a');
        }

        return searchUrl + new String(chars);
    }

}
