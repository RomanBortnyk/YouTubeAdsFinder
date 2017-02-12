package youtubeadsfinder.generators;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import youtubeadsfinder.tools.Parser;
import youtubeadsfinder.repositories.GeneratedLinksRepository;

/**
 * Created by romanb on 2/7/17.
 */
@Component
public class VideoLinksGenerator {

    @Autowired
    private SearchQueriesGenerator queriesGenerator;
    @Autowired
    private Parser parser;

    @Autowired
    private GeneratedLinksRepository repository;

    private String pattern = "https://www.youtube.com";

    public void generate (int numberOfLinks){

        boolean isEnough = false;
        int counter = 0;

        System.out.println("generation of new "+numberOfLinks+" video links...");

        while ( !isEnough ){

            try {
                Document document;
                Elements results;
                // avoid timeout exception while getDocument
                document = parser.getDocument(queriesGenerator.generate());
                if (document!=null){
                    results = document.getElementsByClass("yt-lockup-content");
                }else continue;

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

                        if (counter == numberOfLinks){
                            isEnough = true;
                            break;
                        }

                        if ( getIntViewsNumber(viewsNumberString) >=400000){
                            repository.add(pattern+href);
//                            System.out.println(counter +". "+pattern+href);
                            counter++;
                        }
                    }
                }

            }catch (IndexOutOfBoundsException e){
                System.out.println("this stupid index of bound again");
            }
        }
    }

    //    String a = "6&nbsp;136&nbsp";
    private long getIntViewsNumber (String string){

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i <string.length() ; i++) {
            char curr = string.charAt(i);
            if (Character.isDigit(curr)) stringBuilder.append(curr);
        }

        if (stringBuilder.toString().equals("") ){
            return 1;
        }

        return Long.parseLong(stringBuilder.toString());
    }
}


