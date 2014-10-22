package pe.gob.fovipol.migracion.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pe.gob.fovipol.migracion.model.Cliente;
import pe.gob.fovipol.migracion.service.cliente.ClienteService;

@Controller
public class ClienteController {
	// MENSAJE DE PRUEBA
	@Autowired
	private ClienteService clienteService;

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	private String ccCliente;

	public String getCcCliente() {
		return ccCliente;
	}

	public void setCcCliente(String ccCliente) {
		this.ccCliente = ccCliente;
	}

	private int tipo;

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	private List<Cliente> TipoConsulta() {
		List<Cliente> lstTibus = new ArrayList<Cliente>();
		lstTibus.add(new Cliente("", "Seleccione"));
		lstTibus.add(new Cliente("0", "Apellidos y Nombres"));
		lstTibus.add(new Cliente("1", "CIP"));
		lstTibus.add(new Cliente("2", "DNI"));
		return lstTibus;
	}

	@RequestMapping(value = "/clienteben", method = RequestMethod.GET)
	public String buscarClientes(Model model) {
		model.addAttribute("lstCustomer", null);
		model.addAttribute("ccCliente", this.ccCliente);
		model.addAttribute("tipo", this.tipo);
		model.addAttribute("lstTibus", TipoConsulta());
		return "clientes";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String clientes(@RequestParam String ccCliente,
			@RequestParam int tipo, Model model) {
		model.addAttribute("myname", "Luis Bellido");
		List<Cliente> lstCustomer = clienteService.getCustomer(ccCliente, tipo);// "P000236525"
		model.addAttribute("lstCustomer", lstCustomer);
		model.addAttribute("lstTibus", TipoConsulta());
		return "clientes";
	}
	
	
}
