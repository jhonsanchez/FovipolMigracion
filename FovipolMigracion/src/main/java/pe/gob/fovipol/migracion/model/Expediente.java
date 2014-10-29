package pe.gob.fovipol.migracion.model;

public class Expediente {
	//select e.*,e.c_c_expediente,e.n_i_estado from s10exp e;
	private String ccexpediente;
	private Integer niestado;
	public String getCcexpediente() {
		return ccexpediente;
	}
	public void setCcexpediente(String ccexpediente) {
		this.ccexpediente = ccexpediente;
	}
	public Integer getNiestado() {
		return niestado;
	}
	public void setNiestado(Integer niestado) {
		this.niestado = niestado;
	}
	
}
