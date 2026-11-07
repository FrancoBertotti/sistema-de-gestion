package com.tup.programacion3;

import com.tup.programacion3.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.EntityTransaction;
import java.util.ArrayList;
import com.tup.programacion3.DTO.UsuarioDTO;
import com.tup.programacion3.repository.UsuarioRepository;
import com.tup.programacion3.repository.PedidoRepository;
import com.tup.programacion3.model.entities.Categoria;
import com.tup.programacion3.model.entities.Producto;
import com.tup.programacion3.model.entities.Usuario;
import com.tup.programacion3.model.entities.Pedido;
import com.tup.programacion3.model.entities.DetallePedido;
import com.tup.programacion3.model.enums.FormaPago;
import com.tup.programacion3.model.enums.Estado;
import com.tup.programacion3.model.enums.Rol;
import com.tup.programacion3.repository.CategoriaRepository;
import com.tup.programacion3.repository.ProductoRepository;
import java.util.Scanner;

import java.time.LocalDate;
import java.util.*;
SonarLint
public class Main {
    private static final CategoriaRepository categoriaRepo = new CategoriaRepository();
    private static final ProductoRepository productoRepo = new ProductoRepository();
    private static final UsuarioRepository usuarioRepo = new UsuarioRepository();
    private static final PedidoRepository pedidoRepo = new PedidoRepository();
    private static final Scanner scanner = new Scanner(System.in);


    private static void cargarDatosIniciales(
            Categoria cat0, Categoria cat1, Categoria cat2) {

        //cargar si no hay categorías ya en la BD
        if (!categoriaRepo.listarActivos().isEmpty()) {
            System.out.println("Datos iniciales ya cargados, omitiendo...");
            return;
        }

        System.out.println("Cargando datos iniciales...");
        categoriaRepo.guardar(cat0);
        categoriaRepo.guardar(cat1);
        categoriaRepo.guardar(cat2);
        System.out.println("Datos iniciales guardados.");
    }
    public static void main(String[] args) {
      /*  Categoria cat0 = new Categoria();
        cat0.setNombre("Pizzas");
        cat0.setDescripcion("Pizzas artesanales con masa fresca");

        Categoria cat1 = new Categoria();
        cat1.setNombre("Hamburguesas");
        cat1.setDescripcion("Hamburguesas gourmet con ingredientes frescos");

        Categoria cat2 = new Categoria();
        cat2.setNombre("Bebidas");
        cat2.setDescripcion("Gaseosas, jugos y bebidas frías");



        Set<Producto> listaProductos = new HashSet<>();

        // pizzas
        Producto p1 = Producto.builder()
                .nombre("Pizza Muzzarella")
                .precio(4500.0)
                .descripcion("Pizza clásica con salsa de tomate y muzzarella derretida")
                .stock(20)
                .disponible(true)
                .build();

        Producto p2 = Producto.builder()
                .nombre("Pizza Napolitana")
                .precio(5200.0)
                .descripcion("Pizza con rodajas de tomate fresco, ajo y albahaca")
                .stock(15)
                .disponible(true)
                .build();
        Producto p3 = Producto.builder()
                .nombre("Pizza Especial 4 Quesos")
                .precio(6800.0)
                .descripcion("Muzzarella, provolone, roquefort y parmesano")
                .stock(10)
                .disponible(true)
                .build();
        Producto p4 = Producto.builder()
                .nombre("Pizza Fugazzeta")
                .precio(5500.0)
                .descripcion("Pizza rellena de muzzarella con cebolla caramelizada")
                .stock(10)
                .disponible(true)
                .build();

        // hambur
        Producto p5 = Producto.builder()
                .nombre("Hamburguesa Clásica")
                .precio(3800.0)
                .descripcion("Medallón de carne, lechuga, tomate, cebolla y mayo")
                .stock(30)
                .disponible(true)
                .build();

        Producto p6 = Producto.builder()
                .nombre("Hamburguesa BBQ Bacon")
                .precio(5100.0)
                .descripcion("Doble medallón, bacon crocante y salsa barbacoa ahumada")
                .stock(25)
                .disponible(false)
                .build();

        Producto p7 = Producto.builder()
                .nombre("Hamburguesa Veggie")
                .precio(4200.0)
                .descripcion("Medallón de lentejas y garbanzo, cheddar vegano y rúcula")
                .stock(0)
                .build();

        Producto p8 = Producto.builder()
                .nombre("Hamburguesa Crispy Chicken")
                .precio(4600.0)
                .descripcion("Pollo apanado crocante, coleslaw y salsa honey mustard")
                .stock(0)
                .disponible(false)
                .build();

        // bebidas
        Producto p10 = Producto.builder()
                .nombre("Coca-Cola 500ml")
                .precio(1200.0)
                .descripcion("Gaseosa Coca-Cola fría, botella personal")
                .stock(100)
                .disponible(true)
                .build();

        Producto p11 = Producto.builder()
                .nombre("Jugo de Naranja Natural")
                .precio(1800.0)
                .descripcion("Jugo exprimido en el momento, vaso 400ml")
                .stock(40)
                .disponible(true)
                .build();

        Producto p12 = Producto.builder()
                .nombre("Agua Mineral 500ml")
                .precio(800.0)
                .descripcion("Agua mineral sin gas, botella personal")
                .stock(150)
                .disponible(true)
                .build();

        cat0.getProductos().add(p1);
        cat0.getProductos().add(p2);
        cat0.getProductos().add(p3);
        cat0.getProductos().add(p4);

        cat1.getProductos().add(p5);
        cat1.getProductos().add(p6);
        cat1.getProductos().add(p7);
        cat1.getProductos().add(p8);

        cat2.getProductos().add(p10);
        cat2.getProductos().add(p11);
        cat2.getProductos().add(p12);

        listaProductos.add(p1);
        listaProductos.add(p2);
        listaProductos.add(p3);
        listaProductos.add(p4);
        listaProductos.add(p5);
        listaProductos.add(p6);
        listaProductos.add(p7);
        listaProductos.add(p8);
        listaProductos.add(p10);
        listaProductos.add(p11);
        listaProductos.add(p12);

        //USUARIOS

        Usuario user1 = new Usuario();
        user1.setNombre("Carlos");
        user1.setApellido("Pérez");
        user1.setMail("carlos@mail.com");
        user1.setContrasenia("holahola");
        user1.setRol(Rol.USUARIO);


        Usuario user2 = new Usuario();
        user2.setNombre("Ana");
        user2.setApellido("Gómez");
        user2.setMail("ana@mail.com");
        user2.setRol(Rol.ADMIN);


        // PEDIDOS

        Pedido ped1 = new Pedido();
        ped1.setFecha(LocalDate.now());
        ped1.setEstado(Estado.PENDIENTE);
        ped1.setFormaPago(FormaPago.EFECTIVO);
        ped1.calcularTotal();

        Pedido ped2 = new Pedido();
        ped2.setFecha(LocalDate.now());
        ped2.setEstado(Estado.CONFIRMADO);
        ped2.setFormaPago(FormaPago.TARJETA);
        ped2.calcularTotal();

        Pedido ped3 = new Pedido();
        ped3.setFecha(LocalDate.now());
        ped3.setEstado(Estado.TERMINADO);
        ped3.setFormaPago(FormaPago.TRANSFERENCIA);
        ped3.calcularTotal();

        // Detalles para ped1
        DetallePedido d1 = new DetallePedido();
        d1.setCantidad(2);
        d1.setProducto(p1);
        d1.setSubtotal(2 * p1.getPrecio());
        d1.setPedido(ped1);

        DetallePedido d2 = new DetallePedido();
        d2.setCantidad(1);
        d2.setProducto(p10);
        d2.setSubtotal(1 * p10.getPrecio());
        d2.setPedido(ped1);

        ped1.getDetalles().add(d1);
        ped1.getDetalles().add(d2);
        ped1.setUsuario(user1);
        user1.getPedidos().add(ped1);

        //CARGA DE DATOS INICALES POR SI LA DB NO TIENE NADA, CON VALIDACIUON
        cargarDatosIniciales(cat0, cat1, cat2);
        */



            int opcion;
            do {
                System.out.println("\n=== MENÚ PRINCIPAL ===");
                System.out.println("1. ABM de Categorías");
                System.out.println("2. ABM de Productos");
                System.out.println("3. ABM de Usuarios");
                System.out.println("4. ABM de Pedidos");
                System.out.println("5. Reportes (Consultas)");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opción: ");
                opcion = leerEntero();

                switch (opcion) {
                    case 1: menuCategorias(); break;
                    case 2: menuProductos(); break;
                    case 3: menuUsuarios(); break;
                    case 4: menuPedidos(); break;
                    case 5: menuReportes(); break;
                    case 0: System.out.println("¡Hasta luego!");
                        JPAUtil.close();
                        break;
                    default: System.out.println("Opción inválida.");
                }
            } while (opcion != 0);
        }

