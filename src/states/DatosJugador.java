package states;

public class DatosJugador {

    private static String nombre;

    public static void setNombre(String nombre){
        DatosJugador.nombre = nombre;
    }

    public static String getNombre(){
        return nombre;
    }


}
