package Bibloteca;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Prestamo {
    private String codigoLibro;
    private String tituloLibro;
    private Usuario socio;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucionPrevista;
    private LocalDate fechaDevolicionReal;
    private LocalDate fechaDevolucion;


    public String getCodigoLibro() {
        return codigoLibro;
    }
    public void setCodigoLibro(String codigoLibro) {
        this.codigoLibro = codigoLibro;
    }
    public String getTituloLibro() {
        return tituloLibro;
    }
    public void setTituloLibro(String tituloLibro) {
        this.tituloLibro = tituloLibro;
    }

    public Usuario getSocio() {
        return socio;
    }

    public void setSocio(Usuario socio) {
        this.socio = socio;
    }


    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDate getFechaDevolucionPrevista() {
        return fechaDevolucionPrevista;
    }

    public void setFechaDevolucionPrevista(LocalDate fechaDevolucionPrevista) {
        this.fechaDevolucionPrevista = fechaDevolucionPrevista;
    }

    public LocalDate getFechaDevolicionReal() {
        return fechaDevolicionReal;
    }

    public void setFechaDevolicionReal(LocalDate fechaDevolicionReal) {
        this.fechaDevolicionReal = fechaDevolicionReal;
    }


    public Prestamo(String codigoLibro, Usuario socio, String tituloLibro, LocalDate fechaPrestamo){

        if (codigoLibro == null || !codigoLibro.matches("^[A-Z]{3}\\d{4}$")){
            throw new ExcepcionesBiblioteca.PrestamoInvalidoException(
                    "Código de libro inválido. Formato correcto: LIB1234"
            );
        }

        if (tituloLibro == null || tituloLibro.isBlank()) {
            throw new ExcepcionesBiblioteca.PrestamoInvalidoException(
                    "El título del libro no puede estar vacío"
            );
        }

        if (fechaPrestamo == null) {
            throw new ExcepcionesBiblioteca.PrestamoInvalidoException(
                    "La fecha de préstamo no puede ser nula"
            );
        }

        if (fechaPrestamo.isAfter(LocalDate.now())) {
            throw new ExcepcionesBiblioteca.PrestamoInvalidoException(
                    "La fecha de préstamo no puede ser posterior a la fecha actual"
            );
        }

        this.codigoLibro = codigoLibro;
        this.socio = socio;
        this.tituloLibro = tituloLibro;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucionPrevista = fechaPrestamo.plusDays(14);
    }

    public void registrarDevolucion(LocalDate fechaDevolucion){

        if(fechaDevolucion == null){
            throw new ExcepcionesBiblioteca.PrestamoInvalidoException("La fecha no puede estar vacia");
        }
        if(fechaDevolucion.isBefore(this.fechaPrestamo)){
            throw new ExcepcionesBiblioteca.PrestamoInvalidoException(
                    "La fecha de devolucion no puede ser anterior a la fecha del prestamo"
            );
        }
        this.fechaDevolucion = fechaDevolucion;
    }

    public  int calcularDiasRetraso(){

        LocalDate fechaReferencia;

        if(this.fechaDevolucion != null){
            fechaReferencia = this.fechaDevolucion;
        }else{
            fechaReferencia = LocalDate.now();
        }

        if (!fechaReferencia.isAfter(this.fechaDevolucionPrevista)){
            return 0;
        }
        return (int) ChronoUnit.DAYS.between(
                this.fechaDevolucionPrevista,fechaReferencia
                );
    }

    public boolean estaRetrasado(){
        if(this.fechaDevolucion != null){
            return this.fechaDevolucion.isAfter(this.fechaDevolucionPrevista);
        }
        return LocalDate.now().isAfter(this.fechaDevolucionPrevista);
    }
    public boolean estaActivo(){
        return fechaDevolucion == null;
    }

    @Override
    public String toString() {
        return "===PRESTAMO==="+"\n"
                +"Fecha del Prestamo: " + this.fechaPrestamo + "\n"
                + "Codigo del libro: " + this.codigoLibro + "\n"
                + "Titulo del libro: " + this.tituloLibro + "\n"
                + "Socio asociado al Prestamo: " + socio.getNumeroSocio() + "\n"
                + "Fecha del Prestamo: " + this.fechaPrestamo + "\n"
                + "Fecha de Devolucion Prevista: " + this.fechaDevolucionPrevista;
    }
}
