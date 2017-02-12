package youtubeadsfinder.tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;



/**
 * Created by romanb on 2/12/17.
 */
public class NoDuplicatePrinter {

//    private List<String> listWithDuplicates;
    private Set<String> setWithoutDuplicates;

    public NoDuplicatePrinter(){

        setWithoutDuplicates = new HashSet<>();
    }

    public void printWithoutDuplicates(String pathToFile){


        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {

            String currentLine;

            while ((currentLine = br.readLine()) != null) {
                setWithoutDuplicates.add(currentLine);
//                links.add(currentLine);
//                System.out.println(currentLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        printSet(setWithoutDuplicates);

    }

    private <T> void printSet (Set<T> s){

        Iterator<T> iterator  = s.iterator();

        int count=1;
        while (iterator.hasNext()){
//            System.out.println(count +". "+iterator.next());
            System.out.println(iterator.next());
            count++;
        }

    }

}
