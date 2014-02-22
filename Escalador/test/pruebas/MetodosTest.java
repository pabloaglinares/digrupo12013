package pruebas;

import junit.framework.TestCase;
import metodos.Metodos;

public class MetodosTest extends TestCase {
    
    Metodos instance = new Metodos();
    double rendimientoEsperado, rendimientoObtenido;
    
    public MetodosTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    /**
     * Comprueba que el rendimiento sea igual a cero en el caso de no tener un
     * número positivo de semanas, por pequeño que sea, ya que el rendimiento 
     * se mide en relación al tiempo transcurrido.
     */
    public void testGetRendimiento1() {
        rendimientoEsperado = 0.0;
        rendimientoObtenido = instance.getRendimiento(100.0, 100, 0.0); //horas, itinerarios, semanas
        assertEquals("Cero semanas: ", rendimientoEsperado, rendimientoObtenido);
        rendimientoObtenido = instance.getRendimiento(100.0, 100, -0.01);
        assertEquals("Semanas negativas: ", rendimientoEsperado, rendimientoObtenido);
    }
    
    /**
     * Comprueba que el máximo rendimiento semanal por entrenar sea 5, 
     * aunque la media de horas supere las 10 semanales.
     */
    public void testGetRendimiento2() {
        rendimientoEsperado = 5.0;
        rendimientoObtenido = instance.getRendimiento(100.0, 0, 2.0); //horas, itinerarios, semanas
        assertEquals("Rendimiento de 25 horas medias semanales de entrenamiento: ",
                rendimientoEsperado, rendimientoObtenido);
    }
    
    /**
     * Comprueba que el máximo rendimiento semanal por completar itinerarios sea 5, 
     * aunque la media de itinerarios finalizados por semana sea superior a 20.
     */
    public void testGetRendimiento3() {
        rendimientoEsperado = 5.0;
        rendimientoObtenido = instance.getRendimiento(0.0, 100, 2.0); //horas, itinerarios, semanas
        assertEquals("Rendimiento de una media de 50 itinerarios finalizados por semana: ",
                rendimientoEsperado, rendimientoObtenido);
    }
    
    /**
     * Comprueba que el rendimiento sea cero si no se ha entrenado ni completado
     * itinerarios en un determinado número de semanas positivo.
     */
    public void testGetRendimiento4() {
        rendimientoEsperado = 0.0;
        rendimientoObtenido = instance.getRendimiento(0.0, 0, 1.0); //horas, itinerarios, semanas
        assertEquals("Rendimiento de una media de 50 itinerarios finalizados por semana: ",
                rendimientoEsperado, rendimientoObtenido);
    }
    
    /**
     * Comprueba que calcula bien el rendimiento para casos con media de horas 
     * entrenadas y de itinerarios realizados inferiores al máximo puntuable.
     */
    public void testGetRendimiento5() {
        rendimientoEsperado = 6.5;
        rendimientoObtenido = instance.getRendimiento(8.0, 10, 1.0); //horas, itinerarios, semanas
        assertEquals("Rendimiento de una media de 50 itinerarios finalizados por semana: ",
                rendimientoEsperado, rendimientoObtenido);
    }
    
}
