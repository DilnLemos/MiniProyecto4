import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;

public class Inventario {
    private HashMap<String, Producto> productos;
    private static final String ARCHIVO_INVENTARIO = "Almacen.txt";
    private static final int LONGITUD_LINEA = 50; // Longitud máxima de una línea en el archivo (ajustar según necesidad)

    /**
     * Constructor de la clase Inventario.
     * Inicializa el HashMap de productos y carga el inventario desde el archivo "Almacen.txt".
     */
    public Inventario() {
        this.productos = new HashMap<>();
        cargarInventario();
    }

    /**
     * Método privado para cargar el inventario desde el archivo "Almacen.txt".
     * Lee cada línea del archivo, crea un Producto y lo añade al HashMap de productos.
     * Si una línea no tiene el formato correcto, se imprime un mensaje de error.
     */
    private void cargarInventario() {
        try (RandomAccessFile File = new RandomAccessFile(ARCHIVO_INVENTARIO, "r")) {
            long numLineas = File.length() / LONGITUD_LINEA;
            for (int i = 0; i < numLineas; i++) {
                File.seek(i * LONGITUD_LINEA);
                String linea = File.readLine();
                if (linea != null) {
                    String[] partes = linea.split(",");
                    if (partes.length == 3) {
                        String codigo = partes[0].trim();
                        String nombre = partes[1].trim();
                        int cantidad = Integer.parseInt(partes[2].trim());
                        productos.put(codigo, new Producto(codigo, nombre, cantidad));
                    } else {
                        System.err.println("Error al leer línea del archivo: " + linea);
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
     /**
     * Método público para agregar un nuevo producto al inventario.
     * Verifica si el código del producto ya existe en el HashMap antes de agregarlo.
     * Si el código ya existe, muestra un mensaje de error y no realiza la operación.
     */
    public void agregarProducto(Producto producto) {
        if (!productos.containsKey(producto.getCodigo())) {
            productos.put(producto.getCodigo(), producto);
            guardarInventario();
            System.out.println("El producto '" + producto.getNombre() + "' fue agregado exitosamente.");
        } else {
            System.err.println("No se puede agregar el producto '" + producto.getNombre() + "'. Ya existe un producto con el mismo código.");
        }
    }

    //Método público para buscar un producto por su código.
    public Producto buscarProducto(String codigo) {
        return productos.get(codigo);
    }

    //Método público para actualizar la cantidad de un producto en el inventario.
 
    public void actualizarProducto(String codigo, int nuevaCantidad) {
        Producto producto = productos.get(codigo);
        if (producto != null) {
            producto.setCantidad(nuevaCantidad);
            guardarInventario();
        }
    }

    //Método público para eliminar un producto del inventario.
 
    public void eliminarProducto(String codigo) {
        productos.remove(codigo);
        guardarInventario();
    }

    /**
     * Método privado para guardar el inventario en el archivo "Almacen.txt".
     * Sobrescribe el archivo con los productos actuales del HashMap.
     */
    private void guardarInventario() {
        try (RandomAccessFile raf = new RandomAccessFile(ARCHIVO_INVENTARIO, "rw")) {
            raf.setLength(0);  // Limpiar el archivo antes de escribir
            for (Producto producto : productos.values()) {
                raf.writeBytes(String.format("%s,%s,%d%n", producto.getCodigo(), producto.getNombre(), producto.getCantidad()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}