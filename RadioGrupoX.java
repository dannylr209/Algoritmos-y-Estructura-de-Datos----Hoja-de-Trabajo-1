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
        encendido = true;
        Scanner sc = new Scanner(system.in);

        while (encendido) {
            System.out.println("\n===== RADIO =====")
            System.out.println("Banda: " + (esFM ? "FM" : "AM"));
            System.out.println("Estación actual: " + estacionActual);
            System.out.println("1. Cambiar AM / FM");
            System.out.println("2. Avanzar estación");
            System.out.println("3. Guardar estación");
            System.out.println("4. Cargar estación");
            System.out.println("5. Apagar radio");
            System.out.print("Opción: ");

            int opcion = sc.nextInt();

             switch (opcion) {
                case 1:
                    if (esFM)
                        cambiarAM();
                    else
                        cambiarFM();
                    break;

                case 2:
                    avanzarEstacion();
                    break;

                case 3:
                    System.out.print("Botón (1-12): ");
                    guardarEstacion(sc.nextInt());
                    break;

                case 4:
                    System.out.print("Botón (1-12): ");
                    cargarEstacion(sc.nextInt());
                    break;

                case 5:
                    apagarRadio();
                    break;
            }
        }
    }

    @Override
    public void apagarRadio() {
        encendido = false;
    }

    @Override
    public void avanzarEstacion() {
         if (!encendido)
            return;

        if (esFM) {
            estacionActual += 0.2;
            //Cuando llega al final vuelve al inicio
            if (estacionActual > 107.9) {
                estacionActual = 87.9;
            }
        } else {
            estacionActual += 10;
            if (estacionActual > 1610) {
                estacionActual = 530;
            }
        }

    }

    @Override
    public void guardarEstacion(int numeroBoton) {
         if (!encendido)
            return;
        if (numeroBoton < 1 || numeroBoton > 12)
            return;

        int index = numeroBoton - 1;
        botonesFrecuencia[index] = estacionActual;
        botonesEsFM[index] = esFM;

    }

    @Override
    public void cargarEstacion(int numeroBoton) {
        if (!encendido)
            return;
        if (numeroBoton < 1 || numeroBoton > 12)
            return;

        int index = numeroBoton - 1;

        if (botonesFrecuencia[index] != 0) {
            estacionActual = botonesFrecuencia[index];
            esFM = botonesEsFM[index];
        }

    }

    @Override
    public void cambiarFM() {
        if (!encendido)
            return;
        esFM = true;
        estacionActual = 87.9;

    }

    @Override
    public void cambiarAM() {
         if (!encendido)
            return;
        esFM = false;
        estacionActual = 530;

    }

}









