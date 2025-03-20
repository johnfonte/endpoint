import java.util.Map;
import java.util.TreeMap;

public class Directory {
    private final TreeMap<String, Directory> subdirectories;

    public Directory() {
        this.subdirectories = new TreeMap<>();
    }

    private Directory findSubdirectory(String operation, String target, boolean findParent) {
        String[] directories = target.split("/");
        int size = directories.length;
        if (findParent) {
            size--;
        }
        int count = 0;
        Directory result = this;
        while (count < size) {
            result = result.subdirectories.get(directories[count]);
            if (result == null) {
                printError(operation, directories[count]);
                return null;
            }
            count++;
        }
        return result;
    }

    public void create(String target) {
        if (target.isEmpty()) {
            return;
        }
        String operation = "create " + target;
        Directory parent = findSubdirectory(operation, target, true);
        if (parent == null) {
            return;
        }
        String lastDirectory = getLastDirectory(target);
        if (parent.subdirectories.containsKey(lastDirectory)) {
            printError(operation, lastDirectory, true);
            return;
        }
        subdirectories.put(lastDirectory, new Directory());
    }

    public void move(String source, String destination) {
        if (source.isEmpty() || destination.isEmpty()) {
            return;
        }
        String operation = "move " + source + " to " + destination;
        Directory sourceParentDir = findSubdirectory(operation, source, true);
        Directory destinationDir = findSubdirectory(operation, destination, false);
        String lastDirectory = getLastDirectory(source);
        if (sourceParentDir == null || destinationDir == null) {
            return;
        }
        if (!sourceParentDir.subdirectories.containsKey(lastDirectory)) {
            printError(operation, lastDirectory);
            return;
        }
        if (destinationDir.subdirectories.containsKey(lastDirectory)) {
            printError(operation, lastDirectory, true);
            return;
        }
        destinationDir.subdirectories.put(lastDirectory, sourceParentDir.subdirectories.get(lastDirectory));
        sourceParentDir.subdirectories.remove(lastDirectory);
    }

    public void delete(String target) {
        if (target.isEmpty()) {
            return;
        }
        String operation = "delete " + target;
        Directory parent = findSubdirectory(operation, target, true);
        if (parent == null) {
            return;
        }
        String lastDirectory = getLastDirectory(target);
        if (!parent.subdirectories.containsKey(lastDirectory)) {
            printError(operation, lastDirectory);
            return;
        }
        parent.subdirectories.remove(lastDirectory);
    }

    public void list() {
        list(0);
    }

    private void list(int depth) {
        for (Map.Entry<String, Directory> entry : subdirectories.entrySet()) {
            String spaces = "  ".repeat(depth);
            System.out.println(spaces + entry.getKey());
            entry.getValue().list(depth + 1);
        }
    }

    private String getLastDirectory(String target) {
        return target.substring(target.lastIndexOf("/") + 1);
    }

    private void printError(String operation, String problemDirectory, boolean exists) {
        String existsString = exists ? " already exists" : " does not exist";
        System.out.println("Cannot " + operation + " - " + problemDirectory + existsString);
    }

    private void printError(String operation, String problemDirectory) {
        printError(operation, problemDirectory, false);
    }
}
