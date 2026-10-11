import java.util.Scanner;

/** Program utama pengelolaan data buku. */
public class LibraryApp {
    private static final Scanner INPUT = new Scanner(System.in);
    private static final LinkedList BOOKS = new LinkedList();

    public static void main(String[] args) {
        int menu;
        do {
            System.out.println("\n===== SISTEM DATA BUKU =====");
            System.out.println("1. Tambah Buku\n2. Hapus Buku\n3. Cari Buku");
            System.out.println("4. Lihat Semua Buku\n5. Keluar");
            menu = readInt("Pilih menu: ");
            switch (menu) {
                case 1 -> addBook();
                case 2 -> removeBook();
                case 3 -> findBook();
                case 4 -> { System.out.println("\nDaftar Buku:"); BOOKS.display(); }
                case 5 -> System.out.println("Program selesai.");
                default -> System.out.println("Menu tidak valid.");
            }
        } while (menu != 5);
    }

    private static void addBook() {
        String code;
        do {
            code = readText("Masukkan Kode Buku (maksimal 5 karakter): ");
            if (code.length() > 5 || code.isEmpty()) System.out.println("Kode harus 1-5 karakter.");
        } while (code.length() > 5 || code.isEmpty());
        if (BOOKS.find(code) != null) { System.out.println("Kode buku sudah digunakan."); return; }
        String title = readText("Masukkan Judul: ");
        String author = readText("Masukkan Penulis: ");
        BOOKS.addLast(new Book(code, title, author));
        System.out.println("Data berhasil ditambahkan!");
    }

    private static void removeBook() {
        Book removed = BOOKS.removeLast();
        System.out.println(removed == null ? "Tidak ada data untuk dihapus."
                : "Buku terakhir dihapus: " + removed.getTitle());
    }

    private static void findBook() {
        Book book = BOOKS.find(readText("Masukkan Kode Buku: "));
        if (book == null) System.out.println("Buku tidak ditemukan.");
        else System.out.printf("Kode: %s | Judul: %s | Penulis: %s%n",
                book.getCode(), book.getTitle(), book.getAuthor());
    }

    private static String readText(String prompt) {
        System.out.print(prompt);
        return INPUT.nextLine().trim();
    }

    private static int readInt(String prompt) {
        while (true) {
            try { return Integer.parseInt(readText(prompt)); }
            catch (NumberFormatException e) { System.out.println("Masukkan angka yang valid."); }
        }
    }
}
