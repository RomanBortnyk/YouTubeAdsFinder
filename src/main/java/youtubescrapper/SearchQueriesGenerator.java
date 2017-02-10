package youtubescrapper;

import java.util.Random;

/**
 * Created by romanb on 2/7/17.
 */
public class SearchQueriesGenerator {

    private String searchUrl = "https://www.youtube.com/results?search_query=";
    private Random random;
    private int length;

    public SearchQueriesGenerator(int length) {
        this.random = new Random();
        this.length = length;
    }

    public String generate (){

        char [] chars = new char[length];

        for (int i = 0; i < length ; i++) {
            chars[i] = (char)(random.nextInt(26) + 'a');
        }

        return searchUrl + new String(chars);
    }

}
