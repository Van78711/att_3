import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class SupportData {

    private Map<Class, String> sqlConvert;
    private Map<Class, List<Field>> fields;
    public SupportData(List<Class> tables) {
        initSqlConvert();
        System.out.println(fields);
        initFields(tables);
    }

    private void initSqlConvert(){
        sqlConvert = Map.of(
                Long.class, "BIGINT",
                long.class, "BIGINT",
                Integer.class, "INT",
                int.class, "INT",
                String.class, "VARCHAR(255)"
        );
    }

    private void initFields(List<Class> tables){
        fields = new HashMap<>();
        for (Class t: tables) {
            fields.put(t, SupportClass.getFields(t, this));
        }
    }

    public Map<Class, String> getSqlConvert() {
        return sqlConvert;
    }

    public Map<Class, List<Field>> getFields() {
        return fields;
    }
}
