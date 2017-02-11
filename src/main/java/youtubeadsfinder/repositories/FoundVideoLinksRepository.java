package youtubeadsfinder.repositories;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by romanb on 2/10/17.
 */


public class FoundVideoLinksRepository {

    private ArrayList<String> links;
    private String pathToFile;

    public FoundVideoLinksRepository(String pathToFile){

        this.pathToFile = pathToFile;
        links = new ArrayList<>();

        readLinks();
    }

    public void add (String string){

        links.add(string);
    }

    private void readLinks (){


        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {

            String currentLine;

            while ((currentLine = br.readLine()) != null) {
                links.add(currentLine);
//                System.out.println(currentLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public boolean isExist (String link) {

        return links.contains(link);
    }

    public ArrayList<String> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<String> links) {
        this.links = links;
    }

    public String getPathToFile() {
        return pathToFile;
    }

    public void setPathToFile(String pathToFile) {
        this.pathToFile = pathToFile;
    }
}

