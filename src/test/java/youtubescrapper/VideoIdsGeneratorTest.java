package youtubescrapper;

import org.junit.Test;

/**
 * Created by romanb on 2/7/17.
 */
public class VideoIdsGeneratorTest {

    SearchQueriesGenerator searchQueriesGenerator = new SearchQueriesGenerator(3);

    @Test
    public void generateStringTest() {

        System.out.println(searchQueriesGenerator.generate());
        System.out.println(searchQueriesGenerator.generate());
        System.out.println(searchQueriesGenerator.generate());

    }


}