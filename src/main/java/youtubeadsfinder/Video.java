package youtubeadsfinder;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by romanb on 2/7/17.
 */
public class Video {

    private String videoUrl;
    private String doubleclickLink;
    private String adUrl;
    private String adSystem;
    private String targetScriptString;
    private int durationInSeconds;


    public String getTargetScriptString() {
        return targetScriptString;
    }

    public void setTargetScriptString(String targetScriptString) {
        this.targetScriptString = targetScriptString;
    }

    public Video(String videoUrl) {

        this.videoUrl = videoUrl;

    }

    public String log (){

        return "video " + videoUrl + " has ad_system: "+ adSystem + " doubleclickLink: " + doubleclickLink;
    }


    public boolean hasDoubleclickLink() {

        return doubleclickLink != null;

    }


    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getDoubleclickLink() {
        return doubleclickLink;
    }

    public void setDoubleclickLink(String doubleclickLink) {
        this.doubleclickLink = doubleclickLink;
    }

    public String getAdUrl() {
        return adUrl;
    }

    public void setAdUrl(String adUrl) {
        this.adUrl = adUrl;
    }

    public String getAdSystem() {
        return adSystem;
    }

    public void setAdSystem(String adSystem) {
        this.adSystem = adSystem;
    }
}
