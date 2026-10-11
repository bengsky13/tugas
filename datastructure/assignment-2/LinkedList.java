/** Operasi dasar singly linked list buku. */
public class LinkedList {
    private Node head;
    private Node tail;
    private int size;

    /** Menambahkan buku di akhir list. */
    public void addLast(Book book) {
        Node newNode = new Node(book);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    /** Menghapus dan mengembalikan buku terakhir (pop). */
    public Book removeLast() {
        if (head == null) return null;
        if (head == tail) {
            Book result = head.data;
            head = tail = null;
            size--;
            return result;
        }
        Node current = head;
        while (current.next != tail) current = current.next;
        Book result = tail.data;
        current.next = null;
        tail = current;
        size--;
        return result;
    }

    /** Mencari buku berdasarkan kode. */
    public Book find(String code) {
        Node current = head;
        while (current != null) {
            if (current.data.getCode().equalsIgnoreCase(code)) return current.data;
            current = current.next;
        }
        return null;
    }

    /** Menampilkan seluruh buku sesuai urutan input. */
    public void display() {
        Node current = head;
        while (current != null) {
            System.out.printf("Kode: %s | Judul: %s | Penulis: %s%n",
                    current.data.getCode(), current.data.getTitle(), current.data.getAuthor());
            current = current.next;
        }
        System.out.println("Total Buku: " + size);
    }

    public int size() { return size; }
}