        //ABM DE CATEGORIAS

        private static void menuCategorias() {
            int opcion;
            do {
                System.out.println("\n--- SUBMENU CATEGORÍAS ---");
                System.out.println("1. Alta de categoria");
                System.out.println("2. Baja Lógica de categoria");
                System.out.println("3. Modificación de categoria");
                System.out.println("4. Listado de categorias activas");
                System.out.println("0. Volver al menú principal");
                System.out.print("Seleccione una opción: ");
                opcion = leerEntero();

                switch (opcion) {
                    case 1:
                        //Alta Categoría
                        String nombre = "";
                        boolean nombreValido = false;

                        //VALIDACIÓN DEL NOMBRE VACIO
                        while (!nombreValido) {
                            System.out.print("Nombre de la categoría: ");
                            nombre = scanner.nextLine();

                            if (!nombre.trim().isEmpty()) {
                                nombreValido = true;
                            } else {
                                System.out.println("Error: El nombre de la categoría no puede estar vacío.");
                            }
                        }

                        String desc = "";
                        boolean descValida = false;

                        // VALIDACIÓN DE LA DESCRIPCIÓN VACIA
                        while (!descValida) {
                            System.out.print("Descripción de la categoría: ");
                            desc = scanner.nextLine();

                            if (!desc.trim().isEmpty()) {
                                descValida = true;
                            } else {
                                System.out.println("Error: La descripción no puede estar vacía.");
                            }
                        }

                        Categoria nuevaCat = new Categoria();
                        nuevaCat.setNombre(nombre);
                        nuevaCat.setDescripcion(desc);
                        nuevaCat.setEliminado(false);

                        Categoria guardada = categoriaRepo.guardar(nuevaCat);
                        System.out.println("Categoría creada con éxito. ID generado: " + guardada.getId() + " ,nombre: " + guardada.getNombre());
                        break;

                    case 2:
                        //Baja Lógica Categoría
                        System.out.print("Ingrese el ID de la categoría a eliminar: ");
                        Long idBaja = leerLong();
                        boolean eliminadoCat = categoriaRepo.eliminarLogico(idBaja);
                        if (eliminadoCat) {
                            System.out.println("Categoría dada de baja lógicamente con éxito.");
                        } else {
                            System.out.println("Error: No existe una categoría con el ID provisto.");
                        }
                        break;

                    case 3:
                        //Modificación Categoría
                        System.out.print("Ingrese el ID de la categoría a modificar: ");
                        Long idMod = leerLong();
                        Optional<Categoria> catOpt = categoriaRepo.buscarPorId(idMod);

                        if (catOpt.isPresent() && !catOpt.get().isEliminado()) {
                            Categoria catActual = catOpt.get();
                            System.out.println("Valores actuales -> Nombre: " + catActual.getNombre() + " | Descripción: " + catActual.getDescripcion());

                            System.out.print("Nuevo Nombre (dejar vacío para mantener): ");
                            String nuevoNom = scanner.nextLine();
                            if (!nuevoNom.trim().isEmpty()) catActual.setNombre(nuevoNom);

                            System.out.print("Nueva Descripción (dejar vacío para mantener): ");
                            String nuevaDesc = scanner.nextLine();
                            if (!nuevaDesc.trim().isEmpty()) catActual.setDescripcion(nuevaDesc);

                            categoriaRepo.guardar(catActual);
                            System.out.println("Categoría actualizada con éxito.");
                        } else {
                            System.out.println("Error: Categoría no encontrada o inactiva.");
                        }
                        break;

                    case 4:
                        //Listado Categorías
                        List<Categoria> categorias = categoriaRepo.listarActivos();
                        if (categorias.isEmpty()) {
                            System.out.println("No hay categorías activas registradas.");
                        } else {
                            System.out.println("\n--- LISTADO DE CATEGORÍAS ACTIVAS ---");
                            for (Categoria c : categorias) {
                                System.out.printf("ID: %d | Nombre: %s | Descripción: %s\n", c.getId(), c.getNombre(), c.getDescripcion());
                            }
                        }
                        break;
                }
            } while (opcion != 0);
        }

