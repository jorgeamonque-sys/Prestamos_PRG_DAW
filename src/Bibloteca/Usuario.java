package Bibloteca;

import java.time.LocalDate;

public class Usuario {
    private String nombre;
    private String email;
    private String numeroSocio;
    private LocalDate fechaRegistro;
    private boolean sancionado;
    private LocalDate fechaFinSancion;

    public Usuario() {

    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroSocio() {
        return numeroSocio;
    }

    public void setNumeroSocio(String numeroSocio) {
        this.numeroSocio = numeroSocio;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public boolean isSancionado() {
        return sancionado;
    }

    public LocalDate getFechaFinSancion() {
        return fechaFinSancion;
    }

    public void setFechaFinSancion(LocalDate fechaFinSancion) {
        this.fechaFinSancion = fechaFinSancion;
    }

    public void setSancionado(boolean sancionado) {
        this.sancionado = sancionado;
    }

    public Usuario(String nombre, String email, String numeroSocio, LocalDate fechaRegistro){

        if(email == null || email.trim().isEmpty()){
            throw new ExcepcionesBiblioteca.UsuarioInvalidoException(
                    "El email no puede estar vacio un Ejemplo seria: usuario@gmail.com"
            );
        }

        if(fechaRegistro == null){
            throw new ExcepcionesBiblioteca.UsuarioInvalidoException(
                    "La fecha de registra no puede estar vacia"
            );
        }

        if(nombre == null || nombre.trim().isEmpty()){
            throw new ExcepcionesBiblioteca.UsuarioInvalidoException(
                    "El nombre no puede estar vacio"
            );
        }


        if(email == null || !email.contains("@") || !email.contains(".")){
            throw new ExcepcionesBiblioteca.UsuarioInvalidoException(
                    "El email debe tener el caracter @ y un punto. Ejemplo: usuario@gmail.com"
            );
        }

        if (numeroSocio == null || !numeroSocio.matches("^SOC[0-9]{5}$")) {
            throw new ExcepcionesBiblioteca.UsuarioInvalidoException(
                    "El numero de socio debe tener el formato SOC mas 5 digitos. Ejemplo: SOC00123"
            );
        }
        this.nombre = nombre;
        this.email = email;
        this.numeroSocio = numeroSocio;
        this.fechaRegistro = fechaRegistro;
    }

    public void sancionar(int diaSancion, LocalDate fechaInicio){
        this.sancionado = true;
        this.fechaFinSancion = fechaInicio.plusDays(diaSancion);
    }
    public void levantarSancion(){
        this.sancionado = false;
        this.fechaFinSancion = null;
    }
    public boolean estaSancionado(){
        return this.sancionado;
    }

    public void actualizarSancion(LocalDate hoy){
        if(fechaFinSancion != null && hoy.isAfter(fechaFinSancion)){
            fechaFinSancion = null;
        }
    }

    @Override
    public String toString() {
        return "===USUARIO==="+"\n"
                +"Nombre de Usario: " + this.nombre + "\n" +
                "Email del Usario: " + this.email + "\n" +
                "Numero de Socio del Usario: " + this.numeroSocio + "\n" +
                "El usario se registro: " + this.fechaRegistro;
    }
}
