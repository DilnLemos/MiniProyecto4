public class Producto {
    private String codigo;
    private String nombre;
    private int cantidad;

    // Constructor de la clase Producto
    public Producto(String codigo, String nombre, int cantidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    // Método para obtener el código del producto
    public String getCodigo() {
        return codigo;
    }

    // Método para obtener el nombre del producto
    public String getNombre() {
        return nombre;
    }

    // Método para obtener la cantidad del producto
    public int getCantidad() {
        return cantidad;
    }

    // Método para establecer la cantidad del producto
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    // Método toString para representar el producto como una cadena de texto
    @Override
    public String toString() {
        return codigo + "," + nombre + "," + cantidad;
    }
}