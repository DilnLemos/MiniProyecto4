import java.io.*;
import java.util.HashMap;

public class Inventario {
    private HashMap<String, Producto> productos;
    private static final String ARCHIVO_INVENTARIO = "Almacen.txt";
    private static final String RUTA_ARCHIVO = System.getProperty("user.dir") + File.separator + ARCHIVO_INVENTARIO;
    private static final int LONGITUD_LINEA = 50;

    public Inventario() {
        this.productos = new HashMap<>();
        cargarInventario();
    }

    private void cargarInventario() {
        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
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
        } catch (IOException | NumberFormatException e) {
            // Si ocurre un error al cargar, simplemente imprime la excepción
            e.printStackTrace();
        }
    }

    public void agregarProducto(Producto producto) {
        if (!productos.containsKey(producto.getCodigo())) {
            productos.put(producto.getCodigo(), producto);
            guardarInventario();
            System.out.println("El producto '" + producto.getNombre() + "' fue agregado exitosamente.");
        } else {
            System.err.println("No se puede agregar el producto '" + producto.getNombre() + "'. Ya existe un producto con el mismo código.");
        }
    }

    public Producto buscarProducto(String codigo) {
        return productos.get(codigo);
    }

    public void actualizarProducto(String codigo, int nuevaCantidad) {
        Producto producto = productos.get(codigo);
        if (producto != null) {
            producto.setCantidad(nuevaCantidad);
            guardarInventario();
        }
    }

    public void eliminarProducto(String codigo) {
        productos.remove(codigo);
        guardarInventario();
    }

    private void guardarInventario() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA_ARCHIVO))) {
            for (Producto producto : productos.values()) {
                bw.write(producto.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}