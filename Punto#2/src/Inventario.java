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
}