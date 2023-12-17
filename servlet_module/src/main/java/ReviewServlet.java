import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/ReviewServlet")
public class ReviewServlet extends HttpServlet {

    private Class aClass = Review.class;
    private ConnectionP connection = new ConnectionP();
    private QuaerySql quaerySql = new QuaerySqlImpl();
    private SupportData supportData = new SupportData(List.of(aClass));

    public ReviewServlet() throws SQLException {
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = SupportWeb.readId(request);
        String quaery = "";
        if(id == null){
            quaery = quaerySql.findAll(aClass);
        }else {
            try {
                quaery = quaerySql.findById(supportData, id, aClass);
            } catch (Exception e) {
                response.getWriter().println("ERROR");
                throw new RuntimeException(e);
            }
        }
        ResultSet resultSet = null;
        try {
            resultSet = connection.executeQuaery(quaery);
        } catch (SQLException e) {
            response.getWriter().println("ERROR");
            throw new RuntimeException(e);
        }

        try {
            response.getWriter().println(SupportWeb.toString(supportData, aClass, resultSet));
        } catch (SQLException e) {
            response.getWriter().println("ERROR");
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Object object ;
        try {
            object = SupportWeb.read(request, supportData, aClass);
        } catch (NoSuchFieldException e) {
            response.getWriter().println("ERROR");
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            response.getWriter().println("ERROR");
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            response.getWriter().println("ERROR");
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            response.getWriter().println("ERROR");
            throw new RuntimeException(e);
        }


        String quaery = null;
        try {
            quaery = quaerySql.insert(supportData, object);
        } catch (Exception e) {
            response.getWriter().println("ERROR");
            throw new RuntimeException(e);
        }

        try {
            connection.execute(quaery);
        } catch (SQLException e) {
            response.getWriter().println("ERROR");
            throw new RuntimeException(e);
        }

        response.getWriter().println("COMPLETE");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {


        Long id = SupportWeb.readId(request);
        Object object;


        String quaery = "";

        if (id != null){
            try {
                quaery = quaerySql.deleteById(supportData, id, aClass);
            } catch (Exception e) {
                response.getWriter().println("ERROR");
                throw new RuntimeException(e);
            }
        } else {
            try {
                object = SupportWeb.read(request, supportData, aClass);
                quaery = quaerySql.delete(supportData, object);
            } catch (NoSuchFieldException e) {
                response.getWriter().println("ERROR");
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                response.getWriter().println("ERROR");
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                response.getWriter().println("ERROR");
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                response.getWriter().println("ERROR");
                throw new RuntimeException(e);
            } catch (Exception e) {
                response.getWriter().println("ERROR");
                throw new RuntimeException(e);
            }
        }


        try {
            connection.execute(quaery);
        } catch (SQLException e) {
            response.getWriter().println("ERROR");
            throw new RuntimeException(e);
        }

        response.getWriter().println("COMPLETE");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = SupportWeb.readId(request, "CONDITION");

        Object object;

        try {
            object = SupportWeb.read(request, supportData, aClass);
        } catch (NoSuchFieldException e) {
            response.getWriter().println("ERROR");
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            response.getWriter().println("ERROR");
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            response.getWriter().println("ERROR");
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            response.getWriter().println("ERROR");
            throw new RuntimeException(e);
        }
        String quaery;

        try {
            quaery = quaerySql.update(supportData, id, object);
        } catch (Exception e) {
            response.getWriter().println("ERROR");
            throw new RuntimeException(e);
        }

        try {
            connection.execute(quaery);
        } catch (SQLException e) {
            response.getWriter().println("ERROR");
            throw new RuntimeException(e);
        }

        response.getWriter().println("COMPLETE");
    }


}
