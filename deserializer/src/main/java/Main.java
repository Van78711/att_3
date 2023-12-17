import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        List<Library> libraries = Deserialazer.get("libraries.json");

        for (Library l: libraries) {
            System.out.println(l.getId());
            System.out.println(l.getName());
            System.out.println(l.getLibrarians());

            for (Book b:l.getBooks()) {
                System.out.println(b.getId());
                System.out.println(b.getName());
                System.out.println(b.getAutor());

                System.out.println(b.getReviews());
                System.out.println(b.getReaderId());
                System.out.println(b.getLibraryId());
                System.out.println();
            }
            System.out.println();
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        }

    }
}
