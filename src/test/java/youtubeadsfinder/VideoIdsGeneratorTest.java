package youtubeadsfinder;

import org.junit.Test;
import youtubeadsfinder.generators.SearchQueriesGenerator;

/**
 * Created by romanb on 2/7/17.
 */
public class VideoIdsGeneratorTest {

    SearchQueriesGenerator searchQueriesGenerator = new SearchQueriesGenerator(3);

    @Test
    public void generate() {

        System.out.println(searchQueriesGenerator.generate());
        System.out.println(searchQueriesGenerator.generate());
        System.out.println(searchQueriesGenerator.generate());

    }


}