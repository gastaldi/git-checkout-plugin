# git-sparse-checkout-plugin

A Maven plugin to perform a sparse checkout (fetch only a subtree) in a given repository


# POM Configuration 

```xml
<project>
  ...
  <build>
    <plugins>
       <plugin>
          <groupId>com.github.gastaldi</groupId>
          <artifactId>git-sparse-checkout-plugin</artifactId>
          <version>1.0.0-SNAPSHOT</version>
          <executions>
             <execution>
                <id>get-docs</id>
                <phase>process-resources</phase>
                <goals>
                   <goal>git-sparse-checkout</goal>
                </goals>
             </execution>
          </executions>
          <configuration>
             <!-- The git repository to use -->
             <repository>git@github.com:openshiftio/appdev-documentation.git</repository>
             <!-- The paths to checkout -->
             <paths>
                <path>docs/topics/readme</path>
             </paths>
          </configuration>
       </plugin>
    </plugins>
  </build>
   ...
</project>

```
