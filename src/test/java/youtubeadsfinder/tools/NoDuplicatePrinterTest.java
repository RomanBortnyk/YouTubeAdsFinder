package youtubeadsfinder.tools;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by romanb on 2/12/17.
 */
public class NoDuplicatePrinterTest {
    @Test
    public void printWithoutDuplicates() throws Exception {

        NoDuplicatePrinter printer = new NoDuplicatePrinter();

        printer.printWithoutDuplicates("/home/romanb/Desktop/withoutduplicates");
    }

}