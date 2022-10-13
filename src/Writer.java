import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Writer {
    public static void saveFiles(ArrayList<Category> p){
        String s = new Gson().toJson(p);
        try {
            Files.writeString(Path.of("src","products.json"), s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
