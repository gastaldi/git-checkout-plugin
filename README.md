# git-checkout-plugin

A Maven plugin to perform a sparse checkout (fetch only a subtree) from a given Git repository

## POM Configuration 

```xml
<project>
  ...
  <build>
    <plugins>
       <plugin>
          <groupId>com.github.gastaldi</groupId>
          <artifactId>git-checkout-plugin</artifactId>
          <version>1.0.0.Alpha1</version>
          <executions>
             <execution>
                <id>get-docs</id>
                <phase>process-resources</phase>
                <goals>
                   <goal>git-checkout</goal>
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

### Output

```
[INFO] --- git-checkout-plugin:1.0.0-SNAPSHOT:git-checkout (get-docs) @ launchpad-addon ---
Initialized empty Git repository in /home/ggastald/workspace/launchpad/launchpad-addon/target/classes/.git/
remote: Counting objects: 2614, done.
remote: Compressing objects: 100% (5/5), done.
remote: Total 2614 (delta 0), reused 1 (delta 0), pack-reused 2609
Receiving objects: 100% (2614/2614), 2.90 MiB | 1.00 MiB/s, done.
Resolving deltas: 100% (1570/1570), done.
From github.com:openshiftio/appdev-documentation
 * branch            master     -> FETCH_HEAD
 * [new branch]      master     -> origin/master
[INFO] Files available in: /home/ggastald/workspace/launchpad/launchpad-addon/target/classes
```
