# The fast-jar packaging results in creating an artifact that starts a little faster and consumes slightly less memory
# than a legacy Quarkus jar because it has indexed information about which dependency jar contains classes and
# resources. It can thus avoid the lookup into potentially every jar on the classpath that the legacy jar necessitates,
# when loading a class or resource.
java -Xmx128m -jar build/quarkus-app/quarkus-run.jar

# The traditional fat jar can be started using the following
#java -Xmx128m -jar build/libs/quarkus_postgres-1.0.0-SNAPSHOT.jar
