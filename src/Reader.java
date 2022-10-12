import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Reader {
    public ArrayList read(String readFile){
        ArrayList<String> savedLines = new ArrayList<>();
        try {
            FileReader fr = new FileReader("src/"+readFile+".txt");
            BufferedReader br = new BufferedReader(fr);
            String str;
            while((str = br.readLine()) != null) {
                savedLines.add(str);

            }
            br.close();
        }
        catch (IOException e){
            System.out.println("Error in reader");
        }
        return savedLines;
    }
    public static ArrayList read1(){

        try {
            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new FileReader("src/products.json"));
            ArrayList<Category> jsonToObject = gson.fromJson(reader, new TypeToken<ArrayList<Category>>(){}.getType());
            return jsonToObject;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
