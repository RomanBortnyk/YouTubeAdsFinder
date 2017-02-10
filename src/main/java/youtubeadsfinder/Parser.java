package youtubeadsfinder;

import com.sun.xml.internal.ws.server.ServerRtException;
import org.apache.commons.lang.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by romanb on 2/7/17.
 */
public class Parser {

    private Proxy proxy;

    public Parser() {

//        this.proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("96.239.193.243"	,8080) );
//        this.proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("158.69.201.48",80) );

    }

    public void parse(Video video) {

        getScriptStringWithAdTag(video);
        createDoubleClickLink(video);

//        requestAdSystem(video);

    }

    public int getVideoDurationInSeconds(Video video){


        Document document = getDocument(video.getVideoUrl());

        Elements durationSpan = document.getElementsByAttributeValue("itemprop", "duration");

        String stringDuration = durationSpan.attr("content");

        String time [] = stringDuration.split("M");

        int minutes = Integer.parseInt(time[0].substring(2,time[0].length()) );

        int seconds = Integer.parseInt( time[1].substring(0,time[1].length()-1) );

        return minutes*60 + seconds;
    }




    public String getAdLink (Video video){

        Document document = getDocument(video.getVideoUrl());

        Element reference = document.getElementById("bottom-bar-link");

        String link = null;

        if (reference !=null){
            link = reference.attr("href");
            System.out.println("video " +video.getVideoUrl()+ "has ad link: " +link );
        }else {
            System.out.println("video " +video.getVideoUrl()+ " link not found" );
        }

        System.out.println(" ");

        return link;
    }

    private void getScriptStringWithAdTag(Video video) {

        Document doc = getDocument(video.getVideoUrl());

        if ( doc == null) return;

        // div where target script is located
        Element div = doc.getElementById("player-mole-container");

        Node targetScript = div.childNode(7);

        video.setTargetScriptString(targetScript.toString());

    }

    private void createDoubleClickLink(Video video) {

        // "ad_tag":"some ad_tag info"
        Pattern p = Pattern.compile("(?is)\"ad_tag\":\"(.+?)\"");

        if (video.getTargetScriptString() == null) return;

        Matcher matcher = p.matcher(video.getTargetScriptString());

        String doubleclickLink = null;

        if (matcher.find()) {

            doubleclickLink = matcher.group(1);

            video.setDoubleclickLink(unescapeString(doubleclickLink));

        } else {
//            System.out.println("video " + videoUrl + " has not ad_tag");
        }

        p = Pattern.compile("(?is)\"tpas_partner_id\":\"(.+?)\"");
        matcher = p.matcher(video.getTargetScriptString());

        if (matcher.find()) {
            System.out.println("video: " + video.getVideoUrl() + "partner id: " + matcher.group(1));
        }
    }

    private void requestAdSystem(Video video) {


        if (video.getDoubleclickLink() == null) return;

        Document xml = getDocument(video.getDoubleclickLink());

        if (xml == null) return;

        Element adSystem = xml.getElementsByTag("AdSystem").first();

        if (adSystem == null) {
            System.out.println("video adSystemTag is null");
            return;
        }

        video.setAdSystem(adSystem.text());


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

    public String unescapeString(String incoming) {
        return StringEscapeUtils.unescapeJava(incoming);
    }

}
