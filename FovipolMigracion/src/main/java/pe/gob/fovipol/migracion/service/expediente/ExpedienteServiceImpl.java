package pe.gob.fovipol.migracion.service.expediente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.fovipol.migracion.mapper.ExpedienteMapper;
import pe.gob.fovipol.migracion.model.Expediente;

@Service("expedienteService")
public class ExpedienteServiceImpl implements ExpedienteService{
	//COMENTARIO;
	
	@Autowired
	private ExpedienteMapper expedienteMapper;
	
	@Override
	public List<Expediente> listarExpedientes() {
		Map<String,Object> params = new HashMap<String,Object>();
		expedienteMapper.listarExpedientes(params);
		return ((ArrayList<Expediente>)params.get("my_cursor"));
	}

	@Override
	public int getAnularExpediente(String codexpediente) {
		Map<String, Object> params= new HashMap<String,Object>();
		params.put("p_ccexpediente", codexpediente);
		expedienteMapper.getAnularExpediente(params);
		return ((Integer)params.get("rptaquery"));
	}
}
