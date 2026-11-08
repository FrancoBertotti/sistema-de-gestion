# Checklist de Code Review – Versión Inicial

**Proyecto:** Sistema de gestión Java
**Versión revisada:** Inicial
**Tecnologías:** Java, JPA, Hibernate, H2, Gradle y Lombok
**Fecha:** 07/11/2026
**Revisor:** Franco Bertotti 

## 1. Diseño

| Criterio | Cumple | Observación |
|---|---|---|
| ¿Cada clase tiene una única responsabilidad? | No cumple | Main.java concentra más de 1100 líneas de código, incluyendo menús, validaciones y operaciones. Pedido también contiene lógica de presentación mediante System.out. Main tiene además 202 llamadas a System.out. |
| ¿Las responsabilidades están correctamente distribuidas entre las clases? | No cumple | Gran parte de la lógica de negocio y las operaciones se encuentran concentradas en Main.java. |
| ¿Las relaciones y dependencias entre clases están bien definidas? | Verificar | Las relaciones JPA están definidas, pero existen referencias bidireccionales que pueden generar recursividad en los métodos equals(), hashCode() y toString() de Lombok. |
| ¿Se evita el acoplamiento innecesario entre componentes? | No cumple | Main depende directamente de los repositorios concretos y concentra distintas responsabilidades. |
| ¿La estructura general facilita la incorporación de nuevas funcionalidades? | No cumple | La concentración de funcionalidades en Main dificulta la ampliación y modificación del sistema. |

## 2. Implementación

| Criterio | Cumple | Observación |
|---|---|---|
| ¿El código cumple con los requisitos funcionales establecidos? | Verificar | Se observan operaciones CRUD, gestión de pedidos y reportes. Es necesario contrastar su funcionamiento con los requisitos originales. |
| ¿Los métodos realizan únicamente las operaciones correspondientes a su responsabilidad? | No cumple | Hay responsabilidades mezcladas. Además, al crear productos no se establece disponible en true, lo que impide utilizarlos en pedidos. |
| ¿Las variables, clases y métodos tienen nombres claros y descriptivos? | Cumple | En general los nombres son comprensibles, aunque existen abreviaciones como nNom y nombres genéricos como getDetalle(). |
| ¿Se utilizan correctamente los tipos de datos y las estructuras de Java? | No cumple | Se utiliza Double para representar importes monetarios, cuando sería más apropiado BigDecimal. También se utiliza Boolean nullable para disponibilidad. |
| ¿Se evita la duplicación de código y la implementación innecesaria? | No cumple | Se repiten validaciones y conversiones de opciones de menú. También existe un bloque extenso de código comentado en Main.java. El bloque de cierre del EntityManager se repite 9 veces entre repositorios y Main. |
| ¿Se evita la reflexión y las consultas armadas por concatenación? | No cumple | BaseRepository.eliminarLogico usa reflexión para llamar a setEliminado, y listarActivos arma el JPQL concatenando el nombre de la clase. |

## 3. Robustez

| Criterio | Cumple | Observación |
|---|---|---|
| ¿Se validan los datos de entrada antes de procesarlos? | Cumple parcialmente | Existen validaciones de precio, stock y cantidad, pero faltan controles en campos como nombres, apellidos, descripciones y formato de correo. |
| ¿Se controlan adecuadamente las excepciones? | Cumple parcialmente | Se utilizan capturas genéricas de Exception y mensajes de error poco específicos. |
| ¿Se contemplan valores nulos y entradas inesperadas? | Cumple parcialmente | Hay controles de nulidad en algunas operaciones, pero existen accesos que podrían generar NullPointerException, como el acceso al producto de un detalle. |
| ¿Se contemplan los casos límite de las operaciones? | No cumple | Faltan controles adicionales en determinadas operaciones y no hay pruebas automatizadas que permitan comprobar los casos límite. |
| ¿Se garantiza la liberación de recursos, conexiones y transacciones? | Cumple parcialmente | Los EntityManager se cierran generalmente mediante finally, pero JPAUtil.close() se ejecuta al salir del menú de reportes, cerrando la fábrica antes de finalizar la aplicación. |

## 4. Seguridad

