package pe.gob.fovipol.migracion.service.cliente;

import java.util.List;
import java.util.Map;

import pe.gob.fovipol.migracion.model.Aporte;
import pe.gob.fovipol.migracion.model.Cliente;

public interface ClienteService {
	
	public List<Cliente> getCustomer();
	public List<Cliente> getCustomer(String ccCliente,int tipo);
	public int getUpdateAportes(Aporte aporte);
	public Cliente getCustomerDet(Cliente cliente);
	public Cliente getTotalBen(Cliente cliente);
	public void getTraspasaAporte(String codben,String codtit);
	
}
