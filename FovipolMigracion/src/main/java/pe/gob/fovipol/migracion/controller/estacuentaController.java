package pe.gob.fovipol.migracion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class estacuentaController {
	
	@RequestMapping(value = "/vestadocuenta", method = RequestMethod.GET)
	public String estadoCuenta(Model model) {
		model.addAttribute("lstCustomer", null);
		//model.addAttribute("ccCliente", this.ccCliente);
		//model.addAttribute("tipo", this.tipo);
		//model.addAttribute("lstTibus",TipoConsulta());
		return "estadocuenta";
	}
}
