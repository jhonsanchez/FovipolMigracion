package pe.gob.fovipol.migracion.service.aportes;

import java.util.List;

import pe.gob.fovipol.migracion.model.Aporte;

public interface AporteService {
	public List<Aporte>getAporteBen(String P_C_C_CLIENTE,String tipo);
}
