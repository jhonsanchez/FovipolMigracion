package pe.gob.fovipol.migracion.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import pe.gob.fovipol.migracion.model.Aporte;
import pe.gob.fovipol.migracion.model.Cliente;
import pe.gob.fovipol.migracion.service.cliente.ClienteService;

@Controller
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

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
		model.addAttribute("urlfile", this.urlfile);
		model.addAttribute("tipo", this.tipo);
		return "clientes";
	}

	private String repeat(String cad, int veces) {
		String rpta = "";
		for (int c = 0; c < veces; c++) {
			rpta = rpta + cad;
		}
		return rpta;
	}

	@RequestMapping(value = "/getTitu", method = RequestMethod.POST)
	@ResponseBody
	public String getSaludo(@RequestParam String pcip,
			@RequestParam String pcodofin) {
		Cliente para = new Cliente();
		para.setCiptcl(pcip);
		para.setCoficl(pcodofin);
		Cliente cliente = clienteService.getCustomerDet(para);
		return cliente != null ? cliente.getCodicl() + ";"
				+ cliente.getNococl() : "";
	}

	//getTotalBen
	@RequestMapping(value = "/getVereficaBene", method = RequestMethod.POST)
	@ResponseBody
	public int getVereficaBene(@RequestParam String pcodtit){
		Cliente para = new Cliente();
		para.setCodtit(pcodtit);
		Cliente cliente = clienteService.getTotalBen(para);
		return cliente.getTotal();
	}
	
	@RequestMapping(value = "/getTraspasar", method = RequestMethod.POST)
	@ResponseBody
	public String getTraspaso(@RequestParam String pcodben,
			@RequestParam String pcodtit) {
		clienteService.getTraspasaAporte(pcodben, pcodtit);
		return "ok";
	}

	Vector<Aporte> filas;
	Vector<String> columnas;

	@RequestMapping(method = RequestMethod.POST, params = { "accion=cargar" })
	public String clientes(Model model, @RequestParam MultipartFile urlfile) {
		Vector<String> lst = LeerArchivo(urlfile);
		if (lst.size() > 0) {
			// System.out.print(repeat("0", 5));
			String[] arrayColumns = lst.get(0).split(";");

			int columncount = arrayColumns.length;
			columnas = new Vector<String>();
			for (String col : arrayColumns) {
				columnas.add(col);
			}
			// System.out.print(repeat("0", 5));
			model.addAttribute("xcolumnas", columnas);
			filas = new Vector<Aporte>();
			filas.clear();
			int tamaniocip = 8;
			int tamaniocodofin = 9;
			for (int x = 1; x < lst.size(); x++) {
				String[] arrayRow = lst.get(x).split(";");
				Aporte aporte = new Aporte();
				String cip = arrayRow[0];
				String codofin = arrayRow[1];
				aporte.setCtcip(repeat("0", tamaniocip - cip.length()) + cip);
				aporte.setCtcodofin(repeat("0",
						tamaniocodofin - codofin.length())
						+ codofin);
				aporte.setCtcliente(arrayRow[2]);
				aporte.setNimonto(Double.parseDouble((arrayRow[3]).replace(',',
						'.')));
				aporte.setNifecha(arrayRow[4]);
				filas.add(aporte);
			}
			model.addAttribute("xfilas", filas);
		}
		return "clientes";
	}

	public Map<Integer, String> MesAporte(char tipo) {
		Map<Integer, String> mp = new HashMap<Integer, String>();
		mp.put(1, "N_N_APORTE" + (tipo == 'E' ? "E" : "") + "01");
		mp.put(2, "N_N_APORTE" + (tipo == 'E' ? "E" : "") + "02");
		mp.put(3, "N_N_APORTE" + (tipo == 'E' ? "E" : "") + "03");
		mp.put(4, "N_N_APORTE" + (tipo == 'E' ? "E" : "") + "04");
		mp.put(5, "N_N_APORTE" + (tipo == 'E' ? "E" : "") + "05");
		mp.put(6, "N_N_APORTE" + (tipo == 'E' ? "E" : "") + "06");
		mp.put(7, "N_N_APORTE" + (tipo == 'E' ? "E" : "") + "07");
		mp.put(8, "N_N_APORTE" + (tipo == 'E' ? "E" : "") + "08");
		mp.put(9, "N_N_APORTE" + (tipo == 'E' ? "E" : "") + "09");
		mp.put(10, "N_N_APORTE" + (tipo == 'E' ? "E" : "") + "10");
		mp.put(11, "N_N_APORTE" + (tipo == 'E' ? "E" : "") + "11");
		mp.put(12, "N_N_APORTE" + (tipo == 'E' ? "E" : "") + "12");
		return mp;
	}

	Vector<Aporte> lstaportenot;

	@RequestMapping(method = RequestMethod.POST, params = { "accion=procesar" })
	public String procesar(Model model, @RequestParam char tipo) {
		lstaportenot = new Vector<Aporte>();
		for (Aporte aporte : filas) {
			Aporte ap = new Aporte();
			String anio = aporte.getNifecha().substring(2, 4);
			int mes = Integer.parseInt(aporte.getNifecha().substring(0, 2));
			ap.setCtcip(aporte.getCtcip());
			ap.setCtcodofin(aporte.getCtcodofin());
			ap.setNimonto(aporte.getNimonto());
			ap.setNianhio(Integer.parseInt("20" + anio));
			Map<Integer, String> val = MesAporte(tipo);
			ap.setNomcolumnames(val.get(mes));
			int rpta = clienteService.getUpdateAportes(ap);
			System.out.println(rpta);
			if (rpta < 1) {
				Aporte aponot = new Aporte();
				aponot.setCtcip(aporte.getCtcip());
				aponot.setCtcodofin(aporte.getCtcodofin());
				aponot.setCtcliente(aporte.getCtcliente());
				aponot.setNimonto(aporte.getNimonto());
				aponot.setNifecha(aporte.getNifecha());
				lstaportenot.add(aponot);
			}
		}
		model.addAttribute("xlstaportenot", lstaportenot);
		model.addAttribute("xcolumnasnot", columnas);
		return "clientes";
	}

	// @RequestMapping(value = "/aportescargados2",method =
	// RequestMethod.GET,headers="Accept=application/json")
	// @ResponseBody
	// public Vector<Aporte>GetAportes2(){
	// Vector<Aporte>lst=new Vector<Aporte>();
	// lst.add(new Aporte("00013765", "AMOROS VASQUEZ,FERNANDO"));
	// lst.add(new Aporte("00054525", "CELIS SANTA CRUZ, MAXIMO"));
	// lst.add(new Aporte("00061575", "CHICLOTE VALDEZ,ALBERTO"));
	// lst.add(new Aporte("00067935", "CORDOVA RUBINA,GLADYS LUCRECIA"));
	// lst.add(new Aporte("00013765", "AMOROS VASQUEZ,FERNANDO"));
	// lst.add(new Aporte("00054525", "CELIS SANTA CRUZ, MAXIMO"));
	// lst.add(new Aporte("00061575", "CHICLOTE VALDEZ,ALBERTO"));
	// lst.add(new Aporte("00067935", "CORDOVA RUBINA,GLADYS LUCRECIA"));
	// lst.add(new Aporte("00013765", "AMOROS VASQUEZ,FERNANDO"));
	// lst.add(new Aporte("00054525", "CELIS SANTA CRUZ, MAXIMO"));
	// lst.add(new Aporte("00061575", "CHICLOTE VALDEZ,ALBERTO"));
	// lst.add(new Aporte("00067935", "CORDOVA RUBINA,GLADYS LUCRECIA"));
	// return lst;
	// }
	// @RequestMapping(value = "/aportescargados",method =
	// RequestMethod.GET,headers="Accept=application/json")
	// @ResponseBody
	// public Vector<Aporte>GetAportes(){
	// return this.filas;
	// }
	// value = "/export", method = RequestMethod.GET
	@RequestMapping(method = RequestMethod.POST, params = { "accion=exportar" })
	public ModelAndView getExcel() {
		// List<Animal> animalList = animalService.getAnimalList();
		return new ModelAndView("AnimalListExcel", "clienteben", filas);
	}

	public Vector<String> LeerArchivo(MultipartFile file) {
		BufferedReader br = null;
		Vector<String> lineas = new Vector<String>();
		try {
			br = new BufferedReader(
					new InputStreamReader(file.getInputStream()));
			String linea;
			// int i=0;
			while ((linea = br.readLine()) != null) {
				// i++;
				lineas.add(linea);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return lineas;
	}

	// GET AND SET
	private MultipartFile urlfile;

	public MultipartFile getUrlfile() {
		return urlfile;
	}

	public void setUrlfile(MultipartFile urlfile) {
		this.urlfile = urlfile;
	}

	private char tipo;

	public char getTipo() {
		return tipo;
	}

	public void setTipo(char tipo) {
		this.tipo = tipo;
	}

}
