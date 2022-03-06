# Online shop console project

This is the first project of EPAM Java Web Development training.

Console Application with online shop emulation. With two roles: admin and client.

## Requirements:

1. Use OOP features: classes, inheritance, polymorphism, in- encapsulation.
2. Each class should have a descriptive name and an informative composition.
3. Inheritance should only be used when it makes sense.
4. Encoding conventions must be used java code convention.
5. Classes must be correctly packaged.
6. The console menu should be minimal.
7. Multi-layer architecture (beans-entity classes, services-business logic, dao or repository-data access (first -
   database emulation with a collection, then with files), a controller-dispatcher that distributes user requests)
8. Implement a counter of created objects in the project.
9. Implement interfaces and abstract classes in the project.
10. Implement custom exceptions in the module project and their correct handling.
11. Use collections for your projects. Implement List, Set, Map.
12. Implement Comparable and Comparator.
13. Use date and time classes.
14. Implement writing / reading character and byte information to file.
15. Implement serialization in the project.
16. Implement enums.
17. Implement parameterization (generics).
18. Implement regexp.
19. Implement the entity search function.
20. Validate all data.
21. Write javadoc for classes. Generate javadoc.
22. Write unit tests using Junit and Mockito.
23. Use log4j for logging, maven for build.
24. Implement login logic in applications under different roles (admin, user), with access to different functionality
    depending on the role.
25. Programming using interfaces.

## Setup

+ Install Java SE Development Kit 11
+ Install Maven
+ Build the project with maven with the following command :

```
mvn clean install
```

+ Generate javadoc :

```
mvn javadoc:javadoc
```

+ Start app

```
java -jar .\target\online_shop-1.0-SNAPSHOT.jar
```