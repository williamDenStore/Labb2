import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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
}
