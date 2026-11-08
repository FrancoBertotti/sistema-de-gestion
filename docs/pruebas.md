# Pruebas

## 1. Objetivo

El objetivo de las pruebas es caracterizar el comportamiento actual del sistema antes de realizar tareas de refactorización. Se busca establecer una referencia verificable sobre el funcionamiento de las operaciones implementadas, identificar posibles defectos y documentar aquellos comportamientos que deban modificarse deliberadamente.

El proyecto está desarrollado en Java, utiliza JPA/Hibernate para la persistencia y H2 como base de datos. Las pruebas automatizadas se implementan con JUnit 5 y se ejecutan mediante Gradle.

## 2. Estrategia

Se utiliza una estrategia de **pruebas de caracterización**, cuyo propósito es registrar el comportamiento existente del código antes de introducir modificaciones estructurales.

Estas pruebas permiten establecer una referencia del funcionamiento actual y detectar posibles regresiones durante futuras refactorizaciones.

Se contemplan dos situaciones:

- **Comportamiento correcto:** se verifica que las operaciones produzcan los resultados esperados.
- **Comportamiento defectuoso:** se documenta el resultado actual, aunque no coincida con el comportamiento funcional deseado. Estos casos se registran como defectos y no deben corregirse como parte de una refactorización, ya que implican un cambio deliberado de comportamiento.

## 3. Casos de prueba

| Caso | Entrada | Resultado esperado | Resultado obtenido | Estado |
|---|---|---|---|---|
| `PedidoTest.calcularTotal_sumaLosSubtotalesDeLosDetalles` | Dos productos con precios de 100 y 50, y cantidades de 2 y 3 respectivamente. | Total de 350. | Total de 350. | PASA |
| Prueba de caracterización de detalles duplicados | Dos detalles con igual cantidad y subtotal, asociados a productos diferentes. | Inicialmente se esperaban 2 detalles y un total de 400. | Se conserva 1 detalle y el total es 200. | PASA (comportamiento actual documentado) |
| `CategoriaRepositoryTest.guardar_asignaIdYLaListaComoActiva` | Categoría nueva con nombre y descripción, guardada con `guardar`. | Se le asigna un id y aparece en `listarActivos`. | Se asigna el id y aparece en el listado. | PASA |
| `CategoriaRepositoryTest.eliminarLogico_laSacaDelListadoDeActivos` | Categoría guardada y dada de baja con `eliminarLogico`. | Ya no aparece en `listarActivos`. | No aparece en el listado. | PASA |
| `ProductoRepositoryTest.buscarPorCategoria_devuelveSoloLosProductosDeEsaCategoria` | Categoría con un producto asociado. | `buscarPorCategoria` devuelve 1 producto con el nombre cargado. | Devuelve 1 producto con ese nombre. | PASA |
| `UsuarioRepositoryTest.buscarPorMail_devuelveElUsuarioConEseMail` | Usuario guardado con un mail. | La búsqueda por ese mail devuelve el mismo usuario. | Devuelve el usuario con el mismo id. | PASA |
| `UsuarioRepositoryTest.buscarPorMail_conMailInexistente_devuelveVacio` | Mail que ningún usuario tiene. | `Optional` vacío. | `Optional` vacío. | PASA |
| `PedidoRepositoryTest.buscarPorEstado_devuelveLosPedidosConEseEstado` | Pedido guardado en estado PENDIENTE. | `buscarPorEstado(PENDIENTE)` incluye ese pedido. | Lo incluye. | PASA |

### 3.1. Cálculo del total de un pedido

Se verifica que el método `Pedido.calcularTotal()` sume correctamente los subtotales de los detalles asociados al pedido.

Se utilizan dos productos con los siguientes valores:

- Producto 1: precio 100, cantidad 2, subtotal 200.
- Producto 2: precio 50, cantidad 3, subtotal 150.

El resultado esperado es 350, correspondiente a la suma de ambos subtotales.

**Resultado:** la prueba pasa y el total calculado coincide con el valor esperado.

### 3.2. Comportamiento de detalles con igualdad de valores

Se plantea la hipótesis de que dos detalles de pedido con igual cantidad y subtotal pueden considerarse iguales y, por lo tanto, uno de ellos puede perderse al almacenarse en un `Set`.

Esta hipótesis se relaciona con la implementación de `equals()` de `DetallePedido`, que excluye los atributos `producto` y `pedido`.

Inicialmente, la prueba esperaba conservar los dos detalles y obtener un total de 400. Sin embargo, la ejecución produjo el resultado:

`expected 2 but was 1`

A partir de este resultado, se ajustó la prueba para documentar el comportamiento actual del sistema: se conserva un único detalle y el total calculado es 200.

**Resultado:** la prueba pasa con las expectativas ajustadas al comportamiento existente.

### 3.3. Pruebas de repositorios

Se verifican las operaciones de persistencia y consulta de los repositorios (`guardar`, `listarActivos`, `eliminarLogico`, `buscarPorCategoria`, `buscarPorEstado` y `buscarPorMail`), incluyendo el caso de búsqueda sin resultados.

Para no modificar la base de datos real, el bloque `test` de `build.gradle` ejecuta las pruebas en un directorio de trabajo propio (`build/test-db`), que se elimina y se vuelve a crear en cada ejecución. Como la URL de la base es relativa al directorio de trabajo, cada corrida usa una base nueva y vacía, sin cambiar el código del sistema.

Cada prueba prepara sus propios datos y no depende de otras. Las verificaciones comparan identificadores o campos simples, y no objetos completos, porque las entidades tienen colecciones de carga diferida.

**Resultado:** las 8 pruebas del proyecto pasan (0 fallas).

## 4. Hallazgo

Se identificó un defecto en la gestión de los detalles de un pedido.

La colección `Pedido.detalles` está implementada mediante un `Set`, que no permite almacenar elementos considerados iguales según los métodos `equals()` y `hashCode()`.

En `DetallePedido`, la implementación de igualdad excluye los atributos `producto` y `pedido`. Como consecuencia, dos detalles con igual cantidad y subtotal pueden considerarse iguales aunque correspondan a productos diferentes.

Esto provoca que uno de los detalles no se incorpore a la colección, afectando también el cálculo del total del pedido.

**Clasificación:** defecto funcional.

**Tratamiento:** se documenta el comportamiento actual mediante una prueba de caracterización. Su corrección se realizará como un cambio deliberado de comportamiento y no como parte de una refactorización, ya que modificará el resultado funcional del sistema.

## 5. Cómo ejecutar

Para ejecutar las pruebas automatizadas del proyecto, desde la raíz del repositorio se debe utilizar el siguiente comando:

```bash
./gradlew test
```

Gradle ejecutará las pruebas configuradas en el proyecto utilizando JUnit 5.

## 6. Evidencias

Se adjuntarán las siguientes capturas como evidencia de la ejecución de las pruebas:

1. Captura de la ejecución exitosa de `PedidoTest.calcularTotal_sumaLosSubtotalesDeLosDetalles`: Screenshot8.
2. Captura de la primera ejecución de la prueba de detalles duplicados, mostrando el error `expected 2 but was 1`: Screenshot9 y Screenshot10.
3. Captura del resultado general de la ejecución de `./gradlew test`: Screenshot11.
4. Captura del reporte de ejecución de las 8 pruebas (`PedidoTest` y las de repositorios), todas exitosas: Screenshot12.