# decision-engine

Run DecisionApplication to start the application on http://localhost:8080/

Use the command ./gradlew test to run all tests

Project is running on Java 17, using Gradle and H2 Database

MockData is inserted to the database, but for testing purpose use following identityCodes:

49002010965 - debt
49002010976 - segment 1 (credit_modifier = 100)
49002010987 - segment 2 (credit_modifier = 300)
49002010998 - segment 3 (credit_modifier = 1000)

example request:
http://localhost:8080/api/apply/49002010976/2000/12
