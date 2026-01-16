public class RadioGrupoX implements Radio {
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

public class Main {
    static void main() {
        Radio radio = new RadioGrupoX();
        radio.prenderRadio();
    }

}
