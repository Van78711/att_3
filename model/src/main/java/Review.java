public class Review {
    private Long id;
    private String text;
    private Integer rait;
    private Long bookId;

    public Review() {
    }

    public Review(Long id, String text, Integer rait, Long bookId) {
        this.id = id;
        this.text = text;
        this.rait = rait;
        this.bookId = bookId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getRait() {
        return rait;
    }

    public void setRait(Integer rait) {
        this.rait = rait;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", rait=" + rait +
                ", bookId=" + bookId +
                '}';
    }
}
