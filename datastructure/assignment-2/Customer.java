/** Data pelanggan/transaksi. */
public class Customer {
    private final String number;
    private final String name;
    private final double total;

    public Customer(String number, String name, double total) {
        this.number = number;
        this.name = name;
        this.total = total;
    }

    public String getNumber() { return number; }
    public String getName() { return name; }
    public double getTotal() { return total; }
}
