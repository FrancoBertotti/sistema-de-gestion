## Refactoring 1: ordenar estructura de paquetes

- **Problema que resuelve:** estructura de carpetas inconsistente (fila 4 del diagnóstico).
- **Técnica:** reubicación de archivos/paquetes (Move Class).
- **Dónde:** todo `src/main/java`.
- **Antes:** conviven `com.tup.programacion3` y `com/tup/programacion3`.
- **Después:** una única estructura `com/tup/programacion3`.
- **Verificación:** las 8 pruebas pasan antes y después (screenshot13).
