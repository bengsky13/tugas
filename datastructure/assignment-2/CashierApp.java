import java.util.Scanner;

/** Program kasir menggunakan queue untuk antrian dan stack untuk riwayat. */
public class CashierApp {
    private static final Scanner INPUT = new Scanner(System.in);
    private static final CustomerQueue QUEUE = new CustomerQueue();
    private static final TransactionStack HISTORY = new TransactionStack();

    public static void main(String[] args) {
        int menu;
        do {
            System.out.println("\n=== SISTEM KASIR TOKO ===");
            System.out.println("1. Tambah Antrian\n2. Layani Pelanggan\n3. Tampilkan Antrian");
            System.out.println("4. Lihat Riwayat Transaksi\n5. Keluar");
            menu = readInt("Pilih menu: ");
            switch (menu) {
                case 1 -> enqueueCustomer();
                case 2 -> serveCustomer();
                case 3 -> QUEUE.display();
                case 4 -> HISTORY.display();
                case 5 -> System.out.println("Program selesai.");
                default -> System.out.println("Menu tidak valid.");
            }
        } while (menu != 5);
    }

    private static void enqueueCustomer() {
        String number = readText("Masukkan Nomor Antrian: ");
        String name = readText("Masukkan Nama Pelanggan: ");
        double total = readDouble("Masukkan Total Belanja: ");
        QUEUE.enqueue(new Customer(number, name, total));
        System.out.println("Data pelanggan ditambahkan ke antrian!");
    }

    private static void serveCustomer() {
        Customer customer = QUEUE.dequeue();
        if (customer == null) { System.out.println("Antrian kosong."); return; }
        HISTORY.push(customer);
        System.out.printf("Melayani pelanggan %s (%s)%n", customer.getNumber(), customer.getName());
        System.out.println("Transaksi disimpan ke riwayat.");
    }

    private static String readText(String prompt) {
        System.out.print(prompt); return INPUT.nextLine().trim();
    }

    private static int readInt(String prompt) {
        while (true) {
            try { return Integer.parseInt(readText(prompt)); }
            catch (NumberFormatException e) { System.out.println("Masukkan angka yang valid."); }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            try {
                double value = Double.parseDouble(readText(prompt));
                if (value >= 0) return value;
            } catch (NumberFormatException ignored) { }
            System.out.println("Total belanja harus berupa angka >= 0.");
        }
    }
}
