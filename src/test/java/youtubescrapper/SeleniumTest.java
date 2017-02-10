package youtubescrapper;

import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by romanb on 2/9/17.
 */
public class SeleniumTest {

    @Test
    public void test () throws Exception{

        VideoLinksGenerator generator = new VideoLinksGenerator(500);

        ArrayList<String> links = generator.generate();

        System.setProperty("webdriver.chrome.driver", "chromedriver");

        WebDriver driver = new ChromeDriver(addProxyCapabilities());
//        WebDriver driver = new ChromeDriver();

        //
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

//                WebElement element1 = driver.findElement(By.cssSelector("span[aria-label='Subscribe']"));
//                String ariaLabel = element1.getAttribute("aria-label");


                if ( href != null && href.equals(currentLink)){

                    System.out.println(log + "ad is abcent");
//                    System.out.println(href);
//                    System.out.println( i + ". video " +currentLink+" ad link not found");

                }else {

                    if (href == null){
                        System.out.println(log + "ad is present but link is abcent");

                    }else{

                        System.out.println(log + "ad link: " + href);
                    }


                }

//                System.out.println(counter + ". video " +link + "has link: " + ariaLabel);


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



    public static DesiredCapabilities addProxyCapabilities() {
        Proxy proxy = new Proxy();
        proxy.setProxyType(Proxy.ProxyType.MANUAL);

        proxy.setHttpProxy("217.33.216.114:8080");
        proxy.setSslProxy("217.33.216.114:8080");


//        proxy.setHttpProxy("158.69.201.48:80");
//        proxy.setSslProxy("158.69.201.48:80");

        DesiredCapabilities capability = new DesiredCapabilities();

        capability.setCapability(CapabilityType.PROXY, proxy);
        capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);


        return capability;

    }

}
