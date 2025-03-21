### Endpoint Closing - Backend Coding Challenge

#### Commands for running the project
```
javac src/*.java
java -cp src Main

# or on one line

javac src/*.java && java -cp src Main
```

#### Assumptions/Decisions
 - I used TreeMap with nested nodes to store the directory structure. TreeMap maintains "natural ordering" of its keys by default.
 - Another reason for this structure was simplicity of understanding. An alternative might be a Stack, which could be useful if backtracking was a requirement.
 - I wrote everything into one class, and kept underlying functions private so the root directory could act as the base of operations.
 - I added an interface after the fact to showcase the implemented operations, and provide future extensibility.
 - I built a `findSubdirectory` helper to find target nodes if they exist. This also handles missing nodes.
 - My `runTestScenario` function has the commands listed by the project spec, as well as a few more I thought were appropriate.
