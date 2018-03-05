import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author Alexander Telich
 */
public class WordPath {
    private static String[] args;

    /**
     * Method 'numLines' counts the number of lines in a file
     *
     * @param reader a file that contains a certain number of lines
     * @return an int that represents the number of lines in the file
     * @throws IOException if there is an I/O problem
     */
    static int numLines(Reader reader) throws IOException {
        // Creates BufferedReader
        BufferedReader br = new BufferedReader(reader);

        // Creates counter variable and initializes at 0
        int counter = 0;

        try {
            // While loop increases the counter by 1 for each line in the file
            while (br.readLine() != null) {
                counter++;
            }
        } catch (IOException e) { throw e; }
        return counter;
    }

    /**
     * This method makes an ArrayList containing WordData objects
     *
     * @param fileName a String that is the name of the file
     * @return ArrayList containing WordData objects made by method 'parseWordData'
     */
    static ArrayList<WordData> makeWordArray(String fileName) throws IOException {

        try {
            // Creates new WordData object
            WordData wordData = new WordData(0, null, false, null);

            // Creates new FileReader from inputted file name String
            FileReader fr = new FileReader(fileName);

            // Creates variable for amount of lines
            int numOfLines = numLines(fr);

            // Creates new ArrayList that's capacity is equal to number of lines in file
            ArrayList<WordData> wordDataArrayList = new ArrayList<>(numOfLines);

            // Closes FileReader
            fr.close();

            // Creates new BufferedReader from FileReader(filename)
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            // Precondition: numOfLines != i
            for (int i = 0; i < numOfLines; i++) {
                // adds WordData object parsed from String br.readLine() to ArrayList
                wordDataArrayList.add(wordData.parseWordData(br.readLine()));
            }

            // Closes BufferedReader
            br.close();

            return wordDataArrayList;

        }
        catch (IOException e) { throw e; }
        catch (Exception e) { e.printStackTrace(); }

        return null;

    }

    /**
     * Method getPath gets the path between two indexed words
     *
     * @param begin the starting index
     * @param end   the destination index
     * @param list  the entire set of indexes
     * @return a LinkedList<Integer> of the indexes of the path from start to end
     */
    public LinkedList<Integer> getPath(int begin, int end, ArrayList<WordData> list) {
        // Sets original start
        final WordData truStart = list.get(begin);

        // Sets the start to the index of desired begin
        WordData start = list.get(begin);

        // Sets the destination to the index of desired end
        WordData dest = list.get(end);

        // Creates the Linked List to return
        LinkedList<Integer> path = new LinkedList<>();

        // If start equals dest then return Linked List containing start index
        if (dest == start) {
            path.add(start.getX());
            return path;
        }

        // Gets indices of words that differ by one letter from start
        LinkedList<Integer> startList = start.getIntList();

        // Precondition: startList != null
        for (Integer integer : startList) {
            if (!list.get(integer).getABoolean()) {
                list.get(integer).setaBoolean(true);
                LinkedList<Integer> returnedPath = getPath(list.get(integer).getX(), dest
                        .getX(), list);
                if (returnedPath != null) {
                    returnedPath.addFirst(truStart.getX());
                    return returnedPath;
                }
            }
        }

        return null;

    }

    /**
     * Method getWordPath gets the word path of desired words and prints in String form
     *
     * @param file a name of a file as a String
     * @throws IOException
     */
    void getWordPath(String file) throws IOException {
        ArrayList<WordData> data = WordPath.makeWordArray(file);

        JFrame frame = new JFrame("Input Here");

        // Displays JOptionPane to ask user if they want to start program
        int result = JOptionPane.showConfirmDialog(frame, "Start Program?", "start",
                JOptionPane.YES_NO_CANCEL_OPTION);

        // Precondition: result != JOptionPane.NO_OPTION
        while (result != JOptionPane.NO_OPTION) {
            String startWord = JOptionPane.showInputDialog(frame, "Enter start word");

            String destWord = JOptionPane.showInputDialog(frame, "Enter destination word");

            // Declares variables to hold index numbers of start and dest
            int indexOfStart = 0;
            int indexOfDest = 0;

            // Gets the indexes of the entered words
            for (WordData wordData : data) {
                if (startWord.equals(wordData.getS())) {
                    indexOfStart = wordData.getX();
                }
                if (destWord.equals(wordData.getS())) {
                    indexOfDest = wordData.getX();
                }
            }

            // Gets the path between the two entered words
            LinkedList<Integer> path = getPath(indexOfStart, indexOfDest, data);

            // Creates StringBuilder for pathString
            StringBuilder pathSB = new StringBuilder();

            // Gets the word associated with the index and adds it to pathSB
            for (Integer integer : path) {
                pathSB.append(data.get(integer).getS() + "\n");
            }

            // Prints path as String
            System.out.println(pathSB.toString());

            // Sets all booleans to false so another search can be run
            for (WordData wordData : data) {
                wordData.setaBoolean(false);
            }

            // Displays JOptionPane to ask user if they want to start program again
            result = JOptionPane.showConfirmDialog(frame, "Start Program?", "start",
                    JOptionPane.YES_NO_CANCEL_OPTION);


        }

    }

    /**
     * The main method
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        WordPath wordPath = new WordPath();

        // Gets Word Path
        wordPath.getWordPath("Length3WordIndex.txt");

    }

}
