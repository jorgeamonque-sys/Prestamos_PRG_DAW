package Bibloteca;

public class UsuarioInvalidoException extends RuntimeException {

    public UsuarioInvalidoException(String mensaje){
        super(mensaje);
    }
}
