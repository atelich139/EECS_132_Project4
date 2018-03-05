import java.util.LinkedList;
import java.util.Scanner;

/**
 * WordData class is to parse Word Data and create WordData Objects
 * @author Alexander Telich
 */
public class WordData {
    /**
     * Instance field declarations
     */
    private int x;
    private String s;
    private boolean aBoolean;
    public LinkedList intList;

    /**
     * Constructor declaration
     * @param x an int, x
     * @param s a String, s
     * @param aBoolean a boolean
     * @param intList a LinkedList containing integers
     */
    public WordData(int x, String s, boolean aBoolean, LinkedList intList) {
        this.x = x;
        this.s = s;
        this.aBoolean = aBoolean;
        this.intList = intList;
    }

    /**
     * Getter and setter methods
     */
    public int getX() {
        return x;
    }
    public String getS() {
        return s;
    }
    public boolean getABoolean() {
        return aBoolean;
    }
    public LinkedList getIntList() {
        return intList;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setS(String s) {
        this.s = s;
    }
    public void setaBoolean(boolean aBoolean) {
        this.aBoolean = aBoolean;
    }
    public void setIntList(LinkedList intList) {
        this.intList = intList;
    }

    /**
     * Method declaration for 'parseWordData'
     * @param inputString the String inputted to be parsed
     * @return a WordData object populated from the inputted String
     * @throws Exception
     */
    public WordData parseWordData(String inputString) throws Exception {
        // Variable for index number
        int indexNum;

        // String for word in line
        String word;

        // Creates linked list of ints
        LinkedList<Integer> outLL = new LinkedList<>();

        // Creates the Scanner object w/ a space as the delimiter
        try(Scanner scanner = new Scanner(inputString).useDelimiter("\\s* \\s*")) {
            // Puts index number in 'indexNum' (assumes index number will always exist)
            indexNum = scanner.nextInt();

            // Puts String in word if String exists
            if (scanner.hasNext()) {word = scanner.next();} else word = null;

            // Precondition: scanner has next integer to put in Linked List
            while (scanner.hasNextInt()) {
                outLL.add(scanner.nextInt());
            }

            // Returns populated WordData object
            return new WordData(indexNum, word, false, outLL);

        }catch (Exception e) {throw e;}

    }

}
