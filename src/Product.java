import java.util.Objects;

public final class Product {
    private final String name;
    private final double price;
    private final String brand;
    private final int productId;

    public Product(String name, double price, String brand, int productId) {
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.productId = productId;
    }

    @Override
    public String toString() {
        return
                "Namn:" + name +
                        " pris:" + price +
                        " varumärke:" + brand +
                        " produkt id:" + productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return price == product.price && productId == product.productId && Objects.equals(name, product.name) && Objects.equals(brand, product.brand);
    }

    public String name() {
        return name;
    }

    public double price() {
        return price;
    }

    public String brand() {
        return brand;
    }

    public int productId() {
        return productId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, brand, productId);
    }


}