        //ABM DE PRODUCTOS

        private static void menuProductos() {
            int opcion;
            do {
                System.out.println("\n--- SUBMENU PRODUCTOS ---");
                System.out.println("1. Alta de producto");
                System.out.println("2. Baja Lógica de producto");
                System.out.println("3. Modificación de producto");
                System.out.println("4. Listado de productos activos");
                System.out.println("0. Volver al menú principal");
                System.out.print("Seleccione una opción: ");
                opcion = leerEntero();

                switch (opcion) {
                    case 1: //Alta Producto
                        List<Categoria> catsActivas = categoriaRepo.listarActivos();
                        if (catsActivas.isEmpty()) {
                            System.out.println("Error: No puedes crear un producto si no existen categorías activas.");
                            break;
                        }
                        System.out.println("--- Categorías Activas Disponibles ---");
                        for (Categoria c : catsActivas) {
                            System.out.printf("[%d] %s\n", c.getId(), c.getNombre());
                        }
                        System.out.print("Seleccione el ID de la categoría: ");
                        Long idCatSeleccionada = leerLong();

                        Optional<Categoria> catAsignada = categoriaRepo.buscarPorId(idCatSeleccionada);
                        if (!catAsignada.isPresent() || catAsignada.get().isEliminado()) {
                            System.out.println("Error: La categoría seleccionada no es válida o está dada de baja.");
                            break;
                        }

                        Categoria categoriaReal = catAsignada.get();

                        System.out.print("Nombre del producto: ");
                        String prodNom = scanner.nextLine();
                        // VALIDACION DEL PRECIO
                        double precio;
                        while (true) {
                            System.out.print("Precio: ");
                            precio = leerDouble();

                            if (precio > 0) {
                                break;
                            }
                            System.out.println("Error: El precio debe ser mayor a cero. Ingrese un valor válido.");
                        }
                        System.out.print("Descripción: ");
                        String prodDesc = scanner.nextLine();
                        //VALIDACION DEL STOCK
                        int stock;
                        while (true) {
                            System.out.print("Stock inicial: ");
                            stock = leerEntero();

                            if (stock >= 0) {
                                break;
                            }
                            System.out.println("Error: El stock no puede ser negativo. Ingrese un valor válido.");
                        }

                        Producto nuevoProd = new Producto();
                        nuevoProd.setNombre(prodNom);
                        nuevoProd.setPrecio(precio);
                        nuevoProd.setDescripcion(prodDesc);
                        nuevoProd.setStock(stock);
                        nuevoProd.setEliminado(false);
                        nuevoProd.setCategoria(catAsignada.get());

                        Producto prod = productoRepo.guardar(nuevoProd);
                        System.out.println("Producto guardado de manera exitosa. ID: " + prod.getId() + ", nombre: " + prod.getNombre() + ", categoria: "+categoriaReal.getNombre());
                        break;

                    case 2:
                        //Baja Lógica Producto
                        System.out.print("Ingrese el ID del producto a eliminar: ");
                        Long idBajaP = leerLong();
                        Optional<Producto> prodBajaOpt = productoRepo.buscarPorId(idBajaP);

                        if (!prodBajaOpt.isPresent() || prodBajaOpt.get().isEliminado()) {
                            System.out.println("Error: El producto no existe o ya está dado de baja.");
                        } else {
                            productoRepo.eliminarLogico(idBajaP);
                            System.out.println("Producto dado de baja lógicamente con éxito.");
                        }
                        break;

                    case 3:
                        //Modificación Producto
                        System.out.print("Ingrese el ID del producto a modificar: ");
                        Long idModP = leerLong();
                        Optional<Producto> prodOpt = productoRepo.buscarPorId(idModP);

                        if (prodOpt.isPresent() && !prodOpt.get().isEliminado()) {
                            Producto prodActual = prodOpt.get();
                            System.out.printf("Valores actuales -> Nombre: %s | Precio: %.2f | Stock: %d\n",
                                    prodActual.getNombre(), prodActual.getPrecio(), prodActual.getStock());

                            System.out.print("Nuevo Nombre (dejar vacío para mantener): ");
                            String nNom = scanner.nextLine();
                            if (!nNom.trim().isEmpty()) prodActual.setNombre(nNom);

                            System.out.print("Nuevo Precio (-1 para mantener): ");
                            double nPrecio = leerDouble();
                            if (nPrecio >= 0) prodActual.setPrecio(nPrecio);

                            System.out.print("Nuevo Stock (-1 para mantener): ");
                            int nStock = leerEntero();
                            if (nStock >= 0) prodActual.setStock(nStock);

                            productoRepo.guardar(prodActual);
                            System.out.println("Producto modificado con éxito.");
                        } else {
                            System.out.println("Error: Producto no encontrado o inactivo.");
                        }
                        break;

                    case 4:
                        // Lista Productos
                        List<Producto> productos = productoRepo.listarActivos();
                        if (productos.isEmpty()) {
                            System.out.println("No hay productos activos registrados.");
                        } else {
                            System.out.println("\n--- LISTADO DE PRODUCTOS ACTIVOS ---");
                            for (Producto p : productos) {
                                String nombreCat = (p.getCategoria() != null) ? p.getCategoria().getNombre() : "Sin Categoría";
                                System.out.printf("ID: %d | Nombre: %s | Precio: $%.2f | Stock: %d | Categoría: %s\n",
                                        p.getId(), p.getNombre(), p.getPrecio(), p.getStock(), nombreCat);
                            }
                        }
                        break;
                }
            } while (opcion != 0);
        }


// ABM DE USUARIOS

