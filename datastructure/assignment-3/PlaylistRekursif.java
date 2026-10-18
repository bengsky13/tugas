/*
 * TUGAS KELOMPOK 3
 * Mata Kuliah: COSC6025036 - Data Structures and Algorithm Analysis
 * Topik: Algorithmic Analysis, Mathematical Induction, and Recursive Functions
 *
 * Anggota Kelompok:
 * 1. Krisna Setiyawan                  - 2902829641
 * 2. Azril Tsani                       - 2902807312
 * 3. Bambang Priyanto                  - 2902819073
 * 4. Haiefa Agasy Aprilya Sari         - 2602311275
 * 5. Muhammad Ramadan Abdul Khalik     - 3002933264
 * Program Studi: Computer Science
 */

import java.io.OutputStream;
import java.io.PrintStream;

public class PlaylistRekursif {
    static Lagu[] playlist;

    /*
     * Menghitung total durasi playlist secara rekursif.
     * Base case: n == 0, tidak ada lagu tersisa.
     * Recursive case: durasi lagu terakhir + totalDurasi(n - 1).
     * Kompleksitas: waktu O(n), ruang O(n) untuk call stack.
     */
    static double totalDurasi(Lagu[] list, int n) {
        if (list == null || n <= 0) {
            return 0;
        }
        return list[n - 1].getDurasi() + totalDurasi(list, n - 1);
    }

    /*
     * Menampilkan playlist dari lagu terakhir ke lagu pertama.
     * Base case: index < 0.
     * Recursive case: tampilkan lagu saat ini lalu panggil index - 1.
     * Kompleksitas: waktu O(n), ruang O(n) untuk call stack.
     */
    static void tampilkanMundur(Lagu[] list, int index) {
        if (list == null || index < 0) {
            return;
        }

        System.out.printf("%d. %s - %s (%.2f menit)%n",
                index + 1,
                list[index].getJudul(),
                list[index].getArtis(),
                list[index].getDurasi());
        tampilkanMundur(list, index - 1);
    }

    /*
     * Mencari lagu dengan durasi terpanjang secara rekursif.
     * Base case: index mencapai lagu terakhir.
     * Recursive case: bandingkan lagu saat ini dengan hasil pencarian berikutnya.
     * Kompleksitas: waktu O(n), ruang O(n) untuk call stack.
     */
    static Lagu cariDurasiTerpanjang(Lagu[] list, int index) {
        if (list == null || list.length == 0 || index < 0 || index >= list.length) {
            return null;
        }
        if (index == list.length - 1) {
            return list[index];
        }

        Lagu terpanjangSisa = cariDurasiTerpanjang(list, index + 1);
        return list[index].getDurasi() >= terpanjangSisa.getDurasi()
                ? list[index]
                : terpanjangSisa;
    }

    static Lagu[] buatPlaylist(int n) {
        Lagu[] dasar = {
                new Lagu("Perfect", "Ed Sheeran", 4.23),
                new Lagu("Shivers", "Ed Sheeran", 3.50),
                new Lagu("Fix You", "Coldplay", 4.00),
                new Lagu("Yellow", "Coldplay", 4.10),
                new Lagu("Viva La Vida", "Coldplay", 4.52)
        };

        Lagu[] hasil = new Lagu[n];
        for (int i = 0; i < n; i++) {
            Lagu lagu = dasar[i % dasar.length];
            hasil[i] = new Lagu(lagu.getJudul(), lagu.getArtis(), lagu.getDurasi());
        }
        return hasil;
    }

    static double keMs(long mulai, long selesai) {
        return (selesai - mulai) / 1_000_000.0;
    }

    // Mengukur traversal tanpa membiarkan output lagu mengganggu tabel benchmark.
    static double ukurTampilkanMundur(Lagu[] data) {
        PrintStream outputAsli = System.out;
        try (PrintStream outputBuangan = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) {
                // Output dibuang hanya selama pengukuran.
            }
        })) {
            System.setOut(outputBuangan);
            long mulai = System.nanoTime();
            tampilkanMundur(data, data.length - 1);
            return keMs(mulai, System.nanoTime());
        } finally {
            System.setOut(outputAsli);
        }
    }

    public static void main(String[] args) {
        playlist = buatPlaylist(5);

        System.out.println("=== ANALISIS REKURSIF PLAYLIST ===");
        System.out.println("Jumlah lagu : " + playlist.length);
        System.out.printf("Total durasi : %.2f menit%n",
                totalDurasi(playlist, playlist.length));

        Lagu terpanjang = cariDurasiTerpanjang(playlist, 0);
        if (terpanjang != null) {
            System.out.printf("Lagu terpanjang : \"%s\" - %s (%.2f menit)%n",
                    terpanjang.getJudul(),
                    terpanjang.getArtis(),
                    terpanjang.getDurasi());
        }

        System.out.println("\nDaftar lagu (ditampilkan terbalik):");
        tampilkanMundur(playlist, playlist.length - 1);

        System.out.println("\n=== EKSPERIMEN WAKTU EKSEKUSI (ms) ===");
        System.out.printf("%-6s %-18s %-22s %-26s%n",
                "n", "totalDurasi()", "tampilkanMundur()", "cariDurasiTerpanjang()");

        int[] ukuran = {3, 5, 10, 20, 50, 100, 1000};
        for (int n : ukuran) {
            Lagu[] data = buatPlaylist(n);

            long mulaiTotal = System.nanoTime();
            totalDurasi(data, data.length);
            double waktuTotal = keMs(mulaiTotal, System.nanoTime());

            double waktuMundur = ukurTampilkanMundur(data);

            long mulaiTerpanjang = System.nanoTime();
            cariDurasiTerpanjang(data, 0);
            double waktuTerpanjang = keMs(mulaiTerpanjang, System.nanoTime());

            System.out.printf("%-6d %-18.4f %-22.4f %-26.4f%n",
                    n, waktuTotal, waktuMundur, waktuTerpanjang);
        }
    }
}

class Lagu {
    private String judul;
    private String artis;
    private double durasi;

    Lagu(String judul, String artis, double durasi) {
        this.judul = judul;
        this.artis = artis;
        this.durasi = durasi;
    }

    String getJudul() {
        return judul;
    }

    String getArtis() {
        return artis;
    }

    double getDurasi() {
        return durasi;
    }
}

