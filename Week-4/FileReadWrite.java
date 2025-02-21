import java.io.*;

public class FileReadWrite {
    public static void main(String[] args) {
        // Writing to a file
        try {
            FileWriter writer = new FileWriter("data.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write("Hello, File I/O in Java!\n");
            bufferedWriter.write("This is the second line.");
            bufferedWriter.close();

            // Reading from the file
            FileReader reader = new FileReader("data.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
