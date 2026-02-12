package Bibloteca;

public class ExcepcionesBiblioteca {

    public static class UsuarioInvalidoException extends RuntimeException {
        public UsuarioInvalidoException(String mensaje) {
            super(mensaje);
        }
    }

    public static class PrestamoInvalidoException extends RuntimeException {
        public PrestamoInvalidoException(String mensaje) {
            super(mensaje);
        }
    }
}