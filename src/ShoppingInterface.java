import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class ShoppingInterface {
    ArrayList<Product> products1 = new ArrayList<>();
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
        int choice=-1;
        while(choice!=0) {
            choice = selectOutOfAllProducts(groupedProducts);
            if (choice!=0 && CatchExceptions.categoryContainsId(categories,choice)) {
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
        return CatchExceptions.getInterger();
    }
    private void removeMatchingElements(ArrayList<Product> cart, ArrayList<Category> categories) {
        for (Category category : categories) {
            for (int j = 0; j < category.products().size(); j++) {
                for (Product product : cart) {
                    if (category.products().get(j).equals(product)) {
                        category.products().remove(j);
                        break;
                    }
                }
            }
        }
    }
    private void addAllProductsToArrayList(ArrayList<Category> categories){
        for (Category category : categories) {
            products1.addAll(category.products());
        }
        intalized=true;
    }
    private void printReceipt() {
        //gör cart till map och skriv ut antal som 3st osv osv bla bla haha yaya wawa skrt skrt
        cart.forEach(System.out::println);
        var sum = cart.stream()
                .mapToDouble(Product::price)
                .sum();
        System.out.println("totalt "+sum+"kr");
        applyDiscounts();
    }
    private void applyDiscounts(){
        double sumAfterDiscount=0;
        for (Product product : cart) {
            if (product.discount == null) {
                sumAfterDiscount += product.price();
                System.out.println(product.price());
            } else {
                sumAfterDiscount += product.getDiscount();
                System.out.println("Before discount " + product.price() + " after discount " + product.getDiscount());
            }
        }
        System.out.println("total summa efter rabatt "+sumAfterDiscount+"kr");
    }
}

