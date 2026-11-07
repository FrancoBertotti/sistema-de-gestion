# Checklist de Code Review – Versión Inicial

**Proyecto:** Programación 3 
**Versión revisada:** Inicial
**Fecha:** 08/11/2026 
**Revisor:** Franco Bertotti 

Estados posibles:
- Cumple
- No cumple
- No aplica
- Verificar (pendiente de revisión)

---

## 1. Diseño

| Criterio | Cumple | Observación |
|---|---|---|
| ¿Cada clase tiene una única responsabilidad? | Verificar | |
| ¿Las responsabilidades están correctamente distribuidas entre las clases? | Verificar | |
| ¿Las relaciones y dependencias entre clases están bien definidas? | Verificar | |
| ¿Se evita el acoplamiento innecesario entre componentes? | Verificar | |
| ¿La estructura general facilita la incorporación de nuevas funcionalidades? | Verificar | |

## 2. Implementación

| Criterio | Cumple | Observación |
|---|---|---|
| ¿El código cumple con los requisitos funcionales establecidos? | Verificar | |
| ¿Los métodos realizan únicamente las operaciones correspondientes a su responsabilidad? | Verificar | |
| ¿Las variables, clases y métodos tienen nombres claros y descriptivos? | Verificar | |
| ¿Se utilizan correctamente los tipos de datos y las estructuras de Java? | Verificar | |
| ¿Se evita la duplicación de código y la implementación innecesaria? | Verificar | |

## 3. Robustez

| Criterio | Cumple | Observación |
|---|---|---|
| ¿Se validan los datos de entrada antes de procesarlos? | Verificar | |
| ¿Se controlan adecuadamente las excepciones? | Verificar | |
| ¿Se contemplan valores nulos y entradas inesperadas? | Verificar | |
| ¿Se contemplan los casos límite de las operaciones? | Verificar | |
| ¿Se garantiza la liberación de recursos, conexiones y transacciones? | Verificar | |

## 4. Seguridad

| Criterio | Cumple | Observación |
|---|---|---|
| ¿Se validan los datos recibidos de fuentes externas? | Verificar | |
| ¿Se evita exponer información sensible en mensajes de error o registros (logs)? | Verificar | |
| ¿Las credenciales y configuraciones sensibles están protegidas? | Verificar | |
| ¿Se controlan correctamente los permisos de acceso cuando corresponde? | Verificar | |
| ¿Se utilizan consultas parametrizadas para prevenir inyecciones SQL? | Verificar | |

## 5. Mantenibilidad

| Criterio | Cumple | Observación |
|---|---|---|
| ¿El código mantiene un formato y estilo de escritura consistente? | Verificar | |
| ¿Los métodos tienen una complejidad razonable y son fáciles de comprender? | Verificar | |
| ¿Se evita el uso de valores mágicos mediante constantes cuando corresponde? | Verificar | |
| ¿La documentación y los comentarios ayudan a comprender las partes complejas? | Verificar | |
| ¿Existen pruebas que permitan verificar el comportamiento de las funcionalidades? | Verificar | |

---

## 6. Resultado general de la revisión

**Aspectos positivos:**
- 

**Problemas detectados:**
- 

**Mejoras recomendadas:**
- 

**Problemas críticos que requieren corrección:**
- 

**Conclusión general:**
- 

**Estado final de la revisión:**
- [ ] Aprobado
- [ ] Aprobado con observaciones
- [ ] Requiere correcciones