    private static void menuUsuarios() {
        int opcion;
        do {
            System.out.println("\n--- SUBMENU USUARIOS ---");
            System.out.println("1. Alta de usuario");
            System.out.println("2. Modificación de usuario");
            System.out.println("3. Baja lógica de usuario");
            System.out.println("4. Listado de usuarios activos");
            System.out.println("5. Buscar usuario por mail");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1: // Alta de usuario
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Apellido: ");
                    String apellido = scanner.nextLine();

                    String mail;
                    while (true) {
                        System.out.print("Mail: ");
                        mail = scanner.nextLine();

                        // Validar que el mail no esté en uso
                        if (usuarioRepo.buscarPorMail(mail).isEmpty()) {
                            break;
                        }
                        System.out.println("Error: El mail ya está en uso por otro usuario activo.");
                    }

                    System.out.print("Celular (opcional): ");
                    String celular = scanner.nextLine();

                    System.out.print("Contraseña: ");
                    String contrasenia = scanner.nextLine();

                    System.out.println("Seleccione rol:");
                    System.out.println("1. ADMIN");
                    System.out.println("2. USUARIO");
                    System.out.print("Opción: ");
                    int rolOpcion = leerEntero();

                    Rol rolSeleccionado = (rolOpcion == 1) ? Rol.ADMIN : Rol.USUARIO;

                    Usuario nuevoUsuario = new Usuario();
                    nuevoUsuario.setNombre(nombre);
                    nuevoUsuario.setApellido(apellido);
                    nuevoUsuario.setMail(mail);
                    nuevoUsuario.setCelular(celular);
                    nuevoUsuario.setContrasenia(contrasenia);
                    nuevoUsuario.setRol(rolSeleccionado);
                    nuevoUsuario.setEliminado(false);

                    Usuario usuarioGuardado = usuarioRepo.guardar(nuevoUsuario);
                    System.out.println("Usuario creado con éxito. ID: " + usuarioGuardado.getId());
                    break;

                case 2: // Modificación de usuario
                    List<Usuario> usuariosActivos = usuarioRepo.listarActivos();
                    if (usuariosActivos.isEmpty()) {
                        System.out.println("No hay usuarios activos para modificar.");
                        break;
                    }

                    System.out.println("--- Usuarios Activos ---");
                    for (Usuario u : usuariosActivos) {
                        System.out.printf("[%d] %s %s (%s)\n", u.getId(), u.getNombre(), u.getApellido(), u.getMail());
                    }

                    System.out.print("Ingrese el ID del usuario a modificar: ");
                    Long idModUser = leerLong();

                    Optional<Usuario> usuarioOptMod = usuarioRepo.buscarPorId(idModUser);
                    if (usuarioOptMod.isEmpty() || usuarioOptMod.get().isEliminado()) {
                        System.out.println("Error: Usuario no encontrado o inactivo.");
                        break;
                    }

                    Usuario usuarioMod = usuarioOptMod.get();
                    System.out.printf("Valores actuales -> Nombre: %s | Apellido: %s | Mail: %s | Celular: %s\n",
                            usuarioMod.getNombre(), usuarioMod.getApellido(), usuarioMod.getMail(), usuarioMod.getCelular());

                    System.out.print("Nuevo Nombre (dejar vacío para mantener): ");
                    String nuevoNombre = scanner.nextLine();
                    if (!nuevoNombre.trim().isEmpty()) usuarioMod.setNombre(nuevoNombre);

                    System.out.print("Nuevo Apellido (dejar vacío para mantener): ");
                    String nuevoApellido = scanner.nextLine();
                    if (!nuevoApellido.trim().isEmpty()) usuarioMod.setApellido(nuevoApellido);

                    String nuevoMail = null;
                    boolean mailValido = false;
                    while (!mailValido) {
                        System.out.print("Nuevo Mail (dejar vacío para mantener): ");
                        nuevoMail = scanner.nextLine();

                        if (nuevoMail.trim().isEmpty()) {
                            mailValido = true; // Mantiene el anterior
                        } else if (usuarioRepo.buscarPorMail(nuevoMail).isEmpty() ||
                                usuarioRepo.buscarPorMail(nuevoMail).get().getId().equals(idModUser)) {
                            // Mail disponible o es el mismo usuario
                            usuarioMod.setMail(nuevoMail);
                            mailValido = true;
                        } else {
                            System.out.println("Error: El mail ya está en uso por otro usuario.");
                        }
                    }

                    System.out.print("Nuevo Celular (dejar vacío para mantener): ");
                    String nuevoCelular = scanner.nextLine();
                    if (!nuevoCelular.trim().isEmpty()) usuarioMod.setCelular(nuevoCelular);

                    System.out.print("Nueva Contraseña (dejar vacío para mantener): ");
                    String nuevaContrasenia = scanner.nextLine();
                    if (!nuevaContrasenia.trim().isEmpty()) usuarioMod.setContrasenia(nuevaContrasenia);

                    usuarioRepo.guardar(usuarioMod);
                    System.out.println("Usuario modificado con éxito.");
                    break;

                case 3: // Baja lógica de usuario
                    System.out.print("Ingrese el ID del usuario a dar de baja: ");
                    Long idBajaUser = leerLong();

                    boolean eliminadoUser = usuarioRepo.eliminarLogico(idBajaUser);
                    if (eliminadoUser) {
                        Optional<Usuario> usuarioBaja = usuarioRepo.buscarPorId(idBajaUser);
                        if (usuarioBaja.isPresent()) {
                            System.out.printf("Usuario %s %s dado de baja lógicamente con éxito.\n",
                                    usuarioBaja.get().getNombre(), usuarioBaja.get().getApellido());
                        }
                    } else {
                        System.out.println("Error: El usuario no existe.");
                    }
                    break;

                case 4: // Listado de usuarios
                    List<Usuario> usuariosList = usuarioRepo.listarActivos();
                    if (usuariosList.isEmpty()) {
                        System.out.println("No hay usuarios activos registrados.");
                    } else {
                        System.out.println("\n--- LISTADO DE USUARIOS ACTIVOS ---");
                        for (Usuario u : usuariosList) {
                            System.out.printf("ID: %d | %s %s | Mail: %s | Rol: %s\n",
                                    u.getId(), u.getNombre(), u.getApellido(), u.getMail(), u.getRol());
                        }
                    }
                    break;

                case 5: // Buscar por mail
                    System.out.print("Ingrese el mail a buscar: ");
                    String mailBuscar = scanner.nextLine();

                    Optional<Usuario> usuarioMail = usuarioRepo.buscarPorMail(mailBuscar);
                    if (usuarioMail.isPresent()) {
                        Usuario u = usuarioMail.get();
                        System.out.printf("\n--- USUARIO ENCONTRADO ---\n");
                        System.out.printf("ID: %d\nNombre: %s %s\nMail: %s\nCelular: %s\nRol: %s\n",
                                u.getId(), u.getNombre(), u.getApellido(), u.getMail(), u.getCelular(), u.getRol());
                    } else {
                        System.out.println("No existe usuario activo con ese mail.");
                    }
                    break;
            }
        } while (opcion != 0);
    }


    // 3.8 ABM DE PEDIDOS
