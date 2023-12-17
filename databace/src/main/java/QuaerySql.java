public interface QuaerySql {
    String insert(SupportData supportData, Object o) throws Exception;
    String update(SupportData supportData, Long id, Object o) throws Exception;
    String delete(SupportData supportData, Object o) throws Exception;
    String deleteById(SupportData supportData, Long id, Class c) throws Exception;
    String find(SupportData supportData, Object o) throws Exception;
    String findById(SupportData supportData, Long id, Class c) throws Exception;
    String create(SupportData supportData, Class c);


    default String findAll(Class c){
        return "SELECT * FROM %s;".formatted(c.getSimpleName());
    }
    default String drop(SupportData supportData, Class c){
        return "DROP TABLE IF EXISTS %s;".formatted(c.getSimpleName());
    }
}
