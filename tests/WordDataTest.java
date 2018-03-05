import org.junit.Test;
import java.util.LinkedList;
import static org.junit.Assert.*;

/**
 * Word Data Tests
 * @author Alexander Telich
 */
public class WordDataTest {

    @Test
    public void testParseWordData() throws Exception{
        WordData wordData = new WordData(0, "test", false, new LinkedList());

        // Tests input case with only Index Number
        assertEquals(40, wordData.parseWordData("40").getX());
        assertEquals(null, wordData.parseWordData("40").getS());
        assertEquals(false, wordData.parseWordData("40").getABoolean());

        // Tests input case with only Index Number and String
        assertEquals(40, wordData.parseWordData("40 cat").getX());
        assertEquals("cat", wordData.parseWordData("40 cat").getS());
        assertEquals(false, wordData.parseWordData("40 cat").getABoolean());

        // Tests input case with Index Number, String, and 1 number in Linked List
        assertEquals(40, wordData.parseWordData("40 cat 9").getX());
        assertEquals("cat", wordData.parseWordData("40 cat 9").getS());
        assertEquals(false, wordData.parseWordData("40 cat 9").getABoolean());

        LinkedList<Integer> linkedList = new LinkedList<Integer>();
        linkedList.add(9);

        assertEquals(linkedList.getFirst(), wordData.parseWordData("40 cat 9")
                                                    .getIntList().getFirst());

        // Tests input case with many numbers in Linked List
        linkedList.add(4);
        linkedList.add(9);

        assertEquals(linkedList.getFirst(), wordData.parseWordData("40 cat 9 4 9")
                                                    .getIntList().getFirst());
        assertEquals(linkedList.get(1), wordData.parseWordData("40 cat 9 4 9")
                                                    .getIntList().get(1));
        assertEquals(linkedList.getLast(), wordData.parseWordData("40 cat 9 4 9")
                                                    .getIntList().getLast());

    }

}