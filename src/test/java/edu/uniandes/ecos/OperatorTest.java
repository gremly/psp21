package edu.uniandes.ecos;



import java.text.DecimalFormat;

import edu.uniandes.ecos.logica.Operator;
import junit.framework.TestCase;

/**
 * Casos de prueba para la clase Operator, destinada a la gestión de datos recibidos
 * desde controller.
 * @author Oscar Javier Moreno Rey
 */
public class OperatorTest extends TestCase {

	/**
	 * Prueba el primer caso para p = 0.20 con dof = 6
	 */
	public void testCalculateVarXUno(){
		Operator operator = new Operator();
		operator.setData(6, 0.20);
		assertEquals(0.55338,  Math.floor(operator.calcIntegral().get("Valor de X = ") * 100000) / 100000);
	}
	
	/**
	 * Prueba el primer caso para p = 0.45 con dof = 15
	 */
	public void testCalculateVarXDos(){
		Operator operator = new Operator();
		operator.setData(15, 0.45);
		assertEquals(1.75305,  Math.floor(operator.calcIntegral().get("Valor de X = ") * 100000) / 100000);
	}
}
