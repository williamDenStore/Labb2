import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Category {
    private String category;
    Scanner s = new Scanner(System.in);
    ArrayList<Product> products = new ArrayList<>();

    public Category(String category) {
        this.category = category;
    }
    public void run(){
        menu();
    }
    private void printmenu() {
        System.out.println("1. skapa en produkt av typen "+ category);
        System.out.println("2. skriv ut produkter i "+ category);
        System.out.println("3. ta bort produkt i "+ category);
    }
    public String category(){
        return category;
    }
    private void menu() {
        String menuChoice="a";
        while(!menuChoice.equals("e")) {
            printmenu();
            menuChoice= s.nextLine();
            switch (menuChoice) {
                case "1": createProduct();
                    break;
                case "2":printProducts();
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
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
                "products=" + products +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category1 = (Category) o;
        return Objects.equals(category, category1.category) && Objects.equals(s, category1.s) && Objects.equals(products, category1.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, s, products);
    }

    private void printProducts() {
        for (int i = 0; i < products.size(); i++) {
            System.out.println("produkt namn:" + products.get(i).name());
            System.out.println("produkt pris:" + products.get(i).price());
            System.out.println("produkt varumärke" + products.get(i).brand());
            System.out.println("produkt id" + products.get(i).productId());
        }
    }

    private void createProduct(){
        System.out.println("produkt namn");
        String name = s.nextLine();
        System.out.println("produkt pris");
        int price = s.nextInt();
        System.out.println("produkt märke");
        String brand = s.nextLine();
        s.next();
        System.out.println("produkt id");
        int productId =s.nextInt();
        products.add(new Product(name,price,brand,productId));
        System.out.println("added");
    }
    private void removeProduct(ArrayList<Product> products, Scanner s) {
        products.remove(selectProduct(products,s));
    }
    public int selectProduct(ArrayList<Product> products, Scanner s) {
        System.out.println("choose one of the following numbers");
        printProducts(products);
        int choice = s.nextInt();
        return choice;
    }
    private void printProducts(ArrayList<Product> products) {
        for (int i = 0; i < products.size(); i++) {
            System.out.println("[" + i + "]" + products.get(i).name());
        }
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

    public ArrayList<Product> getProducts() {
        return products;
    }
}


