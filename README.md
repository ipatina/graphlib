# Graphlib

Graphlib is a simple Graph library. Available types of graph - directed and undirected.
Uses Java 8.

## Installation

Use maven to install into your local m2 repository.

```bash
mvn clean install
```

## Run test

Use maven to run tests.

```bash
mvn clean test
```

## Usage

Add as a dependency to your project.

```xml
<dependency>
    <groupId>com.ipatina</groupId>
    <artifactId>graphlib</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

Now you can create a graph.

```java
import com.ipatina.graphlib.DirectedGraph;

public class Main {
    public static void main(String[] args) {
        DirectedGraph<Integer> graph = new DirectedGraph<Integer>();
        graph.addVertex(1);
    }
}
```
