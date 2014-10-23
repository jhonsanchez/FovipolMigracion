package pe.gob.fovipol.migracion.service.aportes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.fovipol.migracion.mapper.AporteMapper;
import pe.gob.fovipol.migracion.model.Aporte;

@Service("usuarioService")
public class AporteServiceImpl implements AporteService{
	
	@Autowired
	private AporteMapper aporteMapper;
	
	@Override
	public List<Aporte> getAporteBen(String P_C_C_CLIENTE, String tipo) {
		Map<String,Object> params = new HashMap<String,Object>();
		aporteMapper.getAporteBen(params);
		return ((ArrayList<Aporte>)params.get("my_cursoraportes"));
	}

}
