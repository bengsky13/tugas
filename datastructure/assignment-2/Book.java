/** Data buku yang disimpan dalam linked list. */
public class Book {
    private final String code;
    private final String title;
    private final String author;

    public Book(String code, String title, String author) {
        this.code = code;
        this.title = title;
        this.author = author;
    }

    public String getCode() { return code; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
}
