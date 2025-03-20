### Endpoint Closing - Backend Coding Challenge

#### Commands for running the project
```
javac src/*.java
java -cp src Main

# or on one line

javac src/*.java && java -cp src Main
```

#### Assumptions/Decisions
 - I used TreeMap with nested nodes to store the directory structure. TreeMap maintains "natural ordering" by default.
 - I built the top level directory into a separate class, so that I didn't need to handle depth, parent nodes, and the original command string at lower levels.
 - I built a `findTarget` helper in my root class to find target nodes if they exist. This also handles missing nodes.
 - I chose to use exception handling to assist in the error output for this project. This way I can return all the way up the call stack, while adhering to the spec error format.
 - My `runTestScenario` function has the commands listed by the project spec, as well as a few more I thought were appropriate.
