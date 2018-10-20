package shapes1_5_5.files;

import shapes1_5_5.command.Command;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Chris Cavazos on 6/29/2018.
 */
public class FileHandler {

    public static void initFile() {

        try {
            FileReader file2 = new FileReader("controls.txt");
            BufferedReader bf2 = new BufferedReader(file2);
            String s2 = bf2.readLine();
            String[] sp = s2.split(" ");
            for (int i = 0; i < sp.length; i++) {
                Command.commands.get(i).changeKey(Integer.parseInt(sp[i]));
            }


            FileReader file = new FileReader("file.txt");
            BufferedReader bf = new BufferedReader(file);
            String s;
            while ((s = bf.readLine()) != null) {
                if (!s.equals("")) {
                    List<String> split = Arrays.asList(s.split(","));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
