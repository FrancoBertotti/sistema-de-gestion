# Problema
1 Clase con demasiadas responsabilidades
# Dónde
Main.java
# Por qué es un problema
Mezcla menús, reglas y transacciones; difícil de probar y mantener
# Gravedad
Alta
# Evidencia
1138 líneas, "Screenshot1"

---

# Problema
2 Interfaz de consola acoplada a la lógica
# Dónde
Main.java
# Por qué es un problema
202 llamadas a System.out mezcladas con las reglas de negocio; la lógica no se puede probar ni reutilizar sin la consola
# Gravedad
Alta
# Evidencia
grep -c "System.out" = 202, "Screenshot1"

---

# Problema
3 Código duplicado en el manejo del EntityManager
# Dónde
BaseRepository, PedidoRepository, ProductoRepository, UsuarioRepository y Main
# Por qué es un problema
El mismo bloque de cierre se repite 9 veces; un cambio hay que hacerlo en todos lados y es fácil olvidarse de alguno
# Gravedad
Media
# Evidencia
grep -rc "em.close()" = 9, "Screenshot1"

---

# Problema
4 Estructura de paquetes inconsistente
# Dónde
src/main/java
# Por qué es un problema
Conviven la carpeta com.tup.programacion3 (con puntos) y com/tup/programacion3, lo que confunde y puede romper la compilación o las herramientas
# Gravedad
Media
# Evidencia
Las dos rutas visibles en "Screenshot1"