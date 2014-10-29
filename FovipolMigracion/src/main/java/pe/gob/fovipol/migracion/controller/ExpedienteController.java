package pe.gob.fovipol.migracion.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pe.gob.fovipol.migracion.service.expediente.ExpedienteService;

@Controller
public class ExpedienteController {
	
	@Autowired
	private ExpedienteService expedienteService;
	
	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);
	
	@RequestMapping(value = "/anulacionexped", method = RequestMethod.GET)
	public String AnulacionExpediente(Model model) {
		model.addAttribute("ccexpediente", this.ccexpediente);
		return "anulacionexp";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String AnularExpediente(@RequestParam String ccexpediente){
		//int rpta = clienteService.getUpdateAportes(ap);
		int rpta=expedienteService.getAnularExpediente(ccexpediente);
		
		return "anulacionexp";
	}
	
	private String ccexpediente;

	public String getCcexpediente() {
		return ccexpediente;
	}

	public void setCcexpediente(String ccexpediente) {
		this.ccexpediente = ccexpediente;
	}
	
}
