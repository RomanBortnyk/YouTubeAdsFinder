package youtubeadsfinder;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import youtubeadsfinder.generators.VideoLinksGenerator;
import youtubeadsfinder.repositories.FoundVideoLinksRepository;
import youtubeadsfinder.repositories.GeneratedLinksRepository;
import youtubeadsfinder.tools.StringToFileAppender;

/**
 * Created by romanb on 2/10/17.
 */
@Component
public class SeleniumSearcher {

    @Autowired
    private VideoLinksGenerator generator;
    @Autowired
    private StringToFileAppender appender;
    @Autowired
    private FoundVideoLinksRepository alreadyFoundLinksrepository;
    @Autowired
    private GeneratedLinksRepository generatedRepository;

    @Autowired
    private WebDriver driver;

    @Autowired
    private WebDriverWait wait;

    private boolean needToGenerateMoreLinks(int currentCount){

        return (generatedRepository.size() - currentCount) < 10;

    }

//    public SeleniumSearcher(){
//
//        generator.generate(10);
//    }

    public void search(int numberOfLinksToExplore){


        int count = 0;
        while (count < numberOfLinksToExplore){

            //10 is more probable number of urls
            // that algorithm can generate per one request to YouTube server
            if (needToGenerateMoreLinks(count)) generator.generate(10);

            String currentLink = generatedRepository.get(count);
            String log = count + ". video " +currentLink +" ";

            try {

                driver.get(currentLink);

                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[data-sessionlink='feature=player-title']")));

                String href = element.getAttribute("href");

                if ( href != null && href.equals(currentLink)){

                    System.out.println(log + "has no ad");

                }else {

                    if (href == null){
                        System.out.println(log + "ad is present but link is abcent");

                    }else{

                        if (alreadyFoundLinksrepository.isExist(href)){

                            System.out.println(log + "ad link: duplicate" );
                        }else {

                            System.out.println(log + "ad link: " + href);
                            alreadyFoundLinksrepository.add(href);
                            appender.appendString(href);
                        }

                    }
                }

            }catch (NoSuchElementException e){
                e.printStackTrace();
                System.out.println(log+ "element not found");

            }catch (TimeoutException e){

                System.out.println(log + "timeout" );
            }

            count++;
        }

        //Close the browser
        driver.quit();

    }

}
