package states;

public class Contador {

    private static int muertes;

    public static void start(){

        muertes = 0;
    }

    public static void muerte(){
        muertes++;
    }

    public static int getMuertes(){
        return muertes;
    }

}
