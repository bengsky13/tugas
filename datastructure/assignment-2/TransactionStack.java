/** Stack riwayat transaksi dengan prinsip LIFO. */
public class TransactionStack {
    private CustomerNode top;

    public void push(Customer customer) {
        CustomerNode node = new CustomerNode(customer);
        node.next = top;
        top = node;
    }

    public void display() {
        if (top == null) { System.out.println("Riwayat transaksi kosong."); return; }
        CustomerNode current = top;
        int index = 1;
        while (current != null) {
            System.out.printf("%d. Nomor: %s | Nama: %s | Total: Rp%,.2f%n", index++,
                    current.data.getNumber(), current.data.getName(), current.data.getTotal());
            current = current.next;
        }
    }
}