// ==========================================
    private static void menuPedidos() {
        int opcion;
        do {
            System.out.println("\n--- SUBMENU PEDIDOS ---");
            System.out.println("1. Alta de pedido");
            System.out.println("2. Cambiar estado de pedido");
            System.out.println("3. Baja lógica de pedido");
            System.out.println("4. Listado de pedidos activos");
            System.out.println("5. Pedidos por usuario");
            System.out.println("6. Pedidos por estado");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1: // Alta de pedido
                    altaPedido();
                    break;

                case 2: // Cambiar estado de pedido
                    System.out.print("Ingrese el ID del pedido: ");
                    Long idPedido = leerLong();

                    Optional<Pedido> pedidoOpt = pedidoRepo.buscarPorId(idPedido);
                    if (pedidoOpt.isEmpty() || pedidoOpt.get().isEliminado()) {
                        System.out.println("Error: El pedido no existe o está dado de baja.");
                        break;
                    }

                    Pedido pedidoActual = pedidoOpt.get();
                    System.out.println("Estado actual: " + pedidoActual.getEstado());
                    System.out.println("Seleccione nuevo estado:");
                    System.out.println("1. PENDIENTE");
                    System.out.println("2. CONFIRMADO");
                    System.out.println("3. TERMINADO");
                    System.out.println("4. CANCELADO");
                    System.out.print("Opción: ");
                    int estadoOpt = leerEntero();

                    Estado nuevoEstado = switch (estadoOpt) {
                        case 1 -> Estado.PENDIENTE;
                        case 2 -> Estado.CONFIRMADO;
                        case 3 -> Estado.TERMINADO;
                        case 4 -> Estado.CANCELADO;
                        default -> null;
                    };

                    if (nuevoEstado != null) {
                        pedidoActual.setEstado(nuevoEstado);
                        pedidoRepo.guardar(pedidoActual);
                        System.out.printf("Pedido %d actualizado a estado %s.\n", idPedido, nuevoEstado);
                    } else {
                        System.out.println("Opción inválida.");
                    }
                    break;

                case 3: // Baja lógica de pedido
                    System.out.print("Ingrese el ID del pedido a dar de baja: ");
                    Long idBajaPedido = leerLong();

                    boolean eliminadoPedido = pedidoRepo.eliminarLogico(idBajaPedido);
                    if (eliminadoPedido) {
                        Optional<Pedido> pedidoBaja = pedidoRepo.buscarPorId(idBajaPedido);
                        if (pedidoBaja.isPresent()) {
                            System.out.printf("Pedido ID %d (Total: $%.2f) dado de baja lógicamente con éxito.\n",
                                    idBajaPedido, pedidoBaja.get().getTotal());
                        }
                    } else {
                        System.out.println("Error: El pedido no existe.");
                    }
                    break;

                case 4: // Listado de pedidos
                    List<Pedido> pedidos = pedidoRepo.listarActivos();
                    if (pedidos.isEmpty()) {
                        System.out.println("No hay pedidos activos registrados.");
                    } else {
                        System.out.println("\n--- LISTADO DE PEDIDOS ACTIVOS ---");
                        for (Pedido p : pedidos) {
                            String nombreUsuario = (p.getUsuario() != null) ?
                                    p.getUsuario().getNombre() + " " + p.getUsuario().getApellido() : "Sin usuario";
                            System.out.printf("ID: %d | Fecha: %s | Estado: %s | Forma Pago: %s | Usuario: %s | Total: $%.2f\n",
                                    p.getId(), p.getFecha(), p.getEstado(), p.getFormaPago(), nombreUsuario, p.getTotal());
                        }
                    }
                    break;

                case 5: // Pedidos por usuario
                    listarPedidosPorUsuario();
                    break;

                case 6: // Pedidos por estado
                    pedidosPorEstado();
                    break;

            }
        } while (opcion != 0);
    }

    private static void listarPedidosPorUsuario() {
        List<Usuario> usuariosListPed = usuarioRepo.listarActivos();
        if (usuariosListPed.isEmpty()) {
            System.out.println("No hay usuarios activos disponibles.");
            return; // Usamos 'return' en lugar de 'break' porque ya no estamos en un switch
        }

        System.out.println("--- Seleccione un Usuario ---");
        for (Usuario u : usuariosListPed) {
            System.out.printf("[%d] %s %s\n", u.getId(), u.getNombre(), u.getApellido());
        }
        System.out.print("Ingrese el ID del usuario: ");
        Long idUsuarioPed = leerLong();

        List<Pedido> pedidosUsuario = pedidoRepo.buscarPorUsuario(idUsuarioPed);
        if (pedidosUsuario.isEmpty()) {
            System.out.println("No hay pedidos activos para este usuario.");
        } else {
            System.out.println("\n--- PEDIDOS DEL USUARIO ---");
            for (Pedido p : pedidosUsuario) {
                System.out.printf("ID: %d | Fecha: %s | Estado: %s | Total: $%.2f\n",
                        p.getId(), p.getFecha(), p.getEstado(), p.getTotal());
            }
        }
    }
    private static void pedidosPorEstado(){
        System.out.println("Seleccione estado:");
        System.out.println("1. PENDIENTE");
        System.out.println("2. CONFIRMADO");
        System.out.println("3. TERMINADO");
        System.out.println("4. CANCELADO");
        System.out.print("Opción: ");
        int estOpt = leerEntero();

        Estado estadoFiltro = switch (estOpt) {
            case 1 -> Estado.PENDIENTE;
            case 2 -> Estado.CONFIRMADO;
            case 3 -> Estado.TERMINADO;
            case 4 -> Estado.CANCELADO;
            default -> null;
        };

        if (estadoFiltro != null) {
            List<Pedido> pedidosEstado = pedidoRepo.buscarPorEstado(estadoFiltro);
            if (pedidosEstado.isEmpty()) {
                System.out.println("No hay pedidos activos en ese estado.");
            } else {
                System.out.printf("\n--- PEDIDOS EN ESTADO %s ---\n", estadoFiltro);
                for (Pedido p : pedidosEstado) {
                    String nombreUsuario = (p.getUsuario() != null) ?
                            p.getUsuario().getNombre() + " " + p.getUsuario().getApellido() : "Sin usuario";
                    System.out.printf("ID: %d | Fecha: %s | Usuario: %s | Total: $%.2f\n",
                            p.getId(), p.getFecha(), nombreUsuario, p.getTotal());
                }
            }
        } else {
            System.out.println("Opción inválida.");
        }
    }
    // Metodo auxiliar para alta de pedido con transacción atómica
    private static void altaPedido() {
        // 1. Listar usuarios activos
        List<Usuario> usuariosDisp = usuarioRepo.listarActivos();
        if (usuariosDisp.isEmpty()) {
            System.out.println("Error: No hay usuarios activos. Cree un usuario antes de crear pedidos.");
            return;
        }

        System.out.println("--- Seleccione un Usuario ---");
        for (Usuario u : usuariosDisp) {
            System.out.printf("[%d] %s %s\n", u.getId(), u.getNombre(), u.getApellido());
        }
        System.out.print("Ingrese el ID del usuario: ");
        Long idUsuarioSelec = leerLong();

        Optional<Usuario> usuarioSelec = usuarioRepo.buscarPorId(idUsuarioSelec);
        if (usuarioSelec.isEmpty() || usuarioSelec.get().isEliminado()) {
            System.out.println("Error: Usuario no válido.");
            return;
        }

        // 2. Seleccionar forma de pago
        System.out.println("Seleccione forma de pago:");
        System.out.println("1. TARJETA");
        System.out.println("2. TRANSFERENCIA");
        System.out.println("3. EFECTIVO");
        System.out.print("Opción: ");
        int formaPagoOpt = leerEntero();

        FormaPago formaPagoSelec = switch (formaPagoOpt) {
            case 1 -> FormaPago.TARJETA;
            case 2 -> FormaPago.TRANSFERENCIA;
            case 3 -> FormaPago.EFECTIVO;
            default -> null;
        };

        if (formaPagoSelec == null) {
            System.out.println("Error: Forma de pago inválida.");
            return;
        }

        // 3. Ingresar productos en memoria (lista temporal)
        List<ProductoTemp> productosTemp = new ArrayList<>();
        boolean agregarMas = true;

        while (agregarMas) {
            List<Producto> productosDisp = productoRepo.listarActivos();
            if (productosDisp.isEmpty()) {
                System.out.println("No hay productos disponibles.");
                break;
            }

            System.out.println("\n--- Catálogo de Productos ---");
            for (Producto p : productosDisp) {
                System.out.printf("[%d] %s - Precio: $%.2f | Stock: %d | Disponible: %s\n",
                        p.getId(), p.getNombre(), p.getPrecio(), p.getStock(), p.getDisponible() != null ? p.getDisponible() : "false");
            }

            System.out.print("Ingrese ID del producto a agregar: ");
            Long idProdSelec = leerLong();

            Optional<Producto> prodSelec = productoRepo.buscarPorId(idProdSelec);
            if (prodSelec.isEmpty() || prodSelec.get().isEliminado()) {
                System.out.println("Error: Producto no válido.");
                continue;
            }

            Producto prod = prodSelec.get();
            if (prod.getDisponible() == null || !prod.getDisponible()) {
                System.out.println("Error: El producto no está disponible.");
                continue;
            }

            System.out.print("Ingrese cantidad: ");
            int cantidad = leerEntero();

            if (cantidad <= 0) {
                System.out.println("Error: La cantidad debe ser mayor a 0.");
                continue;
            }

            if (prod.getStock() < cantidad) {
                System.out.printf("Error: Stock insuficiente. Disponible: %d\n", prod.getStock());
                continue;
            }

            // Agregar a lista temporal
            productosTemp.add(new ProductoTemp(idProdSelec, cantidad));
            System.out.println("Producto agregado al pedido.");

            System.out.print("¿Desea agregar otro producto? (s/n): ");
            String respuesta = scanner.nextLine();
            agregarMas = respuesta.equalsIgnoreCase("s");
        }

        if (productosTemp.isEmpty()) {
            System.out.println("Error: El pedido debe contener al menos un producto.");
            return;
        }

        // Transacción atómica para crear pedido
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            // Crear pedido
            Pedido nuevoPedido = new Pedido();
            nuevoPedido.setUsuario(em.merge(usuarioSelec.get()));
            nuevoPedido.setFecha(LocalDate.now());
            nuevoPedido.setEstado(Estado.PENDIENTE);
            nuevoPedido.setFormaPago(formaPagoSelec);
            nuevoPedido.setEliminado(false);

            // Procesar cada producto
            for (ProductoTemp pt : productosTemp) {
                Producto prodManaged = em.find(Producto.class, pt.idProducto);

                if (prodManaged.getStock() < pt.cantidad) {
                    throw new RuntimeException("Stock insuficiente para " + prodManaged.getNombre());
                }

                DetallePedido detalle = new DetallePedido();
                detalle.setProducto(prodManaged);
                detalle.setCantidad(pt.cantidad);
                detalle.setSubtotal(prodManaged.getPrecio() * pt.cantidad);
                detalle.setPedido(nuevoPedido);
                detalle.setEliminado(false);

                nuevoPedido.getDetalles().add(detalle);

                // Reducir stock
                prodManaged.setStock(prodManaged.getStock() - pt.cantidad);
            }

            // Calcular total
            nuevoPedido.calcularTotal();

            // Persistir (cascade persiste detalles)
            em.persist(nuevoPedido);
            tx.commit();

            // Mostrar confirmación
            System.out.println("\n=== PEDIDO CREADO EXITOSAMENTE ===");
            System.out.printf("ID: %d | Usuario: %s %s | Fecha: %s | Forma Pago: %s\n",
                    nuevoPedido.getId(),
                    usuarioSelec.get().getNombre(), usuarioSelec.get().getApellido(),
                    nuevoPedido.getFecha(), nuevoPedido.getFormaPago());
            System.out.println("\n--- Productos ---");
            for (DetallePedido d : nuevoPedido.getDetalles()) {
                System.out.printf("%d x %s = $%.2f\n", d.getCantidad(), d.getProducto().getNombre(), d.getSubtotal());
            }
            System.out.printf("\nTOTAL: $%.2f\n", nuevoPedido.getTotal());

        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            System.out.println("Error al crear pedido: " + e.getMessage());
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    // Clase auxiliar para almacenar productos temporales en el alta de pedido
    private static class ProductoTemp {
        Long idProducto;
        int cantidad;

        ProductoTemp(Long idProducto, int cantidad) {
            this.idProducto = idProducto;
            this.cantidad = cantidad;
        }
    }

        // CONSULTA JPQL
        private static void menuReportes() {
            int opcion;
            do {
                System.out.println("\n--- SUBMENU REPORTES ---");
                System.out.println("1. Productos por categoría");
                System.out.println("2. Pedidos por usuarios");
                System.out.println("3. Pedidos por estado");
                System.out.println("4. Total facturado");
                System.out.println("0. Volver al menú principal");
                System.out.print("Seleccione una opción: ");
                opcion = leerEntero();

                if (opcion == 1) {
                    //lista categorías activas
                    List<Categoria> listaCats = categoriaRepo.listarActivos();
                    if (listaCats.isEmpty()) {
                        System.out.println("No hay categorías activas disponibles para filtrar.");
                        continue;
                    }

                    System.out.println("--- Seleccione una Categoría ---");
                    for (Categoria c : listaCats) {
                        System.out.printf("[%d] %s\n", c.getId(), c.getNombre());
                    }
                    System.out.print("Ingrese el ID: ");
                    Long idSeleccionado = leerLong();

                    List<Producto> productosFiltrados = productoRepo.buscarPorCategoria(idSeleccionado);

                    // Mostrar los resultados o si esta vacio
                    if (productosFiltrados.isEmpty()) {
                        System.out.println("No hay productos registrados o activos en esa categoría.");
                    } else {
                        System.out.println("\n--- PRODUCTOS EN LA CATEGORÍA SELECCIONADA ---");
                        for (Producto p : productosFiltrados) {
                            System.out.printf("ID: %d | Nombre: %s | Precio: $%.2f | Stock: %d\n",
                                    p.getId(), p.getNombre(), p.getPrecio(), p.getStock());
                        }
                    }
                }
                if (opcion == 2) {
                    listarPedidosPorUsuario();
                }
                if (opcion == 3) {
                    pedidosPorEstado();
                }
                if (opcion == 4) {
                    List<Pedido> pedidosConfirmados = pedidoRepo.buscarPorEstado(Estado.CONFIRMADO);
                    List<Pedido> pedidosTerminados = pedidoRepo.buscarPorEstado(Estado.TERMINADO);

                    int cantConfirmados = pedidosConfirmados.size();
                    int cantTerminados = pedidosTerminados.size();
                    int totalPedidos = cantConfirmados + cantTerminados;

                    double totalFacturado = pedidosConfirmados.stream()
                            .mapToDouble(p -> p.getTotal() != null ? p.getTotal() : 0.0)
                            .sum() +
                            pedidosTerminados.stream()
                                    .mapToDouble(p -> p.getTotal() != null ? p.getTotal() : 0.0)
                                    .sum();

                    System.out.println("\n--- TOTAL FACTURADO ---");
                    System.out.println("Pedidos confirmados: "+ cantConfirmados);
                    System.out.println("Pedidos terminados: "+ cantTerminados);
                    System.out.println("Total pedidos: "+ totalPedidos);
                    System.out.println("\nTotal facturado: "+ totalFacturado);
                    break;
                }
            } while (opcion != 0);
            JPAUtil.close();
        }




        // Limpieza de buffer

        private static int leerEntero() {
            try {
                int num = scanner.nextInt();
                scanner.nextLine();
                return num;
            } catch (Exception e) {
                scanner.nextLine();
                return -1;
            }
        }

        private static Long leerLong() {
            try {
                Long num = scanner.nextLong();
                scanner.nextLine();
                return num;
            } catch (Exception e) {
                scanner.nextLine();
                return -1L;
            }
        }

        private static double leerDouble() {
            try {
                double num = scanner.nextDouble();
                scanner.nextLine();
                return num;
            } catch (Exception e) {
                scanner.nextLine();
                return -1.0;
            }
        }


    }

