package youtubeadsfinder;

import com.sun.xml.internal.ws.server.ServerRtException;
import org.apache.commons.lang.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by romanb on 2/7/17.
 */
@Component
public class Parser {

    @Autowired
    private Proxy proxy;


    public int getVideoDurationInSeconds(String url){


        Document document;
        Elements durationSpan = null;
        boolean isSpanExist = false;

        while ( !isSpanExist ){

            document = getDocument(url);
            durationSpan = document.getElementsByAttributeValue("itemprop", "duration");

            if ( !durationSpan.isEmpty() ) isSpanExist=true;
        }


        String stringDuration = durationSpan.attr("content");

        System.out.println(stringDuration);

        String time [] = stringDuration.split("M");

        int minutes = Integer.parseInt(time[0].substring(2,time[0].length()) );

        int seconds = Integer.parseInt( time[1].substring(0,time[1].length()-1) );

        return minutes*60 + seconds;
    }


    public Document getDocument(String url) {

        try {

            if (proxy == null) {

                return Jsoup
                        .connect(url)
                        .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.75 Safari/537.36") //
                        .header("Content-Language", "en-US") //
                        .timeout(5000)
                        .get();

            } else {

                return Jsoup //
                        .connect(url) //
                        .proxy(proxy) //
                        .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.75 Safari/537.36") //
                        .header("Content-Language", "en-US")
                        .timeout(5000)
                        .get();

            }

        } catch (SocketTimeoutException e) {
            System.out.println("timeout exception while get document");
        } catch (IOException e) {
            System.out.println("Error when sending request");
            e.printStackTrace();
        }

        return null;

    }

//    public String unescapeString(String incoming) {
//        return StringEscapeUtils.unescapeJava(incoming);
//    }

}
