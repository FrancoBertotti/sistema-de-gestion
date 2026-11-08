# Declaración de uso de IA

## Resumen

Se utilizaron herramientas de IA como apoyo: **ChatGPT (OpenAI)**. Las decisiones del trabajo, la ejecución de los cambios en el repositorio, las pruebas manuales, las capturas de evidencia y los commits fueron hechos por mi. La IA actuó como asistente de consulta, revisión y redacción. En varias etapas el apoyo de **medio** (se detalla abajo), y se declara así de forma explícita.

## Detalle por herramienta

| Herramienta | Etapa | Propósito | Grado de intervención | Qué hice yo |
|---|---|---|---|---|
| ChatGPT | Diagnóstico | Orientar la búsqueda de code smells y ayudar a redactar la tabla | Medio | Ejecuté los comandos, reproduje los fallos a mano y saqué las capturas |
| ChatGPT | Pruebas | Explicar pruebas de caracterización y dar ejemplos de estructura de los tests (JUnit) (honestamente no tengo un alto nivel en test, por lo que necesitaba guia para iniciar) | **Alto** | Escribí, compilé y ejecuté las pruebas; resolví los errores de compilación; interpreté el hallazgo de las líneas duplicadas |
| ChatGPT | Refactorings 1 a 4 | Guiar los pasos y revisar mis cambios y errores (errores de `setParameter`, nombres en `PedidoService`, llamada en `Main`) | **Medio** | Apliqué los cambios en el código, corrí las pruebas y repetí los escenarios de comportamiento antes y después |
| ChatGPT | Documentación | Redacción de borradores de `solid-clean-code.md`, `code-review-final.md` y las entradas de `refactorings.md` | **Bajo** | Indique la idea y lo que queria documentar para mantener una estructura profesional y agilizar la redacción, ademas, revisé, corregí con mis datos reales (que no se los pasé) y completé las evidencias |
| ChatGPT | README | Redactar el borrador a partir de los datos reales del proyecto | **Bajo** | Revisé el contenido y corregí los comandos y las secciones, mismo caso que "Documentación" |

## Lo que no hizo la IA

- No tomó decisiones de priorización: yo elegí qué refactorings hacer y cuáles dejar como mejora futura por falta de tiempo.
- No ejecutó el sistema ni grabó la evidencia: las capturas y pruebas manuales son mías.
- No realizó los commits ni los tags del repositorio.

Siempre tuve el control y la decisión. 

## Evidencia

Capturas de las conversaciones en `docs/IA/evidencia/`.

## Verificación

Todo lo que propuso la IA se contrastó ejecutando las pruebas (`./gradlew test`) y repitiendo los escenarios manuales. En varias ocasiones la IA señaló errores en mis cambios y en otras fui yo quien detectó problemas al ejecutar el sistema.