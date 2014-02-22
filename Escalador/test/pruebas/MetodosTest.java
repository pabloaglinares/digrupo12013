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
        rendimientoEsperado = 0;
        rendimientoObtenido = instance.getRendimiento(100.0, 100, 0.0); //horas, itinerarios, semanas
        assertEquals("Cero semanas: ", rendimientoEsperado, rendimientoObtenido);
        rendimientoObtenido = instance.getRendimiento(100.0, 100, -0.01);
        assertEquals("Cero semanas: ", rendimientoEsperado, rendimientoObtenido);
    }
}
