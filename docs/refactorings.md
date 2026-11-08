## Refactoring 1: ordenar estructura de paquetes

- **Problema que resuelve:** estructura de carpetas inconsistente (fila 4 del diagnóstico).
- **Técnica:** reubicación de archivos/paquetes (Move Class).
- **Dónde:** todo `src/main/java`.
- **Antes:** conviven `com.tup.programacion3` y `com/tup/programacion3`.
- **Después:** una única estructura `com/tup/programacion3`.
- **Verificación:** las 8 pruebas pasan antes y después (screenshot13).

## Refactoring 2: extraer ejecutarConsulta (Extract Method)

- **Problema que resuelve:** código duplicado en el manejo del EntityManager (fila 3 del diagnóstico).
- **Técnica:** Extract Method.
- **Dónde:** BaseRepository, PedidoRepository, ProductoRepository, UsuarioRepository.
- **Antes:** el bloque try/finally de cierre del EntityManager aparecía 9 veces.
- **Después:** ahora da 4 en total; screenshot15
- **Verificación:** las 8 pruebas pasan antes y después; screenshot13 y screenshot14
- **Principio relacionado:** DRY (no repetir código) y mejor cohesión de BaseRepository.

## Refactoring 3: Move Method + Extract Class (altaPedido → PedidoService)

**Code smell detectado:** God class / Long Method. `Main.altaPedido()` mezclaba interacción por consola, validación de stock, creación del Pedido y sus DetallePedido, y manejo de la transacción JPA.

**Técnica aplicada:** Move Method (la lógica de negocio pasa a `PedidoService.crearPedido`) y Extract Class (`ItemPedido`, un record que reemplaza a la clase interna `ProductoTemp`).

**Principio:** SRP. Main queda solo con la interacción por consola; `PedidoService` concentra la regla de negocio y la transacción.

**Antes:** `altaPedido()` abría el `EntityManager`, controlaba la transacción, descontaba stock y hacía rollback dentro de Main. Evidencia: Screenshot16, 17 y 18.

**Después:** `altaPedido()` solo lee los datos, arma una `List<ItemPedido>` y llama a `pedidoService.crearPedido(usuario, formaPago, items)`. Evidencia: Screenshot19 y Screenshot20.

**Verificación de comportamiento:** [pedido de 2 unidades: se imprimió la confirmación y el stock bajó 2 unidades]. [Pedido con stock insuficiente: mismo mensaje de error y el stock no cambió]. `./gradlew test`: 8/8 en verde.

**Resultado:** [Main.java quedó con 31 líneas menos]. Los `em.close()` de Main pasaron a PedidoService.