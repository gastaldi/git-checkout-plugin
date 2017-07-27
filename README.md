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
            ...
            <goals>
              <goal>git-sparse-checkout</goal>
            </goals>
          </execution>
        </executions>
        <configuration>
          <!-- The git repository to use -->
          <repository>git@github.com:gastaldi/git-sparse-checkout-plugin.git</repository>
          <!-- The paths to checkout -->
          <paths>
            <path>/foo/bar</path>
          </paths>
          <!-- optional: default is ${project.build.outputDirectory} -->
          <outputDirectory>/tmp</outputDirectory>
        </configuration>
      </plugin>
    </plugins>
  </build>
   ...
</project>

```
