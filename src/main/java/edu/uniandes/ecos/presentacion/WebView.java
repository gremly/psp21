package edu.uniandes.ecos.presentacion;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebView {

	 public void showHome(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {
		  	
		  	PrintWriter pw = resp.getWriter();
		  	String content = "";
		  	
		  	content +="<form action=\"calculate\" method=\"POST\">";
		  	content +="<table style=\"bogrder: 1px solid black;\">";
		  	content += "<tr>";
		  	content +="<th><label for=\"varx\">Valor de la integral (p): </label></th>";
		  	content +="<td style=\"text-align: center;\"><input type=\"text\" name=\"integralp\" id=\"integralp\"  placeholder=\"Ingrese el valor de p.\" ></td>";
		  	content +="</tr>";
		  	content += "<tr>";
		  	content +="<th><label for=\"dof\">Grados de libertad: </label></th>";
		  	content +="<td style=\"text-align: center;\"><input type=\"text\" name=\"dof\" id=\"dof\"  placeholder=\"Número entero\" ></td>";
		  	content +="</tr>";
		  	content +="<tr>";
		  	content +="<td style=\"text-align: center;\"><input type=\"submit\" name=\"calculate\" value=\"Calcular\" ></td>";
		  	content +="</tr>";
		  	content +="</table>";
		  	content +="</form>";
		  	
		  	this.writePage(pw, content);
	  }
	 
	  public void showNotFound(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		  PrintWriter pw = resp.getWriter();
		   this.writePage(pw, "<h3>Error: 404 <em>Página no encontrada!</em></h3>");
	  }

	public void showResults(HttpServletRequest req, HttpServletResponse resp, HashMap<String, Double> results) throws ServletException, IOException {

		PrintWriter pw = resp.getWriter();
		String content = "";
		
		content += "<h2>Resultados</h2>";
		content +="<table style=\"bogrder: 1px solid black;\">";
		
		for (Map.Entry<String, Double> entry : results.entrySet()) {
			content += "<tr>";
			content +="<th>"+ entry.getKey().toUpperCase() +"</th>";
			content +="<td style=\"text-align: center;\">"+ entry.getValue() +"</td>";
			content +="</tr>";
		}
		
		content +="</table>";
		content +="<a href=\"/\"><< Regresar</a>";
			  	
		this.writePage(pw, content);
		
	}
	
	private void writePage(PrintWriter pw, String content){
	  	pw.write("<html>");
	  	pw.write("<head><title>PSP2.1</title></head>");
	  	pw.write("<body>");
	  	pw.println("<h3> Cálculo del valor x dado el valor p para una función t, usando método Simpson. </h3>");
	  	pw.write(content);
	  	pw.write("</body>");
	  	pw.write("</html>");
	}
	 
}
