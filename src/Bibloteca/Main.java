package Bibloteca;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static int mostrarMenu(Scanner in){
        int resp = 0;
        System.out.println("=== SISTEMA GESTION BIBLIOTECA ===");
        System.out.println("1.Registrar nuevo usario");
        System.out.println("2.Realizar prestamo de libro");
        System.out.println("3.Delvolver libro");
        System.out.println("4.Consultar estado de usario");
        System.out.println("5.Mostrar prestamos activos");
        System.out.println("6.Mostrar usarios sancionados");
        System.out.println("7.Actualizar sanciones");
        System.out.println("8.salir");
        resp = Integer.parseInt(in.nextLine());
        return resp;
    }

    public static void main(String[] args) {
        Usuario u1 = new Usuario("Jorge","jorgea_monque@gmail.com","SOC00045",
                LocalDate.of(2026,07,16));

        System.out.println(u1.toString());

        System.out.println();
        System.out.println();


        Prestamo p1 = new Prestamo("JOR1234",u1,"Sancho Panza",
                LocalDate.of(2026,02,10));

        System.out.println(p1.toString());

    }
}
