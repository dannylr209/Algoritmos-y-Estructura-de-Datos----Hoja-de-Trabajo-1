import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;

public class RadioGrupoXTest {

    // encenser sin menu
    private void encenderSinMenu(RadioGrupoX radio) throws Exception {
        Field f = RadioGrupoX.class.getDeclaredField("encendido");
        f.setAccessible(true);
        f.set(radio, true);
    }

    @Test
    public void pruebaGuardarYCargarAM() throws Exception {
        RadioGrupoX radio = new RadioGrupoX();
        encenderSinMenu(radio);

        radio.cambiarAM(); // 530
        radio.avanzarEstacion(); // 540
        radio.guardarEstacion(1);

        radio.cambiarFM(); // cambio de banda
        radio.cargarEstacion(1);

        Field estacion = RadioGrupoX.class.getDeclaredField("estacionActual");
        Field esFM = RadioGrupoX.class.getDeclaredField("esFM");
        estacion.setAccessible(true);
        esFM.setAccessible(true);

        assertEquals(540.0, estacion.getDouble(radio));
        assertFalse(esFM.getBoolean(radio));
    }

    @Test
    public void pruebaAvanzarFMConReinicio() throws Exception {
        RadioGrupoX radio = new RadioGrupoX();
        encenderSinMenu(radio);

        // Forzamos FM
        radio.cambiarFM();

        // Avanzamos hasta pasar el límite
        for (int i = 0; i < 110; i++) {
            radio.avanzarEstacion();
        }

        Field estacion = RadioGrupoX.class.getDeclaredField("estacionActual");
        Field esFM = RadioGrupoX.class.getDeclaredField("esFM");
        estacion.setAccessible(true);
        esFM.setAccessible(true);

        double frecuencia = estacion.getDouble(radio);

        assertTrue(esFM.getBoolean(radio)); // Sigue en FM
        assertTrue(frecuencia >= 87.9 && frecuencia <= 107.9);
    }

    @Test
    public void pruebaGuardarYCargarFMDesdeAM() throws Exception {
        RadioGrupoX radio = new RadioGrupoX();
        encenderSinMenu(radio);

        // FM → avanzar → guardar
        radio.cambiarFM();
        radio.avanzarEstacion(); // 88.1
        radio.guardarEstacion(2);

        // Cambiamos a AM
        radio.cambiarAM();
        radio.avanzarEstacion(); // 540

        // Cargamos botón FM
        radio.cargarEstacion(2);

        Field estacion = RadioGrupoX.class.getDeclaredField("estacionActual");
        Field esFM = RadioGrupoX.class.getDeclaredField("esFM");
        estacion.setAccessible(true);
        esFM.setAccessible(true);

        assertEquals(88.1, estacion.getDouble(radio), 0.0001);
        assertTrue(esFM.getBoolean(radio)); // Debe volver a FM
    }


}

