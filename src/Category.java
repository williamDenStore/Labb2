import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Category {
    private final String categoryName;
    private ArrayList<Product> products = new ArrayList<>();
    public ArrayList<Product> products(){
        return products;
    }
    public String category(){
        return categoryName;
    }
    public Category(String categoryName, ArrayList<Product> products) {
        this.categoryName = categoryName;
    }
    @Override
    public String toString() {
        return "Category{" +
                "categoryName='" + categoryName + '\'' +
                ", products=" + products +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(categoryName, category.categoryName) && Objects.equals(products, category.products);
    }
    @Override
    public int hashCode() {
        return Objects.hash(categoryName, products);
    }
    public void run(){
        menu();
    }
    private void printmenu() {
        System.out.println(  "------------------------------------------\n"
                            +"|1. skapa "+ categoryName + " produkter  \n"
                            +"|2. skriv ut "+ categoryName +" produkter\n"
                            +"|3. ta bort "+ categoryName +" produkter \n"
                            +"|e. gå tillbaka till kategori val        \n"
                            +"------------------------------------------");
    }
    private void menu() {
        Scanner s = new Scanner(System.in);
        String menuChoice="a";
        while(!menuChoice.equals("e")) {
            printmenu();
            menuChoice= s.nextLine();
            switch (menuChoice) {
                case "1": createProduct(s);
                    break;
                case "2":Search.groupSameObjects(products);
                    break;
                case "3":removeProduct(products, s);
                    break;
                case "e":
                    System.out.println("shutting down system");
                    break;
                default:
                    System.out.println("Error vänligen mata in rätt input");
                    break;

            }
        }
    }
    private void createProduct(Scanner s){
        System.out.println("skriv produkt namn");
        String name = s.nextLine();
        System.out.println("skriv produkt pris");
        int price = s.nextInt();
        System.out.println("skriv produkt märke");
        String brand = s.nextLine();
        s.next();
        System.out.println("skriv produkt id");
        int productId =s.nextInt();
        products.add(new Product(name,price,brand,productId));
        System.out.println("added");
    }
    private void removeProduct(ArrayList<Product> products, Scanner s) {
        products.remove(selectProduct(products,s));
    }
    public int selectProduct(ArrayList<Product> products, Scanner s) {
        System.out.println("välj ett att följade alternativ");
        Search.printGroupedObjects(Search.groupSameObjects(products));
        return s.nextInt();
    }

}


