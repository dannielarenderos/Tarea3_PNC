package com.uca.capas.modelo.controller;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Clase10Controller {
	
	@RequestMapping("/ingresar")
	public String index() {
		/* No es necesario poner la extension .html a index, ya que el metodo
		 * templateResolver.setSuffix(".html") sirve para asumir dicha extension.
		 * Como solo vamos a redirigir a la pagina index.html no es necesario un 
		 * objeto ModelAndView, al devolver un tipo de dato String, Spring automaticamente
		 * asume que es una vista la que se quiere devolver.
		 */
		return "clases/clase10/index";
	}
	
	@RequestMapping("/parametros1")
	public ModelAndView parametros1(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		
		String usuario = request.getParameter("usuario");
		String apellido = request.getParameter("apellido");
		String fecha = request.getParameter("fecha");
		String lugar = request.getParameter("lugar");
		String colegio = request.getParameter("colegio");
		String tel = request.getParameter("tel");
		String cel = request.getParameter("cel");

		
		if(fecha.isBlank() || fecha.isEmpty()) {
			fecha=  "2002-01-01";
		}
		
		String input = "2003-01-01";
		
		DateFormat f = new SimpleDateFormat("yyyy-mm-dd");
		
		Date d1 = (Date) f.parse(fecha, new ParsePosition(0));
		Date d2 = (Date) f.parse(input, new ParsePosition(0));
		
		
		int flag= d1.compareTo(d2);
		
		List<String> lista = new ArrayList<>();
		String mensaje= "Alumno ingresado con éxito";
		
		
		if(flag<0) {
			lista.add("*El campo fecha a ingresar no puede ser antes del 1 de Enero del 2003");
		}

		
		if(usuario.length()<1 || usuario.length()>25) {
			lista.add("*El campo Nombres no puede ser mayor a 25 caracteres o menor a 1 caracter");
		}
		
		if(apellido.length()<1 || apellido.length()>25) {
			lista.add("*El campo Apellido no puede ser mayor a 25 caracteres o menor a 1 caracter");
		}
		
		if(lugar.length()<1 || lugar.length()>25) {
			lista.add("*El campo Lugar no puede ser mayor a 25 caracteres o menor a 1 caracter");
		}
		
		if(tel.length()!=8) {
			lista.add("*El campo Telefono debe tener 8 numeros");
		}
		

		if(cel.length()!=8) {
			lista.add("*El campo Celular debe tener 8 numeros");
		}
		
		if(colegio.length()<1 || colegio.length()>100) {
			lista.add("*El campo Colegio no puede ser mayor a 100 caracteres o menor a 1 caracter");
		}
		
	    

		
		if(lista.isEmpty()) {
			mav.addObject("mensaje", mensaje);
			mav.setViewName("clases/clase10/resultado2");

		}else {
			mav.addObject("errores", lista);
			mav.setViewName("clases/clase10/resultado");
		}
		
		
		return mav;
		
	}
	
	
}
