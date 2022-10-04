import java.util.Objects;

public record Product(String name, int price, String brand, int productId) {

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", brand='" + brand + '\'' +
                ", productId=" + productId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return price == product.price && productId == product.productId && Objects.equals(name, product.name) && Objects.equals(brand, product.brand);
    }

}
