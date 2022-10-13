import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Search {

    public static void searchAlternatives(ArrayList<Category> categories) {
        Scanner s = new Scanner(System.in);
        String menuChoice="0";
        while(!menuChoice.equals("e")){
            printSearchAlternatives();
            menuChoice = s.nextLine();
            switch (menuChoice) {
                case "1":searchProductsInCategory(s, categories);
                    break;
                case "2":searchProductsInPriceIntervall(s, categories);
                    break;
                case "3":searchProductNamesMenu(s, categories);
                    break;
                case "4": groupSameObjects(findAllAlternatives(categories));
                    break;
                default:
                    System.out.println("Error vänligen mata in korrekt alternativ");
            }
        }
    }
    private static void printSearchAlternatives() {
        System.out.println( "---------------------------------------------\n"+
                "|1. sök på produkter i en kategori          |\n"+
                "|2. sök på produkter inom ett pris intervall|\n"+
                "|3. sök på produkt namn                     |\n"+
                "|4. få fram alla  produkter                 |\n"+
                "|e. gå tillbaka till kategori alternativ    |\n"+
                "---------------------------------------------");
    }
    private static void searchProductNamesMenu(Scanner s, ArrayList<Category> categories) {
        System.out.println("skriv in vad du vill söka på");
        String search = s.nextLine();
        searchProductName(search, categories);
    }
    private static void searchProductName(String search, ArrayList<Category> categories) {
        ArrayList<Product> searchresult = new ArrayList<>();
        categories.forEach(category -> category.products().stream().filter(product -> product.name().contains(search)).forEach(searchresult::add));
        groupSameObjects(searchresult);
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
        groupSameObjects(searchresult);
    }
    private static void searchProductsInCategory(Scanner s, ArrayList<Category> categories) {
        groupSameObjects(categories.get(RunCategory.selectCategory(categories, s)).products());
        System.out.println("skriv in valfritt tecken för att återgå till sök menyn");
        s.next();
    }
    private static ArrayList<Product> findAllAlternatives(ArrayList<Category> categories){
        ArrayList<Product> result = new ArrayList<>();
        categories.forEach(c -> result.addAll(c.products()));
        return result;
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
}
