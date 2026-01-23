/**
 * Interfaz que define las operaciones básicas de un radio automotriz.
 * 
 * <p>Un radio puede operar en dos bandas:
 * <ul>
 *   <li>AM: frecuencias de 530 a 1610 kHz (múltiplos de 10)</li>
 *   <li>FM: frecuencias de 87.9 a 107.9 MHz (múltiplos de 0.2)</li>
 * </ul>
 * 
 * <p>El radio cuenta con 12 botones programables (1-12) para guardar
 * estaciones favoritas en cualquiera de las dos bandas.
 * 
 * <p>Los comentarios los escribimos nosotros y luego fueron cambiados al formato Javadoc con Claude, pedimos permiso antes de hacerlo y se nos concedió por el catedrático.<p>
 * 
 * @author Daniel López - 242159
 * @author Leonel Hernandez - 25041
 * @version 1.0
 * @since 2025-01-16
 */
public interface Radio {
    /**
     * Enciende el radio e inicia la interfaz de usuario.
     * El radio debe mostrar un menú interactivo con todas las opciones
     * disponibles hasta que el usuario decida apagarlo.
     */
    void prenderRadio();

    /**
     * Apaga el radio y finaliza la interfaz de usuario.
     * Después de llamar este método, el radio no debe responder
     * a otras operaciones hasta que se encienda nuevamente.
     */
    void apagarRadio();

    /**
     * Avanza a la siguiente estación disponible en la banda actual.
     * 
     * <p>Comportamiento:
     * <ul>
     *   <li>En FM: incrementa 0.2 MHz (87.9 → 88.1 → 88.3...)</li>
     *   <li>En AM: incrementa 10 kHz (530 → 540 → 550...)</li>
     * </ul>
     * 
     * <p>Al llegar al límite superior, regresa automáticamente al inicio:
     * <ul>
     *   <li>FM: 107.9 → 87.9</li>
     *   <li>AM: 1610 → 530</li>
     * </ul>
     */
    void avanzarEstacion();

    /**
     * Guarda la estación actual en un botón de memoria específico.
     * La estación se guarda junto con su banda (AM o FM).
     * 
     * @param numeroBoton número del botón donde guardar (1-12)
     * @throws IllegalArgumentException si numeroBoton no está en el rango 1-12
     */
    void guardarEstacion(int numeroBoton);

    /**
     * Carga y sintoniza la estación guardada en un botón de memoria.
     * Si el botón no tiene ninguna estación guardada, no hace nada.
     * 
     * @param numeroBoton número del botón a cargar (1-12)
     * @throws IllegalArgumentException si numeroBoton no está en el rango 1-12
     */
    void cargarEstacion(int numeroBoton);

    /**
     * Cambia el radio a la banda FM.
     * La frecuencia se establece en 87.9 MHz (inicio de la banda FM).
     */
    void cambiarFM();

    /**
     * Cambia el radio a la banda AM.
     * La frecuencia se establece en 530 kHz (inicio de la banda AM).
     */
    void cambiarAM();
}