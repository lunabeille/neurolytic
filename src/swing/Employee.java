package swing;

public class Employee 
{
	int id;
	String name;
	String firstname;
	String sexe;
	String birthdate;
	
	public Employee(int id,String name, String firstname, String sexe, String birthdate) {
		this.id = id;
		this.name = name;
		this.firstname = firstname;
		this.sexe = sexe;
		this.birthdate = birthdate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	
}
