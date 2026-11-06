# README

## Descripción del Proyecto
Este es un Parcial n2 que implementa un sistema de gestión utilizando persistencia de datos utilizado por medio de la consola en Java.

## Componentes Principales

* **Entidades**: Clases que representan las tablas de la base de datos.
* **Enums**: Enumeraciones utilizadas para definir atributos con valores constantes dentro de las entidades.
* **JPAUtil**: Clase encargada de gestionar el `EntityManagerFactory` y los `EntityManager` de forma centralizada.
* **BaseRepository**: Repositorio genérico que contiene los métodos CRUD fundamentales (guardar, actualizar, eliminar, buscar por ID y listar todo) para reutilizar en otros repositorios.
* **CategoriaRepository**: Repositorio específico para la gestión y persistencia de las categorías.
* **ProductoRepository**: Repositorio específico para la gestión y persistencia de los productos.
* **Main**: Punto de entrada de la aplicación donde se inicializa el sistema y se ejecuta el menú principal.

## Menú de Consola
Al ejecutar la clase `Main`, se presentará un menú interactivo en la consola con las siguientes opciones accesibles desde el menú principal:

1.  **Gestionar Categorías** (Crear, modificar, listar, eliminar y buscar categorías).
2.  **Gestionar Productos** (Registrar, editar, listar y eliminar productos).
3.  **Salir** (Cierra la conexión de forma segura a través de `JPAUtil`).

Link video entrega final: https://youtu.be/El06G3yyJJc

Franco Bertotti