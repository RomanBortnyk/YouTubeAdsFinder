package youtubeadsfinder;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

/**
 * Created by romanb on 2/10/17.
 */
public class SeleniumSearcher {

    private VideoLinksGenerator generator;
    private StringToFileAppender appender;
    private VideoLinksRepository repository;

    public SeleniumSearcher (){

        generator = new VideoLinksGenerator();
        appender = new StringToFileAppender("AdLinks");
        repository = new VideoLinksRepository("/home/romanb/Desktop/AdLinksReserve");

    }

    public void search(int count){

        ArrayList<String> links = generator.generate(count);

        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver(addProxyCapabilities());
//        WebDriver driver = new ChromeDriver();

        long timeOutInSeconds = 6;

//        driver.manage().timeouts().pageLoadTimeout(timeOutInSeconds,TimeUnit.SECONDS);

        WebDriverWait wait = new WebDriverWait(driver, (int)timeOutInSeconds);

        for (int i = 0; i < links.size() ; i++) {

            String currentLink = links.get(i);
            String log = i + ". video " +currentLink +" ";

            try {

                driver.get(currentLink);

                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[data-sessionlink='feature=player-title']")));

//                WebElement element = driver.findElement(By.cssSelector("a[data-sessionlink='feature=player-title']"));

                String href = element.getAttribute("href");


                if ( href != null && href.equals(currentLink)){

                    System.out.println(log + "ad is abcent");

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

    private DesiredCapabilities addProxyCapabilities() {
        Proxy proxy = new Proxy();
        proxy.setProxyType(Proxy.ProxyType.MANUAL);

        //uk 20+ ads
        proxy.setHttpProxy("217.33.216.114:8080");
        proxy.setSslProxy("217.33.216.114:8080");

//        proxy.setHttpProxy("208.75.95.148:80");
//        proxy.setSslProxy("208.75.95.148:80");

        // us elite
//        208.75.95.148	80


        DesiredCapabilities capability = new DesiredCapabilities();

        capability.setCapability(CapabilityType.PROXY, proxy);
        capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);


        return capability;

    }
}
