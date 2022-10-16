 public interface Discounter {
    double applyDiscount(double amount);

    static Discounter tenPercentOff() {
        return amount -> amount*0.9;
    }

    static Discounter twentyPercentOff() {
        return amount -> amount*0.8;
    }

    static Discounter fiftyPercentOff() {
        return amount -> amount*0.5;
    }
}
