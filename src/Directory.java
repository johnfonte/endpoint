import java.util.Map;
import java.util.TreeMap;

public class Directory {
    String name;
    TreeMap<String, Directory> subdirectories;

    public Directory(String name) {
        this.name = name;
        this.subdirectories = new TreeMap<>();
    }

    public void create(String directory, Directory existing) throws Exception {
        if (directory.isEmpty()) {
            return;
        }
        if (subdirectories.containsKey(directory)) {
            throw new Exception(directory);
        } else {
            if (existing == null) {
                subdirectories.put(directory, new Directory(directory));
            } else {
                subdirectories.put(directory, existing);
            }
        }
    }

    public void list(int depth) {
        for (Map.Entry<String, Directory> entry : subdirectories.entrySet()) {
            String spaces = "  ".repeat(depth);
            System.out.println(spaces + entry.getKey());
            entry.getValue().list(depth + 1);
        }
    }

    public void delete(String directory) {
        if (directory.isEmpty()) {
            return;
        }
        subdirectories.remove(directory);
    }
}
