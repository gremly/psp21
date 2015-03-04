package edu.uniandes.ecos.logica;

/**
 * Clase encargada de realizar los cálculos matemáticos
 * @author Oscar Javier Moreno Rey
 * */
public class Calc {

	/**
	 * Calcula la función Gamma .
	 * @param varX Valor para el cual se calculará la función gamma.
	 * @return double con el resultado de aplicar la función gamma a varX.
	 */
	public double calculateGamma(double varX) {
		double gamma;
		
		if (varX == 1){
			return 1;
			
		} else if (varX % 1 == 0){
			gamma = this.calculateFactorial(varX - 1);
		} else if (varX == 1.0/2){
			gamma = Math.sqrt(Math.PI);
			
		} else {
			gamma = 1;
			int i = (int)(varX*2 -2);
			while (i > 0){
				gamma *= i/2.0; 
				i -= 2;
			}
			gamma = gamma * Math.sqrt(Math.PI);

		}
		
		return gamma;
	}
	

	/**
	 * Calcula el factorial de un número dado.
	 * @param num Número entero para calcular el factorial.
	 * @return Número entero como resultado del factorial de num.
	 */
	public double calculateFactorial(double num) {
		if	(num == 0) 
			return 1;
		
		return num * calculateFactorial(num - 1);
	}

	/**
	 * Define la función T, para ser evaluada.
	 * @param varX Punto del eje X donde se desea evaluar la función.
	 * @param dof Grados de libertad.
	 * @return Valor correspondiente a la imagen de varX.
	 */
	public double calculateTFunction(double varX, int dof) {
		
		double numerator;
		double denominator;
		double factor;
		
		double numParam = (dof+1)/2.0;
    	numerator = this.calculateGamma(numParam);
		
		double denParam = dof/2.0;
		denominator = this.calculateGamma(denParam);
		
		denominator *= Math.sqrt(dof * Math.PI); 
		factor = 1.0/Math.sqrt(Math.pow(1+ Math.pow(varX,  2)/dof , dof +1 ));
        
		return (numerator / denominator) * factor ;
	}

	/**
	 * Lleva a cabo la integración de la función T usando el método de Simpson, tomando
	 * como límite inferior en x=0.
	 * @param varX Límite superior para evaluar la integral.
	 * @param dof Grados de libertad.
	 * @param segments Número de divisiones a realizar para sumar el área.
	 * @return Valor del área bajo la curva obtenido por el método Simpson.
	 */
	public double computeSimpsonRule(double varX, int dof, int segments) {
		double w = varX/segments;
		double result;
		
		result = (w/3) * this.calculateTFunction(0, dof);

		for (int i = 1; i < segments; i++) {
			result += (w/3) * this.calculateTFunction(i*w, dof) * ( i % 2 == 0 ? 2 : 4);
		}
		result += (w/3) * this.calculateTFunction(segments*w, dof);
		return result;
	}

	/**
	 * Lleva a cabo varios cálculos de la integral para definir el mejor resultado,
	 * tomando como referencia el porcentaje de error permitido.
	 * @param varX Límite superior para evaluar la integral.
	 * @param dof Grados de libertad
	 *  @param segments Número de divisiones a realizar para sumar el área.
	 * @param error Porcentaje de error permitido para las iteraciones.
	 * @param iterations Cantidad de iteraciónes para comparar los resultados de la integral.
	 * @return Valor del área escogido como mejor aproximación, basándose en el límite permitido.
	 */
	public double iterateWithSimpson(double varX, int dof, int segments, double error, int iterations) {
		double choosen = 0;
		double current;
		double previous = 0;
		
		for (int i = 1; i <= iterations; i++) {
			current = this.computeSimpsonRule(varX, dof, segments*i);
			
			if (previous !=0 && previous-current < error){
				choosen = current;
			} else {
				previous = current;
			}
		}
		
		return choosen;
	}
}
