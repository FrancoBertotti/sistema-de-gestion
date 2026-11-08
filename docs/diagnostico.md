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

---

# Problema
5 Productos nuevos quedan sin disponibilidad
# Dónde
Main.java (alta de productos en menuProductos y validación en altaPedido)
# Por qué es un problema
El alta no define el campo disponible, que queda en null; altaPedido rechaza los productos con disponible nulo, así que un producto recién creado no se puede pedir
# Gravedad
Alta
# Evidencia
Prueba manual: crear un producto y tratar de pedirlo, "Screenshot2,3 y 4"

---

# Problema
6 Reflexión y consultas por concatenación en BaseRepository
# Dónde
BaseRepository.java (eliminarLogico y listarActivos)
# Por qué es un problema
La reflexión oculta errores hasta que se ejecuta y evita el chequeo del compilador; el JPQL armado con strings es frágil ante cambios de nombre
# Gravedad
Media
# Evidencia
Código de BaseRepository.java, "Screenshot5"

---

# Problema
7 Contraseñas en texto plano
# Dónde
Usuario.java (campo contrasenia) y Main.java (alta y edición de usuarios)
# Por qué es un problema
Quien acceda a la base ve las contraseñas; no hay hash
# Gravedad
Alta
# Evidencia
Código y una consulta a la base mostrando el campo, "Screenshot6"

---

# Problema
8 README desactualizado
# Dónde
README.MD
# Por qué es un problema
Describe solo categorías y productos; omite usuarios, pedidos y reportes
# Gravedad
Baja
# Evidencia
README.MD

---

# Problema
9 Sin pruebas automatizadas
# Dónde
src/test/java
# Por qué es un problema
No hay forma automática de verificar el comportamiento ni de comprobar que un refactoring no lo rompió
# Gravedad
Alta
# Evidencia
Carpeta src/test/java vacía

---

# Problema
10 Cierre prematuro de EntityManagerFactory al salir de Reportes
# Dónde
Main.java (menuReportes, llamada a JPAUtil.close() al final) y JPAUtil.java
# Por qué es un problema
La fábrica de conexiones es estática y se cierra al volver al menú principal; cualquier operación posterior con la base lanza IllegalStateException y el programa termina abruptamente
# Gravedad
Alta
# Evidencia
Prueba manual: entrar a Reportes, volver con 0 y listar categorías; el programa se cae, "Screenshot7"