package Tools;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WriteLogToFile {
    public void write(String text) throws IOException {
        File fout = new File("app.txt");
        FileOutputStream fos = new FileOutputStream(fout);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));


        bw.write(text);


        bw.close();

    }

    public String read() throws IOException {
        FileReader file = new FileReader("app.txt");  //address of the file
        List<String> Lines = new ArrayList<>();  //to store all lines
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {  //checking for the presence of next Line
            Lines.add(sc.nextLine());  //reading and storing all lines
        }
        sc.close();  //close the scanner
        System.out.print(Lines.get(Lines.size() - 1)); //displaying last one..
        return Lines.get(Lines.size() - 1);
    }
}