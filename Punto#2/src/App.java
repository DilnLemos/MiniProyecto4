import java.util.Scanner;

public class App {
    private static Inventario inventario = new Inventario();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            mostrarMenu();
            int opcion = leerOpcion();
            procesarOpcion(opcion);
            if (opcion == 5) {
                scanner.close();
                System.out.println("Saliendo...");
                break;
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n-------------------------MENÚ---------------------------");
        System.out.println("1. Agregar producto");
        System.out.println("2. Buscar producto");
        System.out.println("3. Actualizar producto");
        System.out.println("4. Eliminar producto");
        System.out.println("5. Salir");
        System.out.println("--------------------------------------------------------\n");
    }

    private static int leerOpcion() {
        System.out.print("Seleccione una opción: ");
        return scanner.nextInt();
    }

    private static void procesarOpcion(int opcion) {
        scanner.nextLine(); // Consumir el salto de línea después de nextInt()

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
                return; // Salir del método main() y terminar el programa
            default:
                System.out.println("Opción inválida.");
        }
    }

    private static void agregarProducto() {
        System.out.print("Ingrese el código del producto: ");
        String codigo = scanner.nextLine();

        System.out.print("Ingrese el nombre del producto: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese la cantidad del producto: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea después de nextInt()

        inventario.agregarProducto(new Producto(codigo, nombre, cantidad));
    }

    private static void buscarProducto() {
        System.out.print("Ingrese el código del producto: ");
        String codigo = scanner.nextLine();

        Producto producto = inventario.buscarProducto(codigo);
        if (producto != null) {
            System.out.println("Producto encontrado: " + producto.getNombre() + " - Cantidad: " + producto.getCantidad());
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    private static void actualizarProducto() {
        System.out.print("Ingrese el código del producto: ");
        String codigo = scanner.nextLine();

        Producto producto = inventario.buscarProducto(codigo);
        if (producto != null) {System.out.print("Ingrese la nueva cantidad del producto: ");
        int nuevaCantidad = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea después de nextInt()

        inventario.actualizarProducto(codigo, nuevaCantidad);}else {
            System.out.println("Producto no encontrado.");
        }
        
    }

    private static void eliminarProducto() {
        System.out.print("Ingrese el código del producto: ");
        String codigo = scanner.nextLine();

        Producto producto = inventario.buscarProducto(codigo);
        if (producto != null){inventario.eliminarProducto(codigo);
            System.out.println("Producto eliminado.");} else {
                System.out.println("Producto no encontrado.");  
        } 

        
    }
}