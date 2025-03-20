public class RootDirectory {
    private final Directory root = new Directory("");

    private Directory findTarget(String target, boolean findParent) throws Exception {
        String[] directories = target.split("/");
        int size = directories.length;
        if (findParent) {
            size--;
        }
        int count = 0;
        Directory result = root;
        while (count < size) {
            result = result.subdirectories.get(directories[count]);
            if (result == null) {
                throw new Exception(directories[count]);
            }
            count++;
        }
        return result;
    }

    public void create(String target) {
        if (target.isEmpty()) {
            return;
        }
        try {
            Directory parent = findTarget(target, true);
            parent.create(target.substring(target.lastIndexOf("/") + 1), null);
        } catch (Exception e) {
            System.out.println("Cannot create " + target + " - " + e.getMessage() + " does not exist");
        }
    }

    public void list() {
        root.list(0);
    }

    public void move(String source, String destination) {
        if (source.isEmpty() || destination.isEmpty()) {
            return;
        }
        try {
            Directory sourceParentDir = findTarget(source, true);
            Directory destinationDir = findTarget(destination, false);
            String sourceName = source.substring(source.lastIndexOf("/") + 1);
            if (!sourceParentDir.subdirectories.containsKey(sourceName)) {
                throw new Exception(sourceName);
            }
            destinationDir.create(sourceName, sourceParentDir.subdirectories.get(sourceName));
            sourceParentDir.delete(sourceName);
        } catch (Exception e) {
            System.out.println("Cannot move " + source + " to " + destination + " - " + e.getMessage() + " does not exist");
        }
    }

    public void delete(String target) {
        if (target.isEmpty()) {
            return;
        }
        try {
            Directory parent = findTarget(target, true);
            String sourceName = target.substring(target.lastIndexOf("/") + 1);
            if (!parent.subdirectories.containsKey(sourceName)) {
                throw new Exception(sourceName);
            }
            parent.delete(sourceName);
        } catch (Exception e) {
            System.out.println("Cannot delete " + target + " - " + e.getMessage() + " does not exist");
        }
    }
}
