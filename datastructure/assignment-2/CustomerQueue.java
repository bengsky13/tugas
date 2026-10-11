/** Queue pelanggan dengan prinsip FIFO. */
public class CustomerQueue {
    private CustomerNode front;
    private CustomerNode rear;
    private int size;

    public void enqueue(Customer customer) {
        CustomerNode node = new CustomerNode(customer);
        if (rear == null) front = rear = node;
        else { rear.next = node; rear = node; }
        size++;
    }

    public Customer dequeue() {
        if (front == null) return null;
        Customer result = front.data;
        front = front.next;
        if (front == null) rear = null;
        size--;
        return result;
    }

    public void display() {
        CustomerNode current = front;
        if (current == null) { System.out.println("Antrian kosong."); return; }
        while (current != null) {
            System.out.printf("Nomor: %s | Nama: %s | Total: Rp%,.2f%n",
                    current.data.getNumber(), current.data.getName(), current.data.getTotal());
            current = current.next;
        }
        System.out.println("Total antrian: " + size);
    }

    public int size() { return size; }
}
