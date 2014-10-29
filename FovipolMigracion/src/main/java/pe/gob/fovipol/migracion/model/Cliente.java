package pe.gob.fovipol.migracion.model;

public class Cliente {
	
	private String codicl;
	private String ciptcl;
	private String nococl;//Nombre completo
	private String nudocl;//Numero de documento
	private String direcl;
	private String telfcl;
	private String coficl;
	private String codben;
	private String codtit;
	private int total;
	
	
	public Cliente(String codicl, String ciptcl) {
		super();
		this.codicl = codicl;
		this.ciptcl = ciptcl;
	}
	
	public Cliente(String codicl, String ciptcl, String nococl, String nudocl,
			String direcl, String telfcl) {
		super();
		this.codicl = codicl;
		this.ciptcl = ciptcl;
		this.nococl = nococl;
		this.nudocl = nudocl;
		this.direcl = direcl;
		this.telfcl = telfcl;
	}
	public Cliente() {
		// TODO Auto-generated constructor stub
	}
	
	public String getCodicl() {
		return codicl;
	}
	public void setCodicl(String codicl) {
		this.codicl = codicl;
	}
	public String getCiptcl() {
		return ciptcl;
	}
	public void setCiptcl(String ciptcl) {
		this.ciptcl = ciptcl;
	}
	public String getNococl() {
		return nococl;
	}
	public void setNococl(String nococl) {
		this.nococl = nococl;
	}
	public String getNudocl() {
		return nudocl;
	}
	public void setNudocl(String nudocl) {
		this.nudocl = nudocl;
	}
	public String getDirecl() {
		return direcl;
	}
	public void setDirecl(String direcl) {
		this.direcl = direcl;
	}
	public String getTelfcl() {
		return telfcl;
	}
	public void setTelfcl(String telfcl) {
		this.telfcl = telfcl;
	}

	public String getCoficl() {
		return coficl;
	}
	public void setCoficl(String coficl) {
		this.coficl = coficl;
	}
		
	public String getCodben() {
		return codben;
	}
	public void setCodben(String codben) {
		this.codben = codben;
	}
	public String getCodtit() {
		return codtit;
	}
	public void setCodtit(String codtit) {
		this.codtit = codtit;
	}
	@Override
	public String toString() {
		return "Cliente [codicl=" + codicl + ", ciptcl=" + ciptcl + ", nococl="
				+ nococl + ", nudocl=" + nudocl + ", direcl=" + direcl
				+ ", telfcl=" + telfcl + "]";
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	
	
}
