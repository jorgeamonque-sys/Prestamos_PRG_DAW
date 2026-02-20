package Bibloteca;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GestorBibloteca {
    private static final int MAX_USARIOS = 50;
    private static final int MAX_PRESTAMOS = 200;
    private Usuario[] usuarios;
    private Prestamo[] prestamos;
    private int numUsuarios;
    private int numPrestamos;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    public Usuario[] getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuario[] usuarios) {
        this.usuarios = usuarios;
    }

    public Prestamo[] getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(Prestamo[] prestamos) {
        this.prestamos = prestamos;
    }

    public int getNumUsuarios() {
        return numUsuarios;
    }

    public void setNumUsuarios(int numUsuarios) {
        this.numUsuarios = numUsuarios;
    }

    public int getNumPrestamos() {
        return numPrestamos;
    }

    public void setNumPrestamos(int numPrestamos) {
        this.numPrestamos = numPrestamos;
    }



    public GestorBibloteca(){
        this.usuarios = new Usuario[MAX_USARIOS];
        this.prestamos = new Prestamo[MAX_PRESTAMOS];
        this.numUsuarios = 0;
        this.numPrestamos = 0;
    }
    public void registrarUsuario(Usuario nuevoUsuario){
        if (nuevoUsuario == null){
            throw new ExcepcionesBiblioteca.UsuarioInvalidoException(("El usuario no puede ser nulo"));
        }
        for(int i = 0; i < numUsuarios;i++){
            if(usuarios[i].getNumeroSocio().equals(nuevoUsuario.getNumeroSocio())){
                throw new ExcepcionesBiblioteca.UsuarioRepetidoException(
                        "El usuario con numero de socio " +
                        nuevoUsuario.getNumeroSocio() + " ya esta registrado");
            }
        }
        if(numUsuarios >= MAX_USARIOS){
            throw new ExcepcionesBiblioteca.UsuarioInvalidoException("No hay espacio para mas usuarios");
        }
        usuarios[numUsuarios] = nuevoUsuario;
        numUsuarios++;
    }


    public void realizarPrestamo(String codigoLibro, String tituloLibro, LocalDate
            fechaPrestamo,Usuario usuario){

        if(usuario == null){
            throw new ExcepcionesBiblioteca.UsuarioInvalidoException("El usuario no puede ser nulo");
        }
        if(usuario.estaSancionado()){
            throw new ExcepcionesBiblioteca.UsuarioSancionadoException("El usuario esta sancionado y " +
                    "no puede realizar este prestamo");
        }
        for(int i = 0; i < numPrestamos; i++){
            if(prestamos[i].getCodigoLibro().equals(codigoLibro) && prestamos[i].estaActivo()){
                throw new ExcepcionesBiblioteca.LibroNoDisponibleException("EL libro con codigo "
                + codigoLibro + " ya esta prestaod");
            }
        }
        if(numPrestamos >= MAX_PRESTAMOS){
            throw new ExcepcionesBiblioteca.PrestamoInvalidoException("No hay mas espacio para mas prestamos");
        }
        Prestamo nuevoPrestamo = new Prestamo(codigoLibro,usuario,tituloLibro,fechaPrestamo);
        prestamos[numPrestamos] = nuevoPrestamo;
        numPrestamos++;

        System.out.println("Prestamo realizado");
        System.out.println("Devolucion prevista: " + nuevoPrestamo.getFechaDevolucionPrevista().format(formatter));
    }


    public boolean delvolverLibro(String codigoLibro, LocalDate fechaDevolucion){

        if(fechaDevolucion == null){
            throw new ExcepcionesBiblioteca.PrestamoInvalidoException("La fecha de devolcion no " +
                    "puede ser anterior");
        }
        for(int i = 0; i < numPrestamos; i++){

            if(prestamos[i].getCodigoLibro().equals(codigoLibro) && prestamos[i].estaActivo()){

                prestamos[i].registrarDevolucion(fechaDevolucion);

                int diaRetraso = prestamos[i].calcularDiasRetraso();

                if(diaRetraso > 0){

                    prestamos[i].getSocio().sancionar(diaRetraso, fechaDevolucion);

                    System.out.println("Devolucion registrada con " +
                            diaRetraso + " dias de retraso");

                    System.out.println("Usuario sancionado por " +
                            diaRetraso + " dias (hasta el " +
                            fechaDevolucion.plusDays(diaRetraso)+") ");
                }
                return  true;
            }
        }
        return false;
    }


    public Usuario buscarUsuario(String numeroSocio){
        if (numeroSocio == null){
            return null;
        }
        for(int i = 0;i < numUsuarios;i++){
            if(usuarios[i].getNumeroSocio().equals(numeroSocio)){
                return usuarios[i];
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "El numero de Usarios es " + numUsuarios + "\n"
                + "El numero de Prestamos es " + numPrestamos;
    }
}

