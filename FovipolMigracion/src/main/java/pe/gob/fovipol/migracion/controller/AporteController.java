package pe.gob.fovipol.migracion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pe.gob.fovipol.migracion.service.aportes.AporteService;

@Controller
public class AporteController {
	
	@Autowired
	private AporteService aporteService;
	
	@RequestMapping(value = "/vaportes", method = RequestMethod.GET)
	public String buscarClientes(Model model) {
		
		return "aportesestadocuenta";
	}
}
