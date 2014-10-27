package pe.gob.fovipol.migracion.service.cliente;

import java.util.List;

import pe.gob.fovipol.migracion.model.Aporte;
import pe.gob.fovipol.migracion.model.Cliente;

public interface ClienteService {
	public List<Cliente> getCustomer();
	public List<Cliente> getCustomer(String ccCliente,int tipo);
	public void getUpdateAportes(Aporte aporte);
}
