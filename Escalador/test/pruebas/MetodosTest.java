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
    
    
    public void testGetRendimiento1() {
        rendimientoEsperado = 0;
        rendimientoObtenido = instance.getRendimiento(0.0, 0, 0.0); //horas, itinerarios, semanas
        assertEquals("Tres parámetros a cero: ", rendimientoEsperado, rendimientoObtenido);
    }
}
