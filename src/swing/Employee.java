package swing;

public class Employee 
{
	int id;
	String name;
	String firstname;
	String sexe;
	String birthdate;
//	String poste; TO DO : add later
	
	public Employee(int id,String name, String firstname, String sexe, String birthdate) {
		this.id = id;
		this.name = name;
		this.firstname = firstname;
		this.sexe = sexe;
		this.birthdate = birthdate;
	}
	
	public String displayInfos()
	{
		String infos = "";
		infos =  "INFORMATIONS EMPLOYE : \n \n ----------------------------- \n \n";
		infos += " Prénom : "  + firstname + " \n \n";
		infos += " Nom : "  + name + " \n \n";
		infos += " Sexe : "  + sexe + " \n \n";
		infos += " Date de naissance : "  + name + " \n \n";
		return infos;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public String getFirstname() 
	{
		return firstname;
	}
	
	public void setFirstname(String firstname) 
	{
		this.firstname = firstname;
	}
	
	public String getSexe() 
	{
		return sexe;
	}
	
	public void setSexe(String sexe) 
	{
		this.sexe = sexe;
	}
	
	public String getBirthdate() 
	{
		return birthdate;
	}
	
	public void setBirthdate(String birthdate) 
	{
		this.birthdate = birthdate;
	}
	
	@Override
	public String toString()
	{
		return firstname + " "  + name;
	}
}
