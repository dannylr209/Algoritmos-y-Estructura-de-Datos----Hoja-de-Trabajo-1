import java.util.Scanner;

public class RadioGrupoX implements Radio {
    private boolean encendido = false;
    // Solo dos estados posibles
    private boolean esFM = true;
    private double estacionActual = 87.9;
    //Se crea una lista donde se guardaran las estaciones
    //Y tambien para mantener control de en que tipo de radio se esta
    private double[] botonesFrecuencia = new double[12];
    private boolean[] botonesEsFM = new boolean[12];

    @Override
    public void prenderRadio() {
        System.out.println("Radio Grupo X prendida");
    }

    @Override
    public void apagarRadio() {
        System.out.println("Radio Grupo X apagada");
    }

    @Override
    public void avanzarEstacion() {

    }

    @Override
    public void guardarEstacion(int numeroBoton) {

    }

    @Override
    public void cargarEstacion(int numeroBoton) {

    }

    @Override
    public void cambiarFM() {

    }

    @Override
    public void cambiarAM() {

    }

}




