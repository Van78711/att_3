import java.util.List;

public class Book {
    private Long id;
    private String name;
    private String autor;

    private List<Review> reviews;
    private Long readerId;
    private Long libraryId;

    public Book() {
    }

    public Book(Long id, String name, String autor, List<Review> reviews, Long readerId, Long libraryId) {
        this.id = id;
        this.name = name;
        this.autor = autor;
        this.reviews = reviews;
        this.readerId = readerId;
        this.libraryId = libraryId;
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

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Long getReaderId() {
        return readerId;
    }

    public void setReaderId(Long readerId) {
        this.readerId = readerId;
    }

    public Long getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(Long libraryId) {
        this.libraryId = libraryId;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", autor='" + autor + '\'' +
                ", reviews=" + reviews +
                ", readerId=" + readerId +
                ", libraryId=" + libraryId +
                '}';
    }
}
