package edu.uniandes.ecos.logica;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Clase encargada de manipular los datos que llegan del usuario y los que se
 * devuelven procesados.
 * @author Oscar Javier Moreno Rey
 */
public class Operator {

	/*
	 * Listado de datos para operar
	 */
	private double varx;
	private int dof;
	private int segments;
	private double integralP;
		
	/**
	 * Inicialización de los valores para realizar los cálculos.
* 	 * @param varX Límite superior para evaluar la integral.
	 * @param dof Grados de libertad
	 *  @param segments Número de divisiones a realizar para sumar el área.
	 */
	public void setData(int dof, double integralP){
		this.varx = 0.9;
		this.dof = dof;
		this.integralP = integralP;
		this.segments = 10;
	}
	
	/**
	 * Permite el cálculo de varX formateando el resultado para ser impreso en la vista. 
	 * @return Dato calculado con un identificador para ser mostrado al usuario.
	 */
	public HashMap<String, Double> calcIntegral(){
		HashMap<String, Double> relSize = new HashMap<String, Double>();
		double integral = 0;
		double posibleX = this.varx;
		double difference = this.integralP;
		double threshold = 0.5;
		double error = 0.000001;
		Calc calc = new Calc();
		
		while(Math.abs(difference) > error){
			integral = calc.iterateWithSimpson(posibleX,  this.dof, this.segments, error, 10);
			difference = this.integralP - integral;
			threshold = Math.signum(difference) == Math.signum(error) ? threshold : threshold / 2;
			this.varx = posibleX;
						
			if (this.integralP < integral){
				posibleX -= threshold;
			} else {
				posibleX += threshold;
			}
		}
		relSize.put("Valor de X = ",  varx);
		
		return relSize;
	}
	
}
