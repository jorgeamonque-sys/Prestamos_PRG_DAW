package Bibloteca;
import javax.crypto.spec.PSource;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {


     private static final Scanner sc = new Scanner(System.in);
     private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static int mostrarMenu(Scanner in) {
        int resp = 0;
        boolean valido = false;

        while (!valido) {

            System.out.println("\n=== SISTEMA GESTION BIBLIOTECA ===");
            System.out.println("1.Registrar nuevo usario");
            System.out.println("2.Realizar prestamo de libro");
            System.out.println("3.Delvolver libro");
            System.out.println("4.Consultar estado de usario");
            System.out.println("5.Mostrar prestamos activos");
            System.out.println("6.Mostrar usarios sancionados");
            System.out.println("7.Actualizar sanciones");
            System.out.println("8.salir");
            System.out.println("Escribe tu opcion: ");
            try {
                resp = Integer.parseInt(in.nextLine());

                if(resp >= 1 && resp <= 8){
                    valido = true;
                }else{
                    System.out.println("Opcion fuera del rango");
                }
            }catch (NumberFormatException e){
                System.out.println("Debes introducir un numero valido");
            }
        }
        return resp;
    }
    private static void registrarUsario(GestorBibloteca gestor){
        try{
            System.out.println("Nombre: ");
            String nombre = sc.nextLine();

            System.out.println("Email: ");
            String email = sc.nextLine();

            System.out.println("Numero de socio: ");
            String numeroSocio = sc.nextLine();

            LocalDate fecha = leerFecha("Fecha registro(dd/mm/aaaa): ");

            Usuario usuario = new Usuario(nombre,email,numeroSocio,fecha);
            gestor.registrarUsuario(usuario);

            System.out.println("Usuario registrado");

        }catch (Exception e){
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private static void realizarPrestamo(GestorBibloteca gestor){
        try{
            System.out.println("Codigo libro: ");
            String codigo = sc.nextLine();

            System.out.println("Titulo: ");
            String titulo = sc.nextLine();

            System.out.println("Numero de socio: ");
            String numeroSocio = sc.nextLine();

            Usuario usuario = gestor.buscarUsuario(numeroSocio);

            if(usuario == null){
                System.out.println("ERROR: usuario no encontrado");
                return;
            }
            LocalDate fecha = leerFecha("Fecha de Prestamo (dd/mm/aaaa)");

            gestor.realizarPrestamo(codigo,titulo,fecha,usuario);

            System.out.println("Prestamo realizado");
        }catch (Exception e){
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private static void delvolverLibro(GestorBibloteca gestor){
        try{
            System.out.println("Codigo libro: ");
            String codigo = sc.nextLine();

            LocalDate fecha = leerFecha("Fecha devolucion (dd/mm/aaaa)");

            boolean devuelto = gestor.delvolverLibro(codigo,fecha);

            if (devuelto){
                System.out.println("Devolucion registrada correctamente");
            }else{
                System.out.println("No se encontro prestamo para ese libro");
            }
        }catch (Exception e){
            System.out.println("ERROR: " + e.getMessage());
        }
    }



    private static int leerEntero(String mensaje){
        while(true){
            try{
                System.out.println(mensaje);
                return Integer.parseInt(sc.nextLine());
            }catch (NumberFormatException e){
                System.out.println("Debe introducir un numero valido");
            }
        }
    }

    private static LocalDate leerFecha(String mensaje){
        while(true){
            try{
                System.out.println(mensaje);
                return LocalDate.parse(sc.nextLine(), formatter);
            }catch (DateTimeException e){
                System.out.println("Formato incorrecto. Usa dd/mm/aaaa");
            }
        }
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int opcion;
        GestorBibloteca gestor = new GestorBibloteca();

        do{
            opcion = mostrarMenu(in);

            switch (opcion){
                case 1:
                    registrarUsario(gestor);
                    break;
                case 2:
                    realizarPrestamo(gestor);
                    break;
                case 3:
                    delvolverLibro(gestor);
                    break;
                case 4:
                    consultarEstadoUsuario(gestor);
                    break;
                case 5:
                    mostrarPrestamosActivos(gestor);
                    break;
                case 6:
                    mostrarUsuariosSancionados(gestor);
                    break;
                case 7:
                    actualizarSanciones(gestor);
                    break;
                case 8:
                    System.out.println("Saliendo del sistema");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        }while(opcion != 8);
        }
    }

