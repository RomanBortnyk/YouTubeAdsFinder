package youtubeadsfinder;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import youtubeadsfinder.generators.SearchQueriesGenerator;
import youtubeadsfinder.repositories.FoundVideoLinksRepository;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Random;

/**
 * Created by romanb on 2/11/17.
 */
@Configuration
@ComponentScan(basePackages = {"youtubeadsfinder"})

public class AppConfig {


    @Bean
    public Proxy parserProxy(){

//        return new Proxy(Proxy.Type.HTTP, new InetSocketAddress("96.239.193.243",8080) );

        return null;
    }

    @Bean
    public SearchQueriesGenerator searchQueriesGenerator(){

        return new SearchQueriesGenerator(3);
    }

    @Bean
    public Random random(){

        return new Random();
    }

    @Bean
    public FoundVideoLinksRepository foundVideoLinksRepository (){

        return new FoundVideoLinksRepository("/home/romanb/Desktop/AdLinksReserve");
    }

    @Bean
    public StringToFileAppender stringToFileAppender(){

        return new StringToFileAppender("AdLinks");
    }

    @Bean
    public WebDriverWait webDriverWait(){

        return new WebDriverWait(chromeDriver(), 6);
    }

    @Bean
    public WebDriver chromeDriver(){

        System.setProperty("webdriver.chrome.driver", "chromedriver");

        return new ChromeDriver(proxyCapabilities());
    }

    @Bean
    public DesiredCapabilities proxyCapabilities() {
        org.openqa.selenium.Proxy proxy = new org.openqa.selenium.Proxy();
        proxy.setProxyType(org.openqa.selenium.Proxy.ProxyType.MANUAL);

        String proxyString = "217.33.216.114:8080";

        //uk 3s/video
        proxy.setHttpProxy(proxyString);
        proxy.setSslProxy(proxyString);
//
//         7s/video
//        proxy.setHttpProxy("103.196.182.118:28425");
//        proxy.setSslProxy("103.196.182.118:28425");

        // 4-5s/video also good proxy
//        proxy.setHttpProxy("35.162.177.140:8083");
//        proxy.setSslProxy("35.162.177.140:8083");


        DesiredCapabilities capability = new DesiredCapabilities();

        capability.setCapability(CapabilityType.PROXY, proxy);
        capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);


        return capability;

    }


}
