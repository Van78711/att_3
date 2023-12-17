import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QuaerySqlImpl implements QuaerySql {

    @Override
    public String insert(SupportData supportData, Object o) throws Exception {
        StringBuilder sb = new StringBuilder();

        sb.
                append("INSERT INTO %s VALUES (".formatted(o.getClass().getSimpleName())).
                append("\n");


        List<Field> fields = supportData.getFields().get(o.getClass());

        for (int i = 0; i < fields.size(); i++) {
            String name = fields.get(i).getName();
            Object val = SupportClass.getValue(o, name);
            sb.
                    append(SupportClass.toSqlString(val)).
                    append((i != fields.size()-1) ? " , " : "\n);").
                    append("\n");
        }

        return sb.toString();

    }
    @Override
    public String update(SupportData supportData, Long id, Object o) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.
                append(String.format("UPDATE %s SET ", o.getClass().getSimpleName())).
                append("\n");

        Map<String, Object> values = SupportClass.toMap(o, supportData);
        List<String> fields = new ArrayList<>(values.keySet());

        for (int i = 0; i < fields.size(); i++) {
            String name = fields.get(i);
            String val = SupportClass.toSqlString(values.get(name));
            sb.
                    append("%s = %s".formatted(name, val)).
                    append((i != values.size()-1) ? "," : " WHERE id = %d;".formatted(id)).
                    append("\n");;

        }
        return sb.toString();
    }

    @Override
    public String delete(SupportData supportData, Object o) throws Exception {
        StringBuilder sb = new StringBuilder();
        String nl = "\n";

        sb.
                append(String.format("DELETE FROM %s WHERE", o.getClass().getSimpleName())).
                append(nl);

        Map<String, Object> values = SupportClass.toMap(o, supportData);
        List<String> fields = new ArrayList<>(values.keySet());

        for (int i = 0; i < fields.size(); i++) {
            String name = fields.get(i);
            String val = SupportClass.toSqlString(values.get(name));
            sb.
                    append("%s = %s".formatted(name, val)).
                    append((i != values.size()-1) ? " and " : ";").
                    append("\n");;

        }
        return sb.toString();
    }

    @Override
    public String deleteById(SupportData supportData, Long id, Class c) throws Exception {
        Object o = SupportClass.createObject(c);
        SupportClass.setValue(o, "id", id);
        return delete(supportData, o);
    }

    @Override
    public String find(SupportData supportData, Object o) throws Exception {
        System.out.println(o.getClass());
        StringBuilder sb = new StringBuilder();
        sb.
                append("SELECT * FROM %s WHERE ".formatted(o.getClass().getSimpleName())).
                append("\n");

        Map<String, Object> values = SupportClass.toMap(o, supportData);
        List<String> fields = new ArrayList<>(values.keySet());

        for (int i = 0; i < fields.size(); i++) {
            String name = fields.get(i);
            String val = SupportClass.toSqlString(values.get(name));
            sb.
                    append("%s = %s".formatted(name, val)).
                    append((i != values.size()-1) ? " and " : "\n);").
                    append("\n");;

        }

        return sb.toString();
    }

    @Override
    public String findById(SupportData supportData, Long id, Class c) throws Exception {
        Object o = SupportClass.createObject(c);
        SupportClass.setValue(o, "id", id);
        return find(supportData, o);
    }

    @Override
    public String create(SupportData supportData, Class c) {
        StringBuilder sb = new StringBuilder();
        sb.
                append("CREATE TABLE IF NOT EXISTS %s (".formatted(c.getSimpleName())).
                append("\n");

        List<Field> fields = supportData.getFields().get(c);
        for(int i = 0; i < fields.size(); i++) {
            Field f = fields.get(i);
            String name = f.getName();
            String type = supportData.getSqlConvert().get(f.getType());
            String primary = SupportClass.toPrimaryKey(f.getName());
            sb.
                    append("%s %s %s".formatted(name, type, primary)).
                    append((i != fields.size()-1) ? "," : "\n);").
                    append("\n");
        }
        return sb.toString();
    }

}
