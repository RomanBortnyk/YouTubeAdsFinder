package youtubeadsfinder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by romanb on 2/7/17.
 */
public class VideoLinksGenerator {

    private SearchQueriesGenerator queriesGenerator;
    private String pattern = "https://www.youtube.com";

    public VideoLinksGenerator() {


        queriesGenerator = new SearchQueriesGenerator(3);

    }

    public ArrayList<String> generate (int numberOfLinks){

        ArrayList<String> randomLinks = new ArrayList<>();

        boolean isEnough = false;

        while ( !isEnough ){

            try {

                Document doc = Jsoup.connect(  queriesGenerator.generate() ).get();
                Elements results = doc.getElementsByClass("yt-lockup-title");

                for (int i = 0; i <results.size() ; i++) {

                    Node node = results.get(i).childNode(0);
                    String href = node.attr("href");

                    // ignore such long links /watch?v=ndIJWhbH-4w&list=PLVhmFI7EhEPNVWVxQW5XDJ0QDf4kZrRPQ
                    // add only with 11 charachters after equals
                    // like that
                    // /watch?v=hnBN2wgi8Ak
                    if (href.charAt(1) == 'w' && href.length() == 20) {

                        if (randomLinks.size() == numberOfLinks){
                            isEnough = true;
                            break;
                        }
                        randomLinks.add(pattern+href);
                        System.out.println(pattern+href);
                    }
                }

            }catch (IOException e){
                System.out.println("Error while generate random links");
                e.printStackTrace();
            }

        }

        return randomLinks;
    }




}
