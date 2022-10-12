import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Category {
    private final String categoryName;

    ArrayList<Product> products = new ArrayList<>();
    public String categoryName(){
        return categoryName;
    }
    public Category(String categoryName, ArrayList<Product> products) {
        this.categoryName = categoryName;
    }
    public void run(){
        menu();
    }
    private void printmenu() {
        System.out.println("1. skapa"+ categoryName + "produkter");
        System.out.println("2. skriv ut "+ categoryName +" produkter");
        System.out.println("3. ta bort"+ categoryName +"produkter");
        System.out.println("e. gå tillbaka till kategori val");
    }
    public ArrayList<Product> products(){
        return products;
    }
    public String category(){
        return categoryName;
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
                case "2":groupSameObjects(products);
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

    private void printProducts() {
        for (int i = 0; i < products.size(); i++) {
            System.out.println("produkt namn:" + products.get(i).name());
            System.out.println("produkt pris:" + products.get(i).price());
            System.out.println("produkt varumärke" + products.get(i).brand());
            System.out.println("produkt id" + products.get(i).productId());
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
        groupSameObjects(products);
        int choice = s.nextInt();
        return choice;
    }
    public static void groupSameObjects(ArrayList<Product> products){
        products.stream()
                .collect(Collectors.groupingBy(Product::productId))
                .entrySet()
                .forEach(findFirstAndPrint());;
    }

    private static Consumer<Map.Entry<Integer, List<Product>>> findFirstAndPrint() {
        return product -> {
            System.out.print(product.getValue().size() + "st ");
            if (product.getValue()
                    .stream()
                    .findFirst()
                    .isPresent()) {
                Product p = product.getValue().stream().findFirst().orElse(null);
                System.out.println(p);
            }
        };
    }

    public void initalizeProducts(int index){
        String word="";
        int count=0;
        String productName="";
        int productPrice = 0;
        String productBrand="";
        int productId=0;
        Reader readProducts = new Reader();
        //StringName.split(,);
        ArrayList productsString = readProducts.read("products");
        for (int j = 0; j < productsString.get(index).toString().length(); j++) {
            if(productsString.get(index).toString().charAt(j)!= ',')
                word+= productsString.get(index).toString().charAt(j);
            if(productsString.get(index).toString().charAt(j)== ','){
                if(count == 0) {
                    productName = word;
                }
                else if(count == 1) {
                    productPrice = Integer.parseInt(word);
                }
                else if(count == 2) {
                    productBrand = word;
                }
                else if(count==3){
                    productId=Integer.parseInt(word);
                    products.add(new Product(productName,productPrice,productBrand,productId));
                    count=-1;
                }
                count++;
                word="";
            }
        }
    }
}


