# Code Review Final – Versión Refactorizada

**Proyecto:** Sistema de gestión Java
**Versión revisada:** Refactorizada (tras los refactorings 1 a 4)
**Revisor:** Franco Bertotti
**Base de comparación:** docs/checklist-code-review.md (versión inicial)

## 1. Resultado por criterio

| Criterio | Inicial | Final | Observación |
|---|---|---|---|
| Responsabilidad única por clase | No cumple | Cumple parcialmente | La creación de pedidos pasó a `PedidoService`. Main sigue siendo grande (1110 líneas) porque conserva todos los menús |
| Responsabilidades bien distribuidas | No cumple | Cumple parcialmente | Aparece la capa `service` con `PedidoService`, `ItemPedido` y `SolicitudPedido`. Faltan servicios para productos y usuarios |
| Acoplamiento innecesario | No cumple | No cumple | Main sigue instanciando repositorios y servicios concretos (sin interfaces ni inyección) |
| Duplicación de código | No cumple | Cumple parcialmente | El cierre del `EntityManager` bajó de 9 a 4 apariciones (`ejecutarConsulta`). Persisten validaciones de menú repetidas |
| Estructura de paquetes | No cumple | Cumple | Una única ruta `com/tup/programacion3` |
| Pruebas automatizadas | No cumple | Cumple parcialmente | 8 pruebas en verde sobre entidades y repositorios. No hay pruebas de `PedidoService` ni de la capa de menús |
| Consultas parametrizadas | Cumple | Cumple | Se mantiene `setParameter` en todos los repositorios |
| Reflexión y JPQL concatenado | No cumple | No cumple | `eliminarLogico` y `listarActivos` siguen igual. Pendiente |
| Valores mágicos (`-1`) | No cumple | No cumple | No se abordó |
| Contraseñas en texto plano | No cumple | No cumple | No se abordó. Es el pendiente de seguridad más importante |
| Liberación de recursos | Cumple parcialmente | Cumple parcialmente | `JPAUtil.close()` sigue cerrándose al salir de Reportes |
| README actualizado | No cumple | Cumple | El README describe funcionalidades, tecnologías, requisitos, uso y estructura |

## 2. Hallazgos que NO se corrigieron (cambian el comportamiento)

Se dejaron fuera del refactoring a propósito, porque un refactoring no debe alterar el comportamiento externo:

1. Productos nuevos con `disponible = null` (diagnóstico fila 5).
2. Cierre prematuro de `JPAUtil` al salir de Reportes (fila 10).
3. Líneas de pedido iguales que se pierden por usar `Set` con `equals/hashCode` de Lombok (fila 11).
4. Contraseñas en texto plano (fila 7).

Cada uno cuenta con evidencia en el diagnóstico y queda como mejora futura, que se resolvería en commits separados.

## 3. Criterios de aceptación

- [x] Las 8 pruebas automatizadas pasan después de cada refactoring.
- [x] El comportamiento visible del alta de pedidos se mantiene (pedido de 2 unidades y caso de stock insuficiente, comparados antes y después).
- [x] Cada refactoring está registrado con su evidencia antes/después en `docs/refactorings.md`.
- [x] Ningún refactoring se mezcló con correcciones de comportamiento en un mismo commit.
- [ ] Los defectos de calidad externa (filas 5, 10 y 11) quedan para una etapa posterior.

## 4. Conclusión

La calidad **interna** mejoró en los puntos priorizados (estructura, duplicación, responsabilidades en la creación de pedidos y firmas de métodos) y quedó respaldada por pruebas. La calidad **externa** no cambió, de forma deliberada. Quedan deudas importantes: seguridad de contraseñas, defectos funcionales, reflexión, valores mágicos y acoplamiento.

**Estado final de la revisión:**
- [ ] Aprobado
- [x] Aprobado con observaciones
- [ ] Requiere correcciones
