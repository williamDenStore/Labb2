import java.util.ArrayList;
import java.util.Scanner;

public class CatchExceptions {
    public static int getInterger() {
        Scanner s = new Scanner(System.in);
        int i = 0;
        boolean success = false;
        while (!success) {
            try {
                String st = s.next();
                i = Integer.parseInt(st);
                success = true;
            } catch (Exception e) {
                System.out.println("vänligen skriv in en siffra");
            }
        }
        return i;
    }
    public static boolean containsId(ArrayList<Product> products, int i){
        return products
                .stream()
                .anyMatch(p -> p.productId() == i);
    }
    public static boolean categoryContainsId(ArrayList<Category> categories, int a) {
        for (Category category : categories) {
            for (int j = 0; j < category.products().size(); j++) {
                if (category.products().get(j).productId() == a) {
                    return true;
                }
            }
        }
        return false;
    }
}
