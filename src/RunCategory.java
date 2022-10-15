import java.util.ArrayList;
import java.util.Scanner;

public class RunCategory{
    Scanner s = new Scanner(System.in);
    ArrayList<Category> categories = new ArrayList<>();
    ShoppingInterface shop = new ShoppingInterface();
    public void running(){
        menu(categories, s);
    }
    private void menu(ArrayList<Category> categories, Scanner s) {
        categories.addAll(Reader.loadFiles());
        menuSwitch(categories, s);
        categories.forEach(i-> Writer.saveFiles(categories));
    }

    private void menuSwitch(ArrayList<Category> categories, Scanner s) {
        String menuChoice="0";
        while(!menuChoice.equals("e")) {
            printmenu();
            menuChoice= s.nextLine();
            switch (menuChoice) {
                case "1": createCategory();
                    break;
                case "2": printCategory(categories);

                    break;
                case "3":
                    categories.get(selectCategory(categories, s)).run();
                    break;
                case "4":removeCategory();
                    break;
                case "5":shop.run(categories);
                    break;
                case "6":Search.searchAlternatives(categories);
                    break;
                case "e":
                    System.out.println("shutting down system");
                default:
                    System.out.println("Error vänligen mata in korrekt alternativ");
                    break;

            }
        }
    }
    private void printmenu() {
        System.out.println("""
                |-----------------------|
                |1. skapa kategori      |
                |2. skriva ut kategorier|
                |3. välj kategori       |
                |4. ta bort kategori    |
                |5. handla              |
                |6. sök produkter       |
                |e. avsluta programmet  |
                |-----------------------|""");
    }
    private void removeCategory() {
        categories.remove(selectCategory(categories, s));
    }
    public static int selectCategory(ArrayList<Category> categories, Scanner s) {
        System.out.println("välj ett av följande nummer");
        printCategory(categories);
        int choice = s.nextInt();
        return choice;
    }
    private static void printCategory(ArrayList<Category> categories) {
        for (int i = 0; i < categories.size(); i++) {
            System.out.println("[" + i + "]" + categories.get(i).category());
        }
    }
    private void createCategory(){
        categories.add(new Category(s.nextLine(),null));
        System.out.println("added");
    }
}
