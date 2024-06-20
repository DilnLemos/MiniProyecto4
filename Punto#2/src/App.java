import java.util.Scanner;

public class App {
    private static Inventario inventario = new Inventario();
    private static Scanner scanner = new Scanner(System.in);

    // Método principal que controla el flujo de la aplicación
    public static void main(String[] args) {
        // Bucle principal del menú
        while (true) {
            // Mostrar el menú de opciones
            System.out.println("\n-------------------------MENÚ---------------------------");
            System.out.println("1. Agregar producto");
            System.out.println("2. Buscar producto");
            System.out.println("3. Actualizar producto");
            System.out.println("4. Eliminar producto");
            System.out.println("5. Salir");
            System.out.println("--------------------------------------------------------\n");

            // Solicitar al usuario que seleccione una opción
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consume newline después de nextInt()

            // Evaluar la opción seleccionada por el usuario
            switch (opcion) {
                case 1:
                    agregarProducto();
                    break;
                case 2:
                    buscarProducto();
                    break;
                case 3:
                    actualizarProducto();
                    break;
                case 4:
                    eliminarProducto();
                    break;
                case 5:
                    // Opción para salir del programa
                    System.out.println("Saliendo...");
                    scanner.close();  // Cerrar el Scanner antes de salir
                    return;
                default:
                    // Mensaje de opción inválida si el usuario selecciona un número fuera del rango 1-5
                    System.out.println("Opción inválida.");
            }
        }
    }

    // Método para agregar un nuevo producto al inventario
    private static void agregarProducto() {
        System.out.print("Ingrese el código del producto: ");
        String codigo = scanner.nextLine();

        System.out.print("Ingrese el nombre del producto: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese la cantidad del producto: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine();  // Consume newline después de nextInt()

        // Llamar al método agregarProducto del inventario con el nuevo Producto
        inventario.agregarProducto(new Producto(codigo, nombre, cantidad));
    }

    // Método para buscar un producto en el inventario
    private static void buscarProducto() {
        System.out.println("\n-------------------------------------------------------");
        System.out.print("Ingrese el código del producto: ");
        String codigo = scanner.nextLine();

        // Buscar el producto en el inventario y mostrar su información si se encuentra
        Producto producto = inventario.buscarProducto(codigo);
        if (producto != null) {
            System.out.println("Producto encontrado: " + producto.getNombre() + " - Cantidad: " + producto.getCantidad());
        } else {
            // Mensaje si el producto no se encuentra en el inventario
            System.out.println("Producto no encontrado.");
        }
        System.out.println("-------------------------------------------------------\n");
    }

    // Método para actualizar la cantidad de un producto en el inventario
    private static void actualizarProducto() {
        System.out.println("\n-------------------------------------------------------");
        System.out.print("Ingrese el código del producto: ");
        String codigo = scanner.nextLine();

        System.out.print("Ingrese la nueva cantidad del producto: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine();  // Consume newline después de nextInt()

        // Llamar al método actualizarProducto del inventario con el código y nueva cantidad
        inventario.actualizarProducto(codigo, cantidad);
        System.out.println("\n-------------------------------------------------------");
    }

    // Método para eliminar un producto del inventario
    private static void eliminarProducto() {
        System.out.println("\n-------------------------------------------------------");
        System.out.print("Ingrese el código del producto a eliminar: ");
        String codigo = scanner.nextLine();

        // Llamar al método eliminarProducto del inventario con el código del producto
        inventario.eliminarProducto(codigo);
        System.out.print("El producto fue eliminado. \n");
        System.out.println("-------------------------------------------------------\n");
    }
}