package pe.gob.fovipol.migracion.service.aportes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.fovipol.migracion.mapper.AportesMapper;
import pe.gob.fovipol.migracion.model.Aportes;
import pe.gob.fovipol.migracion.model.Cliente;

@Service("aportesService")
public class AportesServiceImpl implements AportesService{

	@Autowired
	private AportesMapper aportesMapper;
	
	@Override
	public List<Aportes> getLstAportes(String P_C_C_CLIENTE, String tipo) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("P_C_C_CLIENTE", P_C_C_CLIENTE);
		params.put("TIPOAPORTE", tipo);
		aportesMapper.getLstAportes(params);
		return ((ArrayList<Aportes>)params.get("my_cursoraportes"));
	}

}
