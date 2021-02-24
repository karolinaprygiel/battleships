# battleships
Final project for Java Programming & Design Patterns courses conducted on Jagiellonian University.
Running the game
1. Use command `mvn clean install` in project root directory.
2. Run programm with command `java -jar target/battleships-1.0-SNAPSHOT.jar` with chosen arguments.

For example: `java -jar target/battleships-1.0-SNAPSHOT.jar -mode server -port 6666`

### command-line arguments
The application supports the following parameters:

* `-mode [server|client]` - as server: accepts connection, as client: initiate communication session with server.
* `-port N` - port on which the application is to communicate.
