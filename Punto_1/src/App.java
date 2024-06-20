import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Estudiante> estudiantes = new ArrayList<>();
        while (true) {
            System.out.println("1. Ingresar datos de estudiantes");
            System.out.println("0. Salir");
            int opcion;
            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); 
            } catch (InputMismatchException e) {
                System.out.println("Opción inválida.");
                scanner.nextLine(); 
                continue;
            }
            switch (opcion) {
                case 1:
                    ingresarEstudiante(scanner, estudiantes);
                    break;
                case 0:
                    System.out.println("Saliendo del programa.");
                    return;
                default:
                    System.out.println("Opción inválida. Por favor elija una opción del menú.");
            }}}

    private static void ingresarEstudiante(Scanner scanner, ArrayList<Estudiante> estudiantes) {
        System.out.println("\nIngrese los registros de estudiantes (nombre, calificacion), escriba salir para terminar:");

        while (true) {
            System.out.print("> ");
            String entrada = scanner.nextLine();

            if (entrada.equals("salir")) {
                break;
  }
            String[] partes = entrada.split(",");
            if (partes.length != 2) {
                System.out.println("Formato incorrecto debe ser (nombre, calificacion)");
                continue;
            }
            String nombre = partes[0];
            double calificacion;
            try {
                calificacion = Double.parseDouble(partes[1]);
                if (calificacion < 0.0 || calificacion > 5.0) {
                    System.out.println("La calificación debe estar entre 0.0 y 5.0");
                    continue;}
            } catch (NumberFormatException e) {
                System.out.println("La calificación debe ser un número válido");
                continue; }

            Estudiante estudiante = new Estudiante(nombre, calificacion);
            estudiantes.add(estudiante);
        }
        System.out.println("Datos de estudiantes ingresados correctamente");
    }
}