package Bibloteca;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {


    private final Scanner sc = new Scanner(System.in);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static void mostrarMenu() {
        System.out.println("\n=== SISTEMA GESTIÓN BIBLIOTECA ===");
        System.out.println("1. Registrar nuevo usuario");
        System.out.println("2. Realizar préstamo de libro");
        System.out.println("3. Devolver libro");
        System.out.println("4. Consultar estado de usuario");
        System.out.println("5. Mostrar préstamos activos");
        System.out.println("6. Mostrar usuarios sancionados");
        System.out.println("7. Actualizar sanciones");
        System.out.println("8. Salir");

        private static void registrarUsario (GestorBibloteca gestor){
            try {
                System.out.println("Nombre: ");
                String nombre = sc.nextLine();

                System.out.println("Email: ");
                String email = sc.nextLine();

                System.out.println("Numero de socio: ");
                String numeroSocio = sc.nextLine();


            }
        }

        public static void main (String[]args){

        }
    }
}

