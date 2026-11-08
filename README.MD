# Sistema de Gestión de Pedidos de Comida

## Descripción

Proyecto desarrollado como Trabajo Práctico Integrador (TPI) de la materia **Metodología de Sistemas II**, de la Tecnicatura Universitaria en Programación (TUP) de la Universidad Tecnológica Nacional (UTN).

Consiste en un sistema de gestión de pedidos de comida que funciona mediante una interfaz de consola con menús de texto y utiliza una base de datos para persistir la información.

El trabajo está enfocado en el análisis de la calidad del software, la identificación de deuda técnica y la aplicación de técnicas de refactoring.

## Funcionalidades

El sistema permite:

- Gestionar categorías: alta, listado, modificación y baja lógica.
- Gestionar productos: alta, listado, modificación y baja lógica.
- Gestionar usuarios: alta, listado, modificación y baja lógica.
- Gestionar pedidos: alta, listado, modificación y baja lógica.
- Consultar pedidos por usuario.
- Consultar pedidos por estado.
- Generar reportes.

## Tecnologías

- **Java:** lenguaje de programación.
- **JPA / Hibernate:** persistencia y mapeo objeto-relacional.
- **H2:** base de datos.
- **Gradle:** gestión y construcción del proyecto.
- **Gradle Wrapper:** ejecución de tareas sin necesidad de instalar Gradle manualmente.
- **Lombok:** reducción de código repetitivo.
- **JUnit:** pruebas automatizadas.

## Requisitos

Para ejecutar el proyecto se necesita:

- Java: 21.0.10.
- Git.

No es necesario instalar Gradle manualmente, ya que el proyecto incluye Gradle Wrapper.

## Instalación y uso

1. Clonar el repositorio:

   ```bash
   git clone 
   ```

2. Ingresar al directorio del proyecto:

   ```bash
   cd [NOMBRE_DEL_PROYECTO]
   ```

3. Ejecutar la aplicación:

   ```bash
   ./gradlew run
   ```

   En Windows, desde PowerShell:

   ```powershell
   .\gradlew run
   ```

4. Una vez iniciada la aplicación, utilizar los menús de texto para acceder a las distintas funcionalidades.

## Estructura del proyecto

El código fuente principal se encuentra en:

`src/main/java/com/tup/programacion3`

La organización del código es la siguiente:

```text
com/tup/programacion3/
├── model/
│   ├── entities/    # Entidades del sistema
│   └── enums/       # Enumeraciones
├── repository/      # Acceso y operaciones sobre los datos
├── service/         # Lógica de servicios
├── util/            # Clases utilitarias
└── Main.java        # Punto de entrada y menús de consola
```

## Pruebas

El proyecto utiliza **JUnit** para realizar pruebas automatizadas.

Para ejecutar las pruebas, utilizar:

```bash
./gradlew test
```

En Windows, desde PowerShell:

```powershell
.\gradlew test
```

## Documentación del TPI

La carpeta `docs/` contiene la documentación correspondiente al análisis y desarrollo del trabajo práctico:

- **Diagnóstico:** análisis del estado inicial del proyecto e identificación de problemas.
- **Pruebas:** documentación de la estrategia y los casos de prueba.
- **Refactorings:** registro de las técnicas de refactoring aplicadas.
- **SOLID y Clean Code:** análisis y aplicación de principios de diseño y buenas prácticas.
- **Code Review:** revisión de calidad del código.
- **Uso de IA:** documentación del uso de herramientas de inteligencia artificial durante el trabajo.

## Autor

**Franco Bertotti**

Trabajo Práctico Integrador — Metodología de Sistemas II  
Tecnicatura Universitaria en Programación (TUP) — UTN