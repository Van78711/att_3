

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator {


    public static List<Library> gen(int n) {

        List<Library> libraries = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            long libraryId = i*100;

            List<Librarian> librarians = new ArrayList<>();

            for (int j = 1; j < 5; j++) {
                librarians.add(
                        new Librarian(
                                libraryId*j*100,
                                "name_"+libraryId*j*100,
                                libraryId
                        )
                );
            }
            List<Book> books = new ArrayList<>();
            for (int j = 1; j < 7; j++) {
                long bookId = libraryId*j*1000;

                List<String> genre = new ArrayList<>();
                for (int k = 1; k < new Random().nextInt(2)+1; k++) {
                    genre.add("genre_"+k);
                }
                List<Review> reviews = new ArrayList<>();
                for (int k = 1; k < new Random().nextInt(5)+1; k++) {
                    reviews.add(
                            new Review(
                                    libraryId*j*1000+k,
                                    "text_"+libraryId*j*1000+k,
                                    new Random().nextInt(11),
                                    bookId
                            )
                    );
                }
                books.add(
                        new Book(
                                bookId,
                                "book_name_"+bookId,
                                "autor_"+new Random().nextInt(5000),
                                reviews,
                                bookId+new Random().nextInt(20),
                                libraryId
                        )
                );

            }
            libraries.add(
                    new Library(
                            libraryId,
                            "library_name_"+libraryId,
                            librarians,
                            books
                    )
            );
        }
        return libraries;
    }
}
