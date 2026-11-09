# Decisiones técnicas

Estas son las decisiones principales que tomé durante el trabajo y por qué.

## 1. Escribir pruebas antes de refactorizar

Empecé con 8 pruebas de caracterización, que fijan cómo se comporta el sistema hoy. Así, después de cada refactoring pude correr `./gradlew test` y comprobar que no rompí nada. Para que las pruebas no se mezclen con los datos reales, configuré Gradle para que use una base H2 aislada que se recrea en cada ejecución.

## 2. Qué refactorings hice y por qué

Elegí cuatro, que son los que más impacto tenían en los hallazgos del diagnóstico y los que se podían verificar con las pruebas:

- **Move Class**, para ordenar los paquetes (hallazgo 4).
- **Extract Method** (`ejecutarConsulta`), para sacar el cierre del `EntityManager` repetido 9 veces (hallazgo 3).
- **Move Method + Extract Class** (`PedidoService` e `ItemPedido`), para sacar la lógica de pedidos de `Main` (hallazgos 1 y 2).
- **Introduce Parameter Object** (`SolicitudPedido`), porque `crearPedido` recibía tres datos que siempre van juntos. Es una mejora chica, pero deja el método listo para crecer.

Cada uno va en su propio commit, con las pruebas en verde antes y después, y con la prueba manual del pedido de 2 unidades para ver que el comportamiento visible no cambió.

## 3. Defectos que no corregí

Encontré varios defectos que cambian el comportamiento: los productos nuevos quedan sin disponibilidad, el programa se cae al salir de Reportes, los pedidos pierden líneas iguales, y las contraseñas se guardan en texto plano. Los documenté con evidencia pero no los arreglé dentro de los refactorings, porque un refactoring no debe cambiar lo que hace el sistema. Mezclar las dos cosas habría hecho difícil saber si un cambio de resultado venía de un error mío o de la corrección.

## 4. Qué quedó afuera por tiempo y extensión

No resolví la reflexión y el JPQL concatenado en `BaseRepository`, los valores mágicos como `-1`, ni la dependencia de clases concretas (DIP). `Main` también sigue siendo grande (1110 líneas), porque solo moví la creación de pedidos. Los dejé como mejoras futuras, ordenadas en el informe.

## 5. Uso de IA

Usé IA como apoyo y la declaré en `docs/IA/uso-de-ia.md`. Las decisiones de qué hacer y qué dejar fuera fueron mías. Todo lo que propuso la IA lo verifiqué ejecutando las pruebas o el sistema.
