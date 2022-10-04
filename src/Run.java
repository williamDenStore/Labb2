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
                default:
                    System.out.println("Error vänligen mata in rätt input");
                    break;

            }
        }
    }
    private void printmenu() {
        System.out.println("1. Create category");
        System.out.println("2. print categories");
        System.out.println("3. select category");
        System.out.println("4. remove category");
        System.out.println("5. intizialise");
        System.out.println("6. shop");
    }
    private void initializeCategories() {
        Reader readcategories = new Reader();
        for (int i = 0; i < readcategories.read("categories").size(); i++) {
            categories.add(new Category((String) readcategories.read("categories").get(i)));
        }
        initalizeProducts();
    }
    private void initalizeProducts(){
        for (int i = 0; i < categories.size(); i++) {
            categories.get(i).initalizeProducts(i);
        }
    }

    private void removeCategory() {
        categories.remove(selectCategory());
    }

    private int selectCategory() {
        System.out.println("choose one of the following numbers");
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
        categories.add(new Category(s.nextLine()));
        System.out.println("added");
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }
}
