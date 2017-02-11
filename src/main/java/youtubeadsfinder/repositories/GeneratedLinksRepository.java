package youtubeadsfinder.repositories;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Created by romanb on 2/11/17.
 */
@Repository
public class GeneratedLinksRepository {

    private ArrayList<String> links;

    public GeneratedLinksRepository(){

        links= new ArrayList<>();
    }

    public int size(){
        return links.size();
    }

    public void add (String url){
        links.add(url);
    }

    public String get(int i ){
        return links.get(i);
    }

    public ArrayList<String> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<String> links) {
        this.links = links;
    }
}
