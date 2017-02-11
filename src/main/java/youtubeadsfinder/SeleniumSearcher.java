package youtubeadsfinder;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import youtubeadsfinder.generators.VideoLinksGenerator;
import youtubeadsfinder.repositories.FoundVideoLinksRepository;

import java.util.ArrayList;

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
    private FoundVideoLinksRepository repository;

    @Autowired
    private WebDriver driver;

    @Autowired
    private WebDriverWait wait;


    public void search(int count){

        ArrayList<String> links = generator.generate(count);

        for (int i = 0; i < links.size() ; i++) {

            String currentLink = links.get(i);
            String log = i + ". video " +currentLink +" ";

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

                        if (repository.isExist(href)){

                            System.out.println(log + "ad link: duplicate" );
                        }else {

                            System.out.println(log + "ad link: " + href);
                            repository.add(href);
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
        }

        //Close the browser
        driver.quit();

    }

}
