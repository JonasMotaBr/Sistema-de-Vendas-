package br.jamtech;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class indexController {
	
	
	@RequestMapping("/")
	public String index() {
	 return "login";	
	}
	
	
	@RequestMapping("/403")
	public String erroPermicao() {
	 return "erroTemp/erropermicao";	
	}


}
