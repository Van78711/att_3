import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Console {


    public void commands(){
        System.out.println("find");
        System.out.println("find all");
        System.out.println("find by id");

        System.out.println("delete");
        System.out.println("delete by id");

        System.out.println("insert");

        System.out.println("update");

        System.out.println("commands");

        System.out.println("close");
    }

    public void read(SupportData supportData, ConnectionP connection, QuaerySql quaerySql) throws Exception {
        commands();
        while (true) {
            System.out.print("command: ");
            String command = new Scanner(System.in).nextLine().trim();
            if (command.equals("commands"))
                commands();
            else if (command.equals("close")) {
                return;
            }else{
                readCommand(supportData, connection, quaerySql, command);

            }
        }

    }
    public void readCommand(SupportData supportData, ConnectionP connection, QuaerySql quaerySql, String command) throws Exception {
        System.out.println("class: ");
        String table = new Scanner(System.in).nextLine();


        if(command.equals("find")) {
            Object object = readObj(supportData, SupportClass.createObject(table));
            showResultSet(
                    supportData, connection.executeQuaery(quaerySql.find(supportData, object)), object
            );
        } else if (command.equals("find all")) {
            Object object = SupportClass.createObject(table);
            showResultSet(
                    supportData, connection.executeQuaery(quaerySql.findAll(object.getClass())), object
            );
        } else if (command.equals("find by id")) {
            System.out.print("condition id: ");
            Long id = new Scanner(System.in).nextLong();
            Object object = SupportClass.createObject(table);
            showResultSet(
                    supportData, connection.executeQuaery(quaerySql.findById(supportData, id, object.getClass())), object
            );
        } else if (command.equals("insert")) {
            Object object = readObj(supportData, SupportClass.createObject(table));
            System.out.println(object);
            connection.execute(quaerySql.insert(supportData, object));
        } else if (command.equals("delete")) {
            Object object = readObj(supportData, SupportClass.createObject(table));
            connection.execute(quaerySql.delete(supportData, object));
        } else if (command.equals("delete by id")) {
            System.out.print("condition id: ");
            Long id = new Scanner(System.in).nextLong();
            System.out.println();
            Object object = SupportClass.createObject(table);
            connection.execute(quaerySql.deleteById(supportData, id, object.getClass()));
        } else if (command.equals("update")) {
            System.out.print("condition id: ");
            Long id = new Scanner(System.in).nextLong();
            System.out.println();
            Object object = readObj(supportData, SupportClass.createObject(table));
            connection.execute(quaerySql.update(supportData, id, object.getClass()));
        }
    }

    public Object readObj(SupportData supportData, Object obj) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        for (Field f:supportData.getFields().get(obj.getClass())) {
            System.out.print(f.getName()+": ");
            String val = new Scanner(System.in).nextLine();
            if(val.equals(""))
                continue;

            if(f.getType().equals(String.class)){
                SupportClass.setValue(obj, f.getName(), val);
            } else if (f.getType().equals(Long.class) || f.getType().equals(long.class)) {
                SupportClass.setValue(obj, f.getName(), Long.parseLong(val));
            } else if (f.getType().equals(Integer.class) || f.getType().equals(int.class)) {
                SupportClass.setValue(obj, f.getName(), Integer.parseInt(val));
            }

        }
        return obj;
    }

    public void showResultSet(SupportData supportData, ResultSet resultSet, Object o) throws SQLException {
        while (resultSet.next()) {
            for (Field f : supportData.getFields().get(o.getClass())) {
                System.out.println(f.getName() + " = " + resultSet.getObject(f.getName()));
            }
            System.out.println("________________");
        }
    }
}
