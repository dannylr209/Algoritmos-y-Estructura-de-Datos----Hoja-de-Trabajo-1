public class RadioGrupoX implements Radio {
    @Override
    public void prenderRadio() {
        System.out.println("Radio Grupo X prendida");
    }

    @Override
    public void apagarRadio() {
        System.out.println("Radio Grupo X apagada");
    }

}

public class Main {
    static void main() {
        Radio radio = new RadioGrupoX();
        radio.prenderRadio();
    }

}
