import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Run{
    //hela run går kanske att göra recursiv med 1 steg. om man använder variabler som beskriver om de är product eller category
    /*todo
     *
     */
    //ska användas för att kunna implementera alla metoder som hanterar product i run. med hjälp av interface
    Scanner s = new Scanner(System.in);
    ArrayList<Category> categories = new ArrayList<>();
    ShoppingInterface shop = new ShoppingInterface(categories);
    public void running(){

        menu(categories, s);

    }
    private void menu(ArrayList<Category> categories, Scanner s) {
        loadFiles();
        String menuChoice="0";
        while(!menuChoice.equals("e")) {
            printmenu();
            menuChoice= s.nextLine();
            switch (menuChoice) {
                case "1": createCategory();
                    break;
                case "2": printCategory();

                    break;
                case "3":categories.get(selectCategory()).run();
                    break;
                case "4":removeCategory();
                break;
                case "5":initializeCategories();
                    break;
                case "6":shop.run();
                case "e":
                    System.out.println("shutting down system");
                    break;
                case "7":searchAlternatives();
                default:
                    System.out.println("Error vänligen mata in korrekt alternativ");
                    break;

            }
        }
        categories.get(1).products.get(0).name();
        categories.forEach(i-> Writer.write(categories));
    }

    private void searchAlternatives() {
        String menuChoice="0";
        while(!menuChoice.equals("e")){
            printSearchAlternatives();
            menuChoice = s.nextLine();
            switch (menuChoice) {
                case "1":searchProductsInCategory();
                    break;
                case "2":searchProductsInPriceIntervall();
                    break;
                case "3":searchProductNamesMenu();
                    break;
                default:
                    System.out.println("Error vänligen mata in korrekt alternativ");
            }
        }
    }

    private void searchProductNamesMenu() {
        System.out.println("skriv in vad du vill söka på");
        String search = s.nextLine();

        searchProductName(search);
    }

    private void searchProductName(String search) {
        ArrayList<Product> searchresult = new ArrayList<>();
        categories.forEach(category -> category.products.stream().filter(product -> product.name().contains(search)).forEach(searchresult::add));
        Category.groupSameObjects(searchresult);
    }

    private void searchProductsInPriceIntervall() {
        System.out.println("skriv intervallet du vill söka mellan");
        System.out.println("lägsta pris");
        int lowest = s.nextInt();
        System.out.println("högsta pris");
        int highest = s.nextInt();
        searchPrice(highest,lowest);


    }
    private void searchPrice(int max, int min){
        ArrayList<Product> searchresult = new ArrayList<>();
        categories.forEach(c -> c.products.stream().filter(product -> product.price() < max && product.price() > min).forEach(searchresult::add));
        Category.groupSameObjects(searchresult);
    }

    private void searchProductsInCategory() {
        Category.groupSameObjects(categories.get(selectCategory()).products);
        System.out.println("skriv in valfritt tecken för att återgå till sök menyn");
        s.next();
    }

    private void printSearchAlternatives() {
        System.out.println("---------------------------------------------");
        System.out.println("|1. sök på produkter i en kategori          |");
        System.out.println("|2. sök på produkter inom ett pris intervall|");
        System.out.println("|3. sök på produkt namn                     |"); //string contains
        System.out.println("|e. gå tillbaka till kategori alternativ    |");
        System.out.println("---------------------------------------------");
    }

    private void printmenu() {
        System.out.println("|-----------------------|");
        System.out.println("|1. skapa kategori      |");
        System.out.println("|2. skriva ut kategorier|");
        System.out.println("|3. välj kategori       |");
        System.out.println("|4. ta bort kategori    |");
        System.out.println("|5. intizialise         |");
        System.out.println("|6. handla              |");
        System.out.println("|7. sök produkter       |");
        System.out.println("|e. avsluta programmet  |");
        System.out.println("|-----------------------|");
    }
    private void initializeCategories() {
        Reader readcategories = new Reader();
        for (int i = 0; i < readcategories.read("categories").size(); i++) {
            categories.add(new Category((String) readcategories.read("categories").get(i),null));
        }
        initalizeProducts();
    }
    private void initalizeProducts(){
        for (int i = 0; i < categories.size(); i++) {
            categories.get(i).initalizeProducts(i);
        }
        //System.out.println(categories);
        System.out.println(categories.get(1).products);
        System.out.println(categories.get(1).categoryName());
    }

    private void removeCategory() {
        categories.remove(selectCategory());
    }

    private int selectCategory() {
        System.out.println(categories);
        System.out.println("välj ett av följande nummer");
        printCategory();
        int choice = s.nextInt();
        return choice;
        //Run klassen och kategori klassen kan ärva en metod som heter menu. Don't repeat yourself
    }

    private void printCategory() {
        for (int i = 0; i < categories.size(); i++) {
            System.out.println("[" + i + "]" + categories.get(i).category());
        }
    }



    private void createCategory(){
        categories.add(new Category(s.nextLine(),null));
        System.out.println("added");
    }
    private void loadFiles(){
        categories.addAll(Reader.read1());
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }
}