| Criterio | Cumple | Observación |
|---|---|---|
| ¿Se validan los datos recibidos de fuentes externas? | Cumple parcialmente | Hay validaciones parciales, pero no se controla exhaustivamente la información ingresada. |
| ¿Se evita exponer información sensible en mensajes de error o registros (logs)? | Verificar | No se observa la impresión directa de contraseñas, pero sería conveniente revisar los mensajes de excepciones y los registros de Hibernate. |
| ¿Las credenciales y configuraciones sensibles están protegidas? | No cumple | Las contraseñas de usuarios se almacenan como texto plano. Además, las credenciales de la base (admin / admin123) están escritas en persistence.xml; es una observación menor porque es una H2 local de desarrollo. |
| ¿Se controlan correctamente los permisos de acceso cuando corresponde? | No cumple | Existe una enumeración de roles ADMIN y USUARIO, pero no se implementan mecanismos de autenticación ni autorización. |
| ¿Se utilizan consultas parametrizadas para prevenir inyecciones SQL? | Cumple | Los repositorios utilizan parámetros nombrados mediante setParameter() en las consultas JPQL. |

## 5. Mantenibilidad

| Criterio | Cumple | Observación |
|---|---|---|
| ¿El código mantiene un formato y estilo de escritura consistente? | No cumple | Hay diferencias de indentación, imports innecesarios y estilos de escritura inconsistentes. |
| ¿Los métodos tienen una complejidad razonable y son fáciles de comprender? | No cumple | Main.java contiene métodos extensos con múltiples responsabilidades y lógica anidada. |
| ¿Se evita el uso de valores mágicos mediante constantes cuando corresponde? | No cumple | Se utilizan números y valores como -1 para representar opciones y errores sin centralizar su significado. |
| ¿La documentación y los comentarios ayudan a comprender las partes complejas? | No cumple | Existen comentarios redundantes y código antiguo comentado. |
| ¿Existen pruebas que permitan verificar el comportamiento de las funcionalidades? | No cumple | No se encontraron pruebas automatizadas implementadas en src/test/java. |
| ¿La estructura de paquetes y carpetas es coherente? | No cumple | Conviven las carpetas com.tup.programacion3 (con puntos) y com/tup/programacion3. |
| ¿La documentación general (README) está actualizada? | No cumple | El README describe solo categorías y productos; no menciona usuarios, pedidos ni reportes. |

---

## 6. Resultado general de la revisión

**Aspectos positivos:**
- Implementación de un BaseRepository genérico para reutilizar operaciones CRUD.
- Separación de entidades, repositorios y enumeraciones.
- Uso de Optional para manejar resultados de búsquedas.
- Uso de consultas JPQL parametrizadas.
- Uso de transacciones y cierre de EntityManager mediante bloques finally.
- Utilización de enumeraciones para representar estados y roles.

**Problemas detectados:**
- Exceso de responsabilidades concentradas en Main.java.
- Almacenamiento de contraseñas sin cifrado ni hashing seguro.
- Contraseñas de usuarios en texto plano y credenciales de la base escritas en persistence.xml (esta última, observación menor).
- Falta de autenticación y autorización por roles.
- Posibles problemas de recursividad en equals(), hashCode() y toString().
- Cierre prematuro de EntityManagerFactory.
- Productos nuevos sin disponibilidad inicial definida.
- Ausencia de pruebas automatizadas.

**Mejoras recomendadas:**
- Separar la lógica de negocio de la interfaz de consola.
- Implementar hashing seguro de contraseñas.
- Externalizar las credenciales de conexión.
- Revisar las relaciones entre entidades y las anotaciones de Lombok.
- Mejorar las validaciones de entrada.
- Centralizar el manejo de excepciones.
- Incorporar pruebas unitarias y de integración.
- Mejorar la organización y legibilidad de Main.java.

**Problemas críticos que requieren corrección:**
- Corregir el cierre prematuro de JPAUtil.
- Corregir el almacenamiento de contraseñas.
- Definir correctamente la disponibilidad inicial de los productos.

**Conclusión general:**

El proyecto presenta una estructura inicial funcional, con una separación básica entre entidades y repositorios, y utiliza correctamente algunas herramientas de persistencia de Java. Sin embargo, presenta problemas de diseño, seguridad, robustez y mantenibilidad que deberían corregirse antes de considerar estable la implementación.

**Estado final de la revisión:**
- [ ] Aprobado
- [ ] Aprobado con observaciones
- [x] Requiere correcciones