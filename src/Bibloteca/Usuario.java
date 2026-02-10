package Bibloteca;

import java.time.LocalDate;

public class Usuario {
    private String nombre;
    private String email;
    private String numeroSocio;
    private LocalDate fechaRegistro;
    private boolean sancionado;
    private LocalDate fechaFinSancion;


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

        if(email == null || !email.contains("@") || !email.contains(".")){
            throw new UsuarioInvalidoException(
                    "El email debe tener el caracter @ y un . por ejemplo algo asi jorgea_monque@gmail.com"
            );
        }
        if(numeroSocio == null || !numeroSocio.matches("^SOC[0-9]{5}")){
            throw new UsuarioInvalidoException(
              "El numero de socio tiene que tener el formato SOC mas 5 digitos por ejemplo SOC00123"
            );
        }
        this.nombre = nombre;
        this.email = email;
        this.numeroSocio = numeroSocio;
        this.fechaRegistro = fechaRegistro;
    }

    public void sancionar(){
        return;
    }
    public void levantarSancion(){

    }
    /*
    public boolean estaSancionado(){

    }
     */
    @Override
    public String toString() {
        return "Nombre de Usario: " + this.nombre + "\n" +
                "Email del Usario: " + this.email + "\n" +
                "Numero de Socio del Usario: " + this.numeroSocio + "\n" +
                "El usario se registro: " + this.fechaRegistro
                ;
    }
}
