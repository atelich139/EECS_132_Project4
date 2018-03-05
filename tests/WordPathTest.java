import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * Word Path Tests
 * @author Alexander Telich
 */
public class WordPathTest {

    @Test
    public void testNumLines() throws IOException {
        WordPath wordPath = new WordPath();

        // Tests counting 0 lines
        StringReader stringReader = new StringReader("");

        assertEquals(0, wordPath.numLines(stringReader));

        // Tests counting 1 line
        StringReader stringReader2 = new StringReader("0 cat 234 23423 343");

        assertEquals(1, wordPath.numLines(stringReader2));

        // Tests counting 2 lines w/ the last line blank
        StringReader stringReader3 = new StringReader("0 cat 234 23423 343\n");

        assertEquals(1, wordPath.numLines(stringReader3));

        // Tests counting many lines
        StringReader stringReader4 = new StringReader("0 cat 234 23423 343\n1 bat 234 " +
                                                     "342 432 3\n2 test 34 233 22\n3 " +
                                                     "four 34 2345 6544");

        assertEquals(4, wordPath.numLines(stringReader4));

        // Cannot test IOException because not allow to open file in test class

    }

    @Test
    public void testGetPath() throws IOException {
        WordPath wordPath = new WordPath();

        ArrayList<WordData> testList = wordPath.makeWordArray("Length3WordIndex.txt");

        // Tests start and end at 0
        LinkedList<Integer> test1 = new LinkedList<>();
        test1.add(0);

        assertEquals(test1, wordPath.getPath(0, 0, testList));

        // Tests no path
        test1.clear();

        assertEquals(null, wordPath.getPath(380, 806, testList));

        // Tests random path
        test1.add(45);
        test1.add(9);

        assertEquals(test1.getLast().intValue(), wordPath.getPath(45, 9, testList)
                                                          .getLast()
                                                .intValue());
    }

    @Test
    public void testGetWordPath() throws Exception {
        WordPath wordPath = new WordPath();

        wordPath.getWordPath("Length3WordIndex.txt");

    }

}