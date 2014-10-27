package pe.gob.fovipol.migracion.service.cliente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.fovipol.migracion.mapper.ClienteMapper;
import pe.gob.fovipol.migracion.model.Aporte;
import pe.gob.fovipol.migracion.model.Cliente;
import pe.gob.fovipol.migracion.model.Usuario;

@Service("clienteService")
public class ClienteServiceImpl implements ClienteService{
	
	@Autowired
	private ClienteMapper clientMapper;
	
	@Override
	public List<Cliente> getCustomer() {
		return null;
	}

	@Override
	public List<Cliente> getCustomer(String ccCliente,int tipo) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("P_C_C_CLIENTE", ccCliente);
		params.put("TIPO", tipo);
		clientMapper.getCustomer(params);
		return ((ArrayList<Cliente>)params.get("my_cursorcustomer")); 
	}

	@Override
	public void getUpdateAportes(Aporte aporte) {
		clientMapper.getUpdateAportes(aporte);
	}

}
