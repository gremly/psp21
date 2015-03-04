package edu.uniandes.ecos;

import edu.uniandes.ecos.logica.Calc;
import junit.framework.TestCase;

/**
 * Casos de prueba para la clase Calc, destinada a los cáclulos matemáticos.
 * @author Oscar Javier Moreno Rey
 */
public class CalcTest extends TestCase {

	/**
	 * Prueba el primer caso para gama cuando x = 1
	 */
	public void testCalculateGammaOne(){
		Calc calculator = new Calc();
		assertEquals(1.0, calculator.calculateGamma(1));
	}
	
	/**
	 * Prueba el segundo caso para gama cuando x = 1/2
	 */
   public void testCalculateGammaMid(){
	   double x = 1.0/2;
	   Calc calculator = new Calc();
		assertEquals(Math.sqrt(Math.PI), calculator.calculateGamma(x));
	}
   
   /**
	 * Prueba el caso para gamma cuando x es un fraccionario.
	 */
  public void testCalculateGammaMidNine(){
	   double x = 9.0/2;
	   Calc calculator = new Calc();
		assertEquals(11.631728396567448, calculator.calculateGamma(x));
	}
  
   
   /**
    * Prueba el caso para gamma cuando x es un entero.
    */
   public void testCalculateGammaOther(){
	   Calc calculator = new Calc();
		assertEquals(calculator.calculateFactorial(6 -1), calculator.calculateGamma(6));
	}
   
   /**
    * Prueba la evaluación de la funcion T.
    */
   public void testCalculateTFunction(){
	   Calc calculator = new Calc();
	   assertEquals(0.38543693984483196, calculator.calculateTFunction(0.11, 9));
   }
   
   /**
    * Prueba la correcta ejecución de la regla de Simpson.
    */
   public void testComputeSimpsonRule(){
	   Calc calculator = new Calc();
	   assertEquals(0.3500589042865573, calculator.computeSimpsonRule(1.1, 9, 10));
   }
   
   /**
    * Prueba el procedimiento de integrar con varias iteraciones.
    */
   public void testIterateWithSimpson(){
	   Calc calculator = new Calc();
	   assertEquals(0.35005863689720107, calculator.iterateWithSimpson(1.1, 9, 10, 0.00001, 2));
   }
}
