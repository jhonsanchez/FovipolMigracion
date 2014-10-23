package pe.gob.fovipol.migracion.service.aportes;

import java.util.List;

import pe.gob.fovipol.migracion.model.Aportes;

public interface AportesService {
	public List<Aportes>getLstAportes(String P_C_C_CLIENTE,String tipo);
}
