package br.jamtech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.jamtech.repository.UsuarioRepository;

@Controller
public class ControllerUsuario {

	@Autowired
	UsuarioRepository usuarioreRpository;
	
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/adminpag")
	public String inicio() {
		return "admin/adminpag";
	}
	
	
	
	
	
}
