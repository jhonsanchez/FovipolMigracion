package pe.gob.fovipol.migracion.service.expediente;

import java.util.List;

import pe.gob.fovipol.migracion.model.Expediente;

public interface ExpedienteService {
	//COMENTARIO;
	
	public List<Expediente> listarExpedientes();
	public int getAnularExpediente(String codexpediente);
	
}
