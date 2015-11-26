all:
	javac -source 1.6 -target 1.6 *.java
	jar -cvfm part2.jar manifest.txt *.class

doc:
	javadoc *.java -d ../doc/javadoc/

clean:
	rm -rf *.class part2.jar

cleandoc:
	rm -rf ../doc/javadoc/*