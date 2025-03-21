public interface DirectoryInterface {
    String getName();
    void create(String target);
    void move(String source, String destination);
    void delete(String target);
    void list();
}
