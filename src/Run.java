import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Run{
    //hela run går kanske att göra recursiv med 1 steg. om man använder variabler som beskriver om de är product eller category
    /*todo
     *
     */
    //ska användas för att kunna implementera alla metoder som hanterar product i run. med hjälp av interface
    static int currentCategory;
    public void running(){
        Scanner s = new Scanner(System.in);
        ArrayList<Category> categories = new ArrayList<>();
        menu(categories, s);

    }
    private void menu(ArrayList<Category> categories, Scanner s) {
        String menuChoice="0";
        while(!menuChoice.equals("e")) {
            printmenu();
            menuChoice= s.nextLine();
            switch (menuChoice) {
                case "1": createCategory(categories,s);
                    break;
                case "2": printCategory(categories);

                    break;
                case "3":categories.get(selectCategory(categories, s)).run();
                    break;
                case "4":removeCategory(categories, s);
                break;
                case "5":initializeCategories(categories);
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

    private void initializeCategories(ArrayList<Category> categories) {
        Reader readcategories = new Reader();
        for (int i = 0; i < readcategories.read("categories").size(); i++) {
            categories.add(new Category((String) readcategories.read("categories").get(i)));
        }
        initalizeProducts(categories);
    }
    private void initalizeProducts(ArrayList<Category> categories){
        for (int i = 0; i < categories.size(); i++) {
            categories.get(i).initalizeProducts(i);
        }
    }

    private void removeCategory(ArrayList<Category> categories, Scanner s) {
        categories.remove(selectCategory(categories,s));
    }

    private int selectCategory(ArrayList<Category> categories, Scanner s) {
        System.out.println("choose one of the following numbers");
        printCategory(categories);
        int choice = s.nextInt();
        return choice;
        //Run klassen och kategori klassen kan ärva en metod som heter menu. Don't repeat yourself
    }

    private void printCategory(ArrayList<Category> categories) {
        for (int i = 0; i < categories.size(); i++) {
            System.out.println("[" + i + "]" + categories.get(i).category);
        }
    }

    private void printmenu() {
        System.out.println("1. Create category");
        System.out.println("2. print categories");
        System.out.println("3. select category");
        System.out.println("4. remove category");
        System.out.println("5. intizialise");
    }

    private void createCategory(ArrayList<Category> categories, Scanner s){
        categories.add(new Category(s.nextLine()));
        System.out.println("added");
    }


}
