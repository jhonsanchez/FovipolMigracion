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
		return "clientes";
	}

	private String repeat(String cad, int veces) {
		String rpta = "";
		for (int c = 0; c < veces; c++) {
			rpta = rpta + cad;
		}
		return rpta;
	}
	
	Vector<Aporte> filas;
	@RequestMapping(method = RequestMethod.POST,params={"accion=cargar"})
	public String clientes(Model model,@RequestParam MultipartFile urlfile) {
		Vector<String> lst = LeerArchivo(urlfile);
		//System.out.print(repeat("0", 5));
		String[] arrayColumns = lst.get(0).split(";");
		
		int columncount = arrayColumns.length;
		Vector<String> columnas = new Vector<String>();
		for (String col : arrayColumns) {
			columnas.add(col);
		}
		//System.out.print(repeat("0", 5));
		model.addAttribute("xcolumnas", columnas);
		filas= new Vector<Aporte>();
		filas.clear();
		int tamaniocip = 8;
		int tamaniocodofin = 9;
		for (int x = 1; x < lst.size(); x++) {
			String[] arrayRow = lst.get(x).split(";");
			Aporte aporte = new Aporte();
			String cip = arrayRow[0];
			String codofin = arrayRow[1];
			aporte.setCtcip(repeat("0", tamaniocip - cip.length()) + cip);
			aporte.setCtcodofin(repeat("0", tamaniocodofin - codofin.length())
					+ codofin);
			aporte.setCtcliente(arrayRow[2]);
			aporte.setNimonto(Double.parseDouble((arrayRow[3])
					.replace(',', '.')));
			aporte.setNianhio(arrayRow[4]);
			filas.add(aporte);
		}
		model.addAttribute("xfilas", filas);
		return "clientes";
	}
	
	@RequestMapping(method = RequestMethod.POST,params={"accion=procesar"})
	public String procesar(Model model ) {
		for(Aporte aporte:filas){
		    Aporte ap=new Aporte();
		    ap.setCtcip(aporte.getCtcip());
		    ap.setCtcodofin(aporte.getCtcodofin());
		    ap.setNimonto(aporte.getNimonto());
		    ap.setNianhio(aporte.getNianhio());
		    int rpta=clienteService.getUpdateAportes(ap);
		    System.out.println(rpta);
		}
		return "clientes";
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@RequestMapping(value = "/aportescargados2",method = RequestMethod.GET,headers="Accept=application/json")
//	@ResponseBody
//	public Vector<Aporte>GetAportes2(){
//		Vector<Aporte>lst=new Vector<Aporte>();
//		lst.add(new Aporte("00013765", "AMOROS VASQUEZ,FERNANDO"));
//		lst.add(new Aporte("00054525", "CELIS SANTA CRUZ, MAXIMO"));
//		lst.add(new Aporte("00061575", "CHICLOTE VALDEZ,ALBERTO"));
//		lst.add(new Aporte("00067935", "CORDOVA RUBINA,GLADYS LUCRECIA"));
//		lst.add(new Aporte("00013765", "AMOROS VASQUEZ,FERNANDO"));
//		lst.add(new Aporte("00054525", "CELIS SANTA CRUZ, MAXIMO"));
//		lst.add(new Aporte("00061575", "CHICLOTE VALDEZ,ALBERTO"));
//		lst.add(new Aporte("00067935", "CORDOVA RUBINA,GLADYS LUCRECIA"));
//		lst.add(new Aporte("00013765", "AMOROS VASQUEZ,FERNANDO"));
//		lst.add(new Aporte("00054525", "CELIS SANTA CRUZ, MAXIMO"));
//		lst.add(new Aporte("00061575", "CHICLOTE VALDEZ,ALBERTO"));
//		lst.add(new Aporte("00067935", "CORDOVA RUBINA,GLADYS LUCRECIA"));
//		return lst;
//	}
//	@RequestMapping(value = "/aportescargados",method = RequestMethod.GET,headers="Accept=application/json")
//	@ResponseBody
//	public Vector<Aporte>GetAportes(){
//		return this.filas;
//	}
	
	
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

	//GET AND SET
	private MultipartFile urlfile;

	public MultipartFile getUrlfile() {
		return urlfile;
	}
	public void setUrlfile(MultipartFile urlfile) {
		this.urlfile = urlfile;
	}

}
