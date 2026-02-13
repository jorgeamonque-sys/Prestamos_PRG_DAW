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
    public static class UsuarioRepetidoException extends RuntimeException {

        public UsuarioRepetidoException(String mensaje) {
            super(mensaje);
        }
    }
    public static class UsuarioSancionadoException extends RuntimeException {
        public UsuarioSancionadoException(String mensaje){
            super(mensaje);
        }
    }
    public static class LibroNoDisponibleException extends RuntimeException{
        public LibroNoDisponibleException(String mensaje){
            super(mensaje);
        }
    }
}