import java.util.Scanner;

/**
 * Implementación concreta de la interfaz Radio que simula el funcionamiento
 * de un radio automotriz con capacidades AM/FM y memoria de estaciones.
 * 
 * <p>Características principales:
 * <ul>
 *   <li>Control de encendido/apagado</li>
 *   <li>Dos bandas: AM (530-1610 kHz) y FM (87.9-107.9 MHz)</li>
 *   <li>Navegación continua por el dial de frecuencias</li>
 *   <li>12 botones de memoria programables</li>
 *   <li>Interfaz de usuario basada en menú de consola</li>
 * </ul>
 * 
 * <p>Ejemplo de uso:
 * <pre>
 * {@code
 * Radio radio = new RadioGrupoX();
 * radio.prenderRadio();  // Inicia la interfaz interactiva
 * }
 * </pre>
 * 
 * @author Daniel López - 242159
 * @author Leonel Hernandez - 25041
 * @version 1.0
 * @since 2025-01-16
 * @see Radio
 */
public class RadioGrupoX implements Radio {
    /**
     * Estado de encendido del radio.
     * {@code true} si está encendido, {@code false} si está apagado.
     */
    private boolean encendido = false;
    
    /**
     * Banda actual del radio.
     * {@code true} para FM, {@code false} para AM.
     */
    private boolean esFM = true;
    
    /**
     * Frecuencia actual sintonizada.
     * Para FM: valores entre 87.9 y 107.9
     * Para AM: valores entre 530 y 1610
     */
    private double estacionActual = 87.9;
    
    /**
     * Array que almacena las frecuencias guardadas en cada botón (1-12).
     * Un valor de 0 indica que el botón no tiene ninguna estación guardada.
     */
    final double[] botonesFrecuencia = new double[12];
    
    /**
     * Array que almacena la banda (AM/FM) de cada estación guardada.
     * {@code true} si la estación guardada es FM, {@code false} si es AM.
     */
    final boolean[] botonesEsFM = new boolean[12];

    /**
     * {@inheritDoc}
     * 
     * <p>Esta implementación muestra un menú interactivo en consola con
     * las siguientes opciones:
     * <ol>
     *   <li>Cambiar entre AM y FM</li>
     *   <li>Avanzar estación</li>
     *   <li>Guardar estación en botón</li>
     *   <li>Cargar estación desde botón</li>
     *   <li>Apagar radio</li>
     * </ol>
     * 
     * <p>El menú se mantiene activo hasta que el usuario seleccione
     * la opción de apagar el radio. Utiliza try-with-resources para
     * garantizar el cierre automático del Scanner.
     */
    @Override
    public void prenderRadio() {
        encendido = true;
        try (Scanner sc = new Scanner(System.in)) {
            while (encendido) {
                System.out.println("\n===== RADIO =====");
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
                    case 1 -> {
                        if (esFM)
                            cambiarAM();
                        else
                            cambiarFM();
                    }

                    case 2 -> {
                        avanzarEstacion();
                    }

                    case 3 -> {
                        System.out.print("Botón (1-12): ");
                        guardarEstacion(sc.nextInt());
                    }

                    case 4 -> {
                        System.out.print("Botón (1-12): ");
                        cargarEstacion(sc.nextInt());
                    }

                    case 5 -> {
                        apagarRadio();
                    }
                }
            }
        }
    }
    
    /**
     * {@inheritDoc}
     * 
     * <p>Establece el estado interno del radio como apagado,
     * lo que causa que el bucle de la interfaz de usuario termine.
     */
    @Override
    public void apagarRadio() {
        encendido = false;
    }

    /**
     * {@inheritDoc}
     * 
     * <p>Detalles de implementación:
     * <ul>
     *   <li>En modo FM: incrementa la frecuencia en 0.2 MHz</li>
     *   <li>En modo AM: incrementa la frecuencia en 10 kHz</li>
     *   <li>Al superar el límite, regresa automáticamente al inicio de la banda</li>
     * </ul>
     * 
     * <p>El método verifica si el radio está encendido antes de proceder.
     * Si está apagado, no realiza ninguna acción.
     */
    @Override
    public void avanzarEstacion() {
         if (!encendido)
            return;

        if (esFM) {
            estacionActual += 0.2;
            // Cuando llega al final vuelve al inicio
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

    /**
     * {@inheritDoc}
     * 
     * <p>Guarda tanto la frecuencia actual como la banda (AM/FM) en el botón
     * especificado. Si el botón ya tenía una estación guardada, esta se
     * sobrescribe.
     * 
     * <p>Validaciones:
     * <ul>
     *   <li>El radio debe estar encendido</li>
     *   <li>El número de botón debe estar entre 1 y 12 (inclusive)</li>
     * </ul>
     * 
     * <p>Si alguna validación falla, el método no realiza ninguna acción.
     * 
     * @param numeroBoton número del botón donde guardar (1-12)
     */
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

    /**
     * {@inheritDoc}
     * 
     * <p>Al cargar una estación, el radio automáticamente cambia a la banda
     * (AM o FM) en la que fue guardada esa estación.
     * 
     * <p>Comportamiento especial:
     * <ul>
     *   <li>Si el botón especificado no tiene ninguna estación guardada
     *       (valor 0), el método no realiza ningún cambio</li>
     *   <li>Al cargar, se restaura tanto la frecuencia como la banda</li>
     * </ul>
     * 
     * <p>Validaciones:
     * <ul>
     *   <li>El radio debe estar encendido</li>
     *   <li>El número de botón debe estar entre 1 y 12 (inclusive)</li>
     * </ul>
     * 
     * @param numeroBoton número del botón a cargar (1-12)
     */
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

    /**
     * {@inheritDoc}
     * 
     * <p>Establece la banda en FM y la frecuencia en 87.9 MHz,
     * que corresponde al inicio de la banda FM.
     * 
     * <p>Solo realiza el cambio si el radio está encendido.
     */
    @Override
    public void cambiarFM() {
        if (!encendido)
            return;
        esFM = true;
        estacionActual = 87.9;
    }

    /**
     * {@inheritDoc}
     * 
     * <p>Establece la banda en AM y la frecuencia en 530 kHz,
     * que corresponde al inicio de la banda AM.
     * 
     * <p>Solo realiza el cambio si el radio está encendido.
     */
    @Override
    public void cambiarAM() {
         if (!encendido)
            return;
        esFM = false;
        estacionActual = 530;
    }
}