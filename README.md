# 🎲 Monopoly Terminal

Monopoly Terminal es una versión del clásico juego de mesa Monopoly, diseñada para ejecutarse en una terminal. Combina lógica de programación orientada a objetos en Java con persistencia de datos en MySQL, ofreciendo una experiencia interactiva por línea de comandos.

---

## 📦 Instrucciones de uso

1. **Base de datos**  
   Descarga el archivo `.sql` incluido en el proyecto.  
   Importa su contenido en tu servidor MySQL con el siguiente comando (desde consola o cliente GUI recomendado):
   ```bash
   mysql -u tu_usuario -p < nombre_archivo.sql
   ```
   
2. **Ejecutable Java**
   Descarga el archivo .jar del proyecto.
   Ejecuta el juego desde tu terminal con:
   ```bash
   java -jar MonopolyTerminal.jar
   ```

## 📝 Recomendaciones

  - Para comprender la lógica interna del proyecto, consulta la carpeta recursos y la web del proyecto donde se encuentra el UML, diagrama ER, y la documentación Javadoc.

## 🎮 Normas del Juego

  - El juego guía al usuario paso a paso mediante mensajes en la terminal.
  ⚠️ Sigue siempre las instrucciones para evitar errores durante la partida.
  
  - Introduce el número total de jugadores que se guardarán en la base de datos.
  
  - Selecciona cuántos de ellos participarán activamente en la partida (mínimo 2, máximo 4).
  
  - Elige los jugadores activos de entre los previamente registrados.
  
  - Si un jugador va a la cárcel, solo podrá salir si obtiene un 6 al tirar el dado.
  
  - Aun estando en la cárcel, se permite a otros jugadores hacer con el preso:
    
    - Intercambiar propiedades.
    
    - Comprar cartas a otros jugadores.
  
  - En el tablero:
  
    - ⬛ Las casas se representan con un cuadrado negro.
    
    - 💲 Los hoteles se indican con el símbolo del dólar.
   
## 🧰 Tecnologías Utilizadas

  - Java (POO)
  - MySQL
  - UML / ERD / Javadoc
  - Terminal / CLI
  
