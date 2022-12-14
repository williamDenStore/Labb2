import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
public class Writer {
    public static void saveFiles(ArrayList<Category> categories){
        String s = new Gson().toJson(categories);
        try {
            Files.writeString(Path.of("src","products.json"), s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
