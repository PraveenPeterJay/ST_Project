# DSA Workbench

## 1. Running the Application

To start the Spring Boot application using the built-in Maven plugin:
```
mvn spring-boot:run
```

## 2. Running Unit, E2E, and Performance Testing

To clean the project and execute the default lifecycle goals, which includes compiling, running unit tests, packaging, and potentially running E2E and performance tests:
```
mvn clean
mvn clean install
```

mvn clean: Removes the target directory, ensuring a fresh build.

mvn install: Compiles source code, runs unit tests, packages the code, and installs the artifact in the local repository.

## 3. Running Mutation Testing 

To execute mutation coverage testing using the PIT Maven plugin, which assesses the effectiveness of your existing test suite:
```
mvn org.pitest:pitest-maven:mutationCoverage
```

This command is typically run after a successful build (mvn clean install) and generates a report detailing which mutations your tests failed to kill.
