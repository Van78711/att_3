import java.util.List;

public class Library {

    private Long id;

    private String name;
    private List<Librarian> librarians;

    private List<Book> books;

    public Library() {
    }

    public Library(Long id, String name, List<Librarian> librarians, List<Book> books) {
        this.id = id;
        this.name = name;
        this.librarians = librarians;
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Librarian> getLibrarians() {
        return librarians;
    }

    public void setLibrarians(List<Librarian> librarians) {
        this.librarians = librarians;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Library{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", librarians=" + librarians +
                ", books=" + books +
                '}';
    }
}
