import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

// Clase principal de la aplicación
public class App {
    // Método principal que ejecuta el programa
    public static void main(String[] args) {
        // Crear un escáner para leer la entrada del usuario
        Scanner scanner = new Scanner(System.in);
        // Crear una lista para almacenar los estudiantes
        ArrayList<Estudiante> estudiantes = new ArrayList<>();
        // Bucle infinito para mostrar el menú y procesar las opciones del usuario
        while (true) {
            // Mostrar las opciones del menú
            System.out.println("1. Ingresar datos de estudiantes");
            System.out.println("2. Calcular promedio de calificaciones");
            System.out.println("3. Mostrar estudiantes con calificación superior al promedio");
            System.out.println("4. Salir");

            int opcion;
            try {
                // Leer la opción seleccionada por el usuario
                opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea
            } catch (InputMismatchException e) {
                // Capturar y manejar excepciones de entrada inválida
                System.out.println("Opción inválida.");
                scanner.nextLine(); // Limpiar el buffer
                continue;
            }
            // Procesar la opción seleccionada
            switch (opcion) {
                case 1:
                    ingresarEstudiante(scanner, estudiantes);
                    break;
                case 2:
                    calcularPromedio(estudiantes);
                    break;               
                case 3:
                    mostrarSuperioresAlPromedio(estudiantes);
                    break;
                case 4:
                    System.out.println("Saliendo del programa.");
                    return; // Salir del programa
                default:
                    System.out.println("Opción inválida. Por favor elija una opción del menú.");
            }
        }
    }

    // Método para ingresar datos de estudiantes
    private static void ingresarEstudiante(Scanner scanner, ArrayList<Estudiante> estudiantes) {
        System.out.println("\nIngrese los registros de estudiantes (nombre, calificacion), escriba salir para terminar:");

        // Bucle para ingresar múltiples estudiantes
        System.out.println("Si desea regresar al menú escriba -salir-");
        while (true) {
            System.out.print("> ");
            String entrada = scanner.nextLine();

            if (entrada.equals("salir")) {
                break; // Salir del bucle si el usuario escribe "salir"
            }
            String[] partes = entrada.split(", ");
            if (partes.length != 2) {
                System.out.println("Formato incorrecto debe ser (nombre, calificacion)");
                continue;
            }
            String nombre = partes[0];
            double calificacion;
            try {
                // Convertir la calificación a un valor numérico
                calificacion = Double.parseDouble(partes[1]);
                if (calificacion < 0.0 || calificacion > 5.0) {
                    System.out.println("La calificación debe estar entre 0.0 y 5.0");
                    continue;
                }
            } catch (NumberFormatException e) {
                // Capturar y manejar excepciones de formato de número inválido
                System.out.println("La calificación debe ser un número válido");
                continue;
            }

            // Crear un nuevo objeto Estudiante y añadirlo a la lista
            Estudiante estudiante = new Estudiante(nombre, calificacion);
            estudiantes.add(estudiante);
        }
        System.out.println("Datos de estudiantes ingresados correctamente");
    }

    // Método para calcular el promedio de calificaciones
    private static void calcularPromedio(ArrayList<Estudiante> estudiantes) {
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes ");
            return;
        }
        double sumaCalificaciones = 0;
        for (Estudiante estudiante : estudiantes) {
            sumaCalificaciones += estudiante.getCalificacion();
        }
        double promedio = sumaCalificaciones / estudiantes.size();

        System.out.println("El promedio de calificaciones es: " + promedio);
    }

    // Método para mostrar estudiantes con calificación superior al promedio
    private static void mostrarSuperioresAlPromedio(ArrayList<Estudiante> estudiantes) {
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes ");
            return;
        }
        double sumaCalificaciones = 0;
        for (Estudiante estudiante : estudiantes) {
            sumaCalificaciones += estudiante.getCalificacion();
        }
        double promedio = sumaCalificaciones / estudiantes.size();

        System.out.println("Estudiantes con calificación superior al promedio:");
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getCalificacion() > promedio) {
                System.out.println(estudiante.getNombre());
            }
        }
    }
}