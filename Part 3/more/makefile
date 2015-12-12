all:
	javac -source 1.6 -target 1.6 *.java
	jar -cvfm part3.jar manifest.txt *.class

doc:
	javadoc *.java -d ../doc/javadoc/

clean:
	rm -rf *.class part3.jar

cleandoc:
	rm -rf ../doc/javadoc/*