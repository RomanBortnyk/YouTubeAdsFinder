package youtubeadsfinder;

import org.junit.BeforeClass;
import org.junit.Test;
import youtubeadsfinder.repositories.FoundVideoLinksRepository;
import youtubeadsfinder.tools.Parser;

/**
 * Created by romanb on 2/7/17.
 */
public class ParserTest {

    private static Parser parser;

    @BeforeClass
    public static void initialize (){

        parser = new Parser();

    }


    @Test
    public void getVideoDurationInSeconds (){

        FoundVideoLinksRepository repository = new FoundVideoLinksRepository("AdLinks");

        int commonDuration= 0;
        int counter = 1;

       for (String link: repository.getLinks()){

           commonDuration+= parser.getVideoDurationInSeconds(link);
           System.out.println(counter +". current duration "+String.format("%.2f", (double)commonDuration/3600)+ " hours");
           counter++;
       }

//        System.out.println("Common duration: "+ String.format("%.2f", (double)commonDuration/3600) + " hours");

    }
}
