package pe.gob.fovipol.migracion.model;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
public class Usuario {
	int id;
    String name;
    double salary;
    
    public Usuario (int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }
    
    public Usuario() {
    }
    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("Id = ").append(id).append(" - ");
    	sb.append("Name = ").append(name).append(" - ");
    	sb.append("Salary = ").append(salary);
    	return sb.toString();
    }

}