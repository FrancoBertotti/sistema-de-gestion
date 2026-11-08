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

