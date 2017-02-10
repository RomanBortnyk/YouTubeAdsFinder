package youtubescrapper;

import java.util.ArrayList;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws Exception {

        VideoLinksGenerator linksGenerator = new VideoLinksGenerator(10);
        ArrayList<String> links = linksGenerator.generate();

        VideoToFileWriter writer = new VideoToFileWriter();

        Parser parser = new Parser();


        ArrayList<Video> videos = new ArrayList<Video>();

        links.forEach(link -> { videos.add( new Video(link) ); });

//        videos.forEach(video -> {
//
//            parser.parse(video);
////            parser.getAdLink(video);
//            System.out.println(video.log());
//
//        });

        String parsed = parser.unescapeString("https:\\/\\/pubads.g.doubleclick.net\\/gampad\\/ads?ad_rule=0\\u0026ciu_szs=300x250,300x60\\u0026env=vp\\u0026gdfp_req=1\\u0026iu=\\/4061\\/com.ytpwatch.entertainment\\/3\\/GLIUBC6OD2CTJGCIJ5L2A547RI\\u0026loeid=9433221,9451825,9457141,9457494\\u0026osd=2\\u0026output=xml_vast3\\u0026scor=1\\u0026scp=av%3D1%26kpeid%3DMtFAi84ehTSYSE9XoHefig%26kpid%3D3%26kvid%3DsJR9QjezRGg%26mpvid%3DMVc2xlwKmOPe3T3R%26ssl%3D1%26afv%3D1%26dbp%3DChZHeFR3MEtMbThCdHpTRm5Gb01pZ1ZBEAEgASgA%26dc_yt%3D1%26excl_cat%3D3%26k2%3D3,16,36,184,358,1048%26k5%3D3_16_36_184_358_1048%26kga%3D-1%26kgg%3D-1%26kgpt%3D1%26klg%3Den%26kmyd%3Dwatch-longform-ad%26ko%3Dp%26kr%3DF%26ktype%3Dgptc%26kvlg%3Den%26kvz%3D204%26nlfb%3D1%26p13_rm%3D1%26pos%3Dpre%26yt3pav%3D1%26ytcat%3D24%26ytdevice%3D1%26ytdevicever%3D1.20170206%26ytexp%3D9410705,9419451,9422596,9428398,9431012,9433221,9434289,9434905,9439580,9439880,9441146,9443443,9446054,9447913,9449006,9451372,9451825,9455154,9456628,9457114,9457360,9457494,9457617,9458240,9459030,9459181,9461302,9461498,9461536,9462253,9462280,9462437%26yt_ec%3D0%26yt_ec2%3D0%26yt_vrallowed%3D1%26yt_viral%3D1\\u0026sdki=18803DED\\u0026sz=480x360%7C480x361%7C480x70\\u0026unviewed_position_start=1\\u0026vid=sJR9QjezRGg\\u0026vpos=preroll\\u0026ytdevice=1\\u0026yt_pt=APb3F2-gbMQ_EdLTom4AOihtT8NoILqGhAvnZZliuk7a1w_m8BchYsPciG5Na025ENqtnts3CqngmhgT3XsY3M5eeErBo-rmjAIZQuNIlcPXntnAvHqoiITZ5qmo4F1877Rin_u3FOjFRlCQmD9npkDQZ5WrcUgJUMGZaJRC5p_r-8rBizLa77oDSkLc3WhUXW-Nvm_2Fn0HYLD2G07YsGjv3hO0GLd6CdDcOWafAjYDvbGSdVGhu3lhi9rvXOGNF-KGxYNazNGeI09cGNOJrWyF8OzGBffavuF97W90gA");
        System.out.println(parsed);



    }
}
