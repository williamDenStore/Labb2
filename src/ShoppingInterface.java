import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ShoppingInterface {
    ArrayList<Product> products1 = new ArrayList<>();
    Scanner s = new Scanner(System.in);
    ArrayList<Product> cart = new ArrayList<>();
    boolean intalized;
    public void run(ArrayList<Category> categories){
        if(!intalized) {
            addAllProductsToArrayList(categories);
        }
        var groupedProducts = Search.groupSameObjects(products1);
        addToCart(groupedProducts, categories);
        printReceipt();
    }
    private void addToCart(Map<Integer, List<Product>> groupedProducts, ArrayList<Category> categories){
        int count=0;
        int choice=-1;
        while(choice!=0) {
            choice = selectOutOfAllProducts(groupedProducts);
            if (choice!=0) {
                cart.add(groupedProducts.get(choice).get(0));
                groupedProducts.get(choice).remove(0);
                if(groupedProducts.get(choice).size()==0){
                    groupedProducts.remove(choice);
                }

            }
        }
        removeMatchingElements(cart, categories);
    }
    private int selectOutOfAllProducts(Map<Integer, List<Product>> groupedProducts){
        System.out.println("Skriv in produktens id för att lägga till i kundvagnen. skriv 0 för att avsluta");
        Search.printGroupedObjects(groupedProducts);
        return s.nextInt();
    }
    private void removeMatchingElements(ArrayList<Product> cart, ArrayList<Category> categories) {
        for (int i = 0; i < categories.size(); i++) {
            for (int j = 0; j < categories.get(i).products().size(); j++) {
                for (int k = 0; k < cart.size(); k++) {
                    if(categories.get(i).products().get(j).equals(cart.get(k))){
                        categories.get(i).products().remove(j);
                        break;
                    }
                }

            }
        }
    }
    private void addAllProductsToArrayList(ArrayList<Category> categories){
        for (int i = 0; i < categories.size(); i++) {
            products1.addAll(categories.get(i).products());
        }
        intalized=true;
    }
    private void printReceipt() {
        cart.forEach(System.out::println);
        var sum = cart.stream()
                .mapToDouble(Product::price)
                .sum();
        System.out.println("totalt "+sum+"kr");
    }
}

