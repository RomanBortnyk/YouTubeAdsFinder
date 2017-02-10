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

//    String a = "6&nbsp;136&nbsp";
    public int getIntViewsNumber (String string){

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i <string.length() ; i++) {
            char curr = string.charAt(i);
            if (Character.isDigit(curr)) stringBuilder.append(curr);
        }

        if (stringBuilder.toString().equals("")){
            return 1;
        }

        return Integer.parseInt(stringBuilder.toString());
    }



    public ArrayList<String> generate (int numberOfLinks){

        ArrayList<String> randomLinks = new ArrayList<>();

        boolean isEnough = false;

        while ( !isEnough ){

            try {

                Document doc = Jsoup.connect(  queriesGenerator.generate() ).get();
                Elements results = doc.getElementsByClass("yt-lockup-content");

                Node nodeWithHref;
                Node nodeWithMetaData;
                String href;
                String viewsNumberString = null;


                for (int i = 0; i <results.size() ; i++) {

                    nodeWithHref = results.get(i).childNode(0).childNode(0);
                    href = nodeWithHref.attr("href");


                   // if href like example below it means that it's video - not a channel
                    // /watch?v=hnBN2wgi8Ak
                    if (href.charAt(1) == 'w' && href.length() == 20){

                        nodeWithMetaData = results.get(i).childNode(2);
                        if (nodeWithMetaData.childNodeSize()>=1){
                            viewsNumberString = nodeWithMetaData.childNode(0).childNode(1).toString();

                        }

                        if (randomLinks.size() == numberOfLinks){
                            isEnough = true;
                            break;
                        }

                        if ( getIntViewsNumber(viewsNumberString) >=100000){

                            randomLinks.add(pattern+href);
                            System.out.println(pattern+href);
                        }

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
