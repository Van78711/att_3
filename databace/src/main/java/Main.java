
import java.sql.SQLException;
import java.util.List;

public class Main {
    private static List<Class> classes;
    private static ConnectionP connection;
    private static QuaerySqlImpl quaerySql;
    private static SupportData supportData;
    static {
        quaerySql = new QuaerySqlImpl();

        try {
            connection = new ConnectionP();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            classes = SupportClass.createClasses("model\\src\\main\\java");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        supportData = new SupportData(classes);

        for (Class c:classes) {
            try {
                connection.execute(quaerySql.drop(supportData, c));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        for (Class c:classes) {
            try {
                connection.execute(quaerySql.create(supportData, c));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        SupportData supportData = new SupportData(classes);

        List<Library> libraries = Deserialazer.get("libraries.json");

        for (Library l: libraries) {
            connection.execute(
                    quaerySql.insert(supportData, l)
            );
            for (Librarian lb:l.getLibrarians()) {
                connection.execute(
                        quaerySql.insert(supportData, lb)
                );
            }
            for (Book b:l.getBooks()) {
                connection.execute(
                        quaerySql.insert(supportData, b)
                );
                for (Review r:b.getReviews()) {
                    connection.execute(
                            quaerySql.insert(supportData, r)
                    );
                }
            }
        }
    }
}
