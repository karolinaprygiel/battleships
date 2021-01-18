# battleships
By uruchomić grę należy:
1. Użyć komendy `mvn clean install` w katalogu głównym
2. Uruchomić program komendą `java -jar target/battleships-1.0-SNAPSHOT.jar` z odpowiednimi parametrami uruchomieniowymi

Na przykład: `java -jar target/battleships-1.0-SNAPSHOT.jar -mode server -port 6666`

### Parametry uruchomieniowe
Aplikacja obługuje następujące parametry:
* `-mode [server|client]` - wskazuje tryb działania (jako serwer: przyjmuje połączenie, jako klient: nawiązuje połączenie z serwerem)
* `-port N` - port, na którym aplikacja ma się komunikować.
