/*
## The problem

 A common method of organizing files on a computer is to store them in hierarchical directories. For instance:

 ```
 photos/
  birthdays/
      joe/
        mary/
      vacations/
    weddings/
    ```

 In this challenge, you will implement commands that allow a user to create, move and delete directories.

 A successful solution will take the following input:

```
CREATE fruits
CREATE vegetables
CREATE grains
CREATE fruits/apples
CREATE fruits/apples/fuji
LIST
CREATE grains/squash
MOVE grains/squash vegetables
CREATE foods
MOVE grains foods
MOVE fruits foods
MOVE vegetables foods
LIST
DELETE fruits/apples
DELETE foods/fruits/apples
LIST
```

 and produce the following output

 ```
 CREATE fruits
CREATE vegetables
CREATE grains
CREATE fruits/apples
CREATE fruits/apples/fuji
LIST
fruits
  apples
    fuji
grains
vegetables
CREATE grains/squash
MOVE grains/squash vegetables
CREATE foods
MOVE grains foods
MOVE fruits foods
MOVE vegetables foods
LIST
foods
  fruits
    apples
      fuji
  grains
  vegetables
    squash
DELETE fruits/apples
  Cannot delete fruits/apples - fruits does not exist
DELETE foods/fruits/apples
LIST
foods
  fruits
  grains
  vegetables
    squash
 */

import java.util.Scanner;


public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        runOnMachine();
//        runTestScenario();
    }

    public static void runTestScenario() {
        final Directory root = new Directory();
        root.create("fruits");
        root.create("vegetables");
        root.create("vegetables");
        root.create("grains");
        root.create("fruits/apples");
        root.create("fruits/apples");
        root.create("fruits/apples/fuji");
        root.create("fruits/nuts/coconuts");
        root.list();
        System.out.println();

        root.create("grains/squash");
        root.move("grains/squash", "vegetables");
        root.create("foods");
        root.move("grains", "foods");
        root.move("fruits", "foods");
        root.move("vegetables", "foods");
        root.move("blahblah", "foods");
        root.move("foods", "blahblah");
        root.list();
        System.out.println();

        root.delete("fffff");
        root.delete("fruits/apples");
        root.delete("foods/fruits/apples");
        root.list();
        System.out.println();
    }

    private static boolean commandIsValid(String[] commands) {
        return switch (commands[0]) {
            case "LIST" -> commands.length == 1;
            case "CREATE", "DELETE" -> commands.length == 2;
            case "MOVE" -> commands.length == 3;
            default -> false;
        };
    }

    public static void runOnMachine() {
        final Directory root = new Directory();
        while (true) {
            String command = scanner.nextLine();
            if (command.equals("exit")) {
                break;
            }
            String[] commands = command.split(" ");
            if (!commandIsValid(commands)) {
                System.out.println("Invalid command");
                continue;
            }
            switch (commands[0]) {
                case "CREATE":
                    root.create(commands[1]);
                    break;
                case "LIST":
                    root.list();
                    break;
                case "DELETE":
                    root.delete(commands[1]);
                    break;
                case "MOVE":
                    root.move(commands[1], commands[2]);
                    break;
                default:
                    System.out.println("Invalid command");
            }
        }
    }
}
