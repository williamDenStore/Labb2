import java.util.*;
import java.util.stream.Collectors;

public class Search {

    public static void searchAlternatives(ArrayList<Category> categories) {
        Scanner s = new Scanner(System.in);
        String menuChoice="0";
        while(!menuChoice.equals("e")){
            printSearchAlternatives();
            menuChoice = s.nextLine();
            switch (menuChoice) {
                case "1" -> searchProductsInCategory(s, categories);
                case "2" -> searchProductsInPriceIntervall(s, categories);
                case "3" -> searchProductNamesMenu(s, categories);
                case "4" -> printGroupedObjects(groupSameObjects(findAllAlternatives(categories)));
                default -> System.out.println("Error vänligen mata in korrekt alternativ");
            }
        }
    }
    private static void printSearchAlternatives() {
        System.out.println("""
                ---------------------------------------------
                |1. sök på produkter i en kategori          |
                |2. sök på produkter inom ett pris intervall|
                |3. sök på produkt namn                     |
                |4. få fram alla  produkter                 |
                |e. gå tillbaka till kategori alternativ    |
                ---------------------------------------------""");
    }
    private static void searchProductNamesMenu(Scanner s, ArrayList<Category> categories) {
        System.out.println("skriv in vad du vill söka på");
        String search = s.nextLine();
        searchProductName(search, categories);
    }
    private static void searchProductName(String search, ArrayList<Category> categories) {
        ArrayList<Product> searchresult = new ArrayList<>();
        categories.forEach(category -> category.products().stream().filter(product -> product.name().contains(search)).forEach(searchresult::add));
        printGroupedObjects(groupSameObjects(searchresult));
    }
    private static void searchProductsInPriceIntervall(Scanner s, ArrayList<Category> categories) {
        System.out.println("skriv intervallet du vill söka mellan \n lägsta pris");
        int lowest = s.nextInt();
        System.out.println("högsta pris");
        int highest = s.nextInt();
        searchPrice(highest,lowest, categories);
    }
    private static void searchPrice(int max, int min, ArrayList<Category> categories){
        ArrayList<Product> searchresult = new ArrayList<>();
        categories.forEach(c -> c.products()
                .stream()
                .filter(product -> product.price() < max && product.price() > min).forEach(searchresult::add));
        printGroupedObjects(groupSameObjects(searchresult));
    }
    private static void searchProductsInCategory(Scanner s, ArrayList<Category> categories) {
        printGroupedObjects(groupSameObjects(categories.get(RunCategory.selectCategory(categories)).products()));
        System.out.println("skriv in valfritt tecken för att återgå till sök menyn");
        s.next();
    }
    private static ArrayList<Product> findAllAlternatives(ArrayList<Category> categories){
        ArrayList<Product> result = new ArrayList<>();
        categories.forEach(c -> result.addAll(c.products()));
        return result;
    }
    public static Map<Integer, List<Product>> groupSameObjects(ArrayList<Product> products){
        return products.stream()
                .collect(Collectors.groupingBy(Product::productId));
    }
    public static void printGroupedObjects(Map<Integer, List<Product>> products2) {
        products2.forEach((key, value) -> {
            System.out.print(value.size() + "st ");
            Product p = value.stream().findFirst().orElse(null);
            System.out.println(p);
        });
    }
}
