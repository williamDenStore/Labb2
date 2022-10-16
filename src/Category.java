import java.util.*;

public class Category {
    private final String categoryName;
    private final ArrayList<Product> products = new ArrayList<>();
    public final ArrayList<Product> products(){
        return products;
    }
    public String category(){
        return categoryName;
    }
    public Category(String categoryName) {
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
                            +"|4. lägg till rabatt                     \n"
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
                case "2":Search.printGroupedObjects(Search.groupSameObjects(products));
                    break;
                case "3":removeProduct(products, s);
                    break;
                case "4": addDiscount(products, s);
                case "e":
                    System.out.println("shutting down system");
                    break;
                default:
                    System.out.println("Error vänligen mata in rätt input");
                    break;

            }
        }
    }
    private void addDiscount(ArrayList<Product> products, Scanner s) {
            var groupedProducts = Search.groupSameObjects(products);
            while(true) {
                System.out.println("skriv in id på produkten du vill implementera rabatt skirv 0 för att avsluta");
                Search.printGroupedObjects(groupedProducts);
                int productChoice = s.nextInt();
                if (productChoice==0){
                    break;
                }
                int choice = selectDiscount(s);
                Discounter discount = getDiscount(choice);
                if (discount != null)
                    applyDiscount(productChoice, discount, products, choice);
            }
    }

    private static int selectDiscount(Scanner s) {
        System.out.println("1. 50% rabatt");
        System.out.println("2. 20% rabatt");
        System.out.println("3. 10% rabatt");
        return CatchExceptions.getInterger();
    }

    private void applyDiscount(int productId, Discounter discount, ArrayList<Product> products, int discountType){
        if (CatchExceptions.containsId(products, productId)) {
            implementDiscount(productId, discount, products, discountType);
        }
        else
            System.out.println("vänligen skriv in id nummer på produkten du vill applicera rabatt på");
    }

    private static void implementDiscount(int productId, Discounter discount, ArrayList<Product> products, int discountType) {
        products.stream().filter(p -> p.productId() == productId).forEach(p -> p.setDiscount(discount));
        products.stream().filter(p -> p.productId() == productId).forEach(p -> p.setDiscountInt(discountType));
        System.out.println("rabatten är implementerad");
    }

    private Discounter getDiscount(int choice){

        if(choice==1)
            return Discounter.fiftyPercentOff();
        else if (choice==2)
            return(Discounter.twentyPercentOff());
        else if (choice==3)
            return Discounter.tenPercentOff();
        return null;
    }

    private void createProduct(Scanner s){
        System.out.println("skriv produkt namn");
        String name = s.nextLine();
        System.out.println("skriv produkt pris");
        int price = CatchExceptions.getInterger();
        System.out.println("skriv produkt märke");
        String brand = s.nextLine();
        s.next();
        System.out.println("skriv produkt id");
        int productId =CatchExceptions.getInterger();
        products.add(new Product(name,price,brand,productId));
        System.out.println("added");
    }
    private void removeProduct(ArrayList<Product> products, Scanner s) {
        products.remove(selectProduct(products,s));
    }
    public int selectProduct(ArrayList<Product> products, Scanner s) {
        System.out.println("välj ett att följade alternativ");
        for (int i = 0; i < products.size(); i++) {
            System.out.println("["+i+"]"+ products.get(i));
        }
        return CatchExceptions.getInterger();
    }
}


