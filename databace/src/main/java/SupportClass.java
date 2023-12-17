import java.io.File;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class SupportClass {

    public static Class createClass(String className) throws ClassNotFoundException {
        return Class.forName(className);
    }
    public static Object createObject(Class className) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        return createClass(className.getSimpleName()).newInstance();
    }
    public static Object createObject(String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        return createClass(className).newInstance();
    }
    public static List<Class> createClasses(List<String> classNames) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<>();
        for (String s:classNames) {
            classes.add(createClass(s));
        }
        return classes;
    }

    public static List<Class> createClasses(String path) throws ClassNotFoundException {
        return createClasses(getFileNames(path));
    }
    public static List<Field> getFields(Class c, SupportData supportData){
        return Arrays.stream(c.getDeclaredFields())
                .filter(field -> supportData.getSqlConvert().containsKey(field.getType()))
                .collect(Collectors.toList());
    }

    public static List<String> getFileNames(String path){
        File folder = new File(path);
        List<String> files = new ArrayList<>();
        for (File file : folder.listFiles()) {
            if (file.isFile()) {
                String name = file.getName();
                files.add(name.substring(0, name.lastIndexOf('.')));
            }
        }
        return files;
    }

    public static Map<String, Object> toMap(Object o, SupportData supportData) throws Exception {
        List<Field> fields = getFields(o.getClass(), supportData);

        Map<String, Object> values = new HashMap<>();
        for (Field f:fields) {

            Object v = getValue(o, f.getName());
            if(v != null){
                values.put(f.getName(), v);
            }
        }
        return values;
    }
    public static Object getValue(Object object, String fieldName) throws Exception {
        return object.getClass().getMethod(
                createMethod("get", fieldName)
        ).invoke(object);
    }
    public static void setValue(Object object, String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(object, value);
    }

    public static String createMethod(String type, String fieldName){
        return type + capitalizeFirstLetter(fieldName);
    }
    public static String capitalizeFirstLetter(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static String toSqlString(Object o){
        return (o.getClass().equals(String.class)) ? "\'%s\'".formatted(o) : o.toString();
    }


    public static String toPrimaryKey(String fieldName){
        return (fieldName.equals("id")) ? "PRIMARY KEY" : "";
    }
}
