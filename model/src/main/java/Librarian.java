public class Librarian {
    private Long id;
    private String name;
    private Long libraryId;

    public Librarian() {
    }

    public Librarian(Long id, String name, Long libraryId) {
        this.id = id;
        this.name = name;
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

    public Long getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(Long libraryId) {
        this.libraryId = libraryId;
    }

    @Override
    public String toString() {
        return "Librarian{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", libraryId=" + libraryId +
                '}';
    }
}
