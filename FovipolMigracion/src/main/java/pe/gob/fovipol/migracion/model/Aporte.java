package pe.gob.fovipol.migracion.model;

public class Aporte {
	
	private String ctcliente;
	private String ctcip;
	private String ctcodofin;
	private String cccliente;
	private String nifecha;
	private Integer nianhio;
	private Double nimonto;
	private String nomcolumnames;
	private Integer rptaquery;
	
	public Aporte(String ctcip,String ctcliente) {
		super();
		this.ctcip = ctcip;
		this.ctcliente = ctcliente;
	}

	public Aporte() {
		// TODO Auto-generated constructor stub
	}
	
	public String getCtcliente() {
		return ctcliente;
	}
	public void setCtcliente(String ctcliente) {
		this.ctcliente = ctcliente;
	}
	public String getCtcip() {
		return ctcip;
	}
	public void setCtcip(String ctcip) {
		this.ctcip = ctcip;
	}
	public String getCtcodofin() {
		return ctcodofin;
	}
	public void setCtcodofin(String ctcodofin) {
		this.ctcodofin = ctcodofin;
	}
	public String getCccliente() {
		return cccliente;
	}
	public void setCccliente(String cccliente) {
		this.cccliente = cccliente;
	}
	public Integer getNianhio() {
		return nianhio;
	}
	public void setNianhio(Integer nianhio) {
		this.nianhio = nianhio;
	}
	public Double getNimonto() {
		return nimonto;
	}
	public void setNimonto(Double nimonto) {
		this.nimonto = nimonto;
	}
	
	public String getNomcolumnames() {
		return nomcolumnames;
	}

	public void setNomcolumnames(String nomcolumnames) {
		this.nomcolumnames = nomcolumnames;
	}

	public String getNifecha() {
		return nifecha;
	}

	public void setNifecha(String nifecha) {
		this.nifecha = nifecha;
	}

	public Integer getRptaquery() {
		return rptaquery;
	}

	public void setRptaquery(Integer rptaquery) {
		this.rptaquery = rptaquery;
	}

	@Override
	public String toString() {
		return "Aporte [ctcliente=" + ctcliente + ", ctcip=" + ctcip
				+ ", ctcodofin=" + ctcodofin + ", cccliente=" + cccliente
				+ ", nianhio=" + nianhio + ", nimonto=" + nimonto + "]";
	}
	
	
}
