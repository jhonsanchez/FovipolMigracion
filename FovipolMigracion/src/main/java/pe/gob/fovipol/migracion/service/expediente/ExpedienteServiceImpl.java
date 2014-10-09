package pe.gob.fovipol.migracion.service.expediente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import pe.gob.fovipol.migracion.mapper.ExpedienteMapper;
import pe.gob.fovipol.migracion.model.Expediente;

public class ExpedienteServiceImpl implements ExpedienteService{
	@Autowired
	private ExpedienteMapper expedienteMapper;
	
	@Override
	public List<Expediente> listarExpedientes() {
		Map<String,Object> params = new HashMap<String,Object>();
		expedienteMapper.listarExpedientes(params);
		return ((ArrayList<Expediente>)params.get("my_cursor"));
	}

}
