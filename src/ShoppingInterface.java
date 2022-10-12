import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class ShoppingInterface {
    ArrayList<Product> products = new ArrayList<>();
    Scanner s = new Scanner(System.in);

    public ShoppingInterface(ArrayList<Category> categories) {
        this.categories = categories;
    }
    ArrayList<Category> categories;
    ArrayList<Product> cart = new ArrayList<>();
    boolean intalized;
    public void run(){
        if(!intalized) {
            addAllProductsToArrayList();
        }
        addToCart();
        printCart();
    }
    private void addAllProductsToArrayList(){
        System.out.println(categories.size());
        for (int i = 0; i < categories.size(); i++) {
            products.addAll(categories.get(i).products());
            System.out.println(categories.get(i).products());
        }
        intalized=true;
    }
    private int selectOutOfAllProducts(){
        System.out.println("välj en av följande");
        System.out.println(products.size());
        for (int i = 0; i < products.size(); i++) {
            System.out.println("["+i+"] "+products.get(i).name());
        }
        return s.nextInt();
    }
    private void addToCart(){
        int i=0;
        while(true) {
            i = selectOutOfAllProducts();
            if(i<products.size())
                cart.add(products.get(i));
                break;
        }
    }
    private void printCart(){
        for (int i = 0; i < cart.size(); i++) {
            System.out.println(cart.get(i));
        }
    }
}

