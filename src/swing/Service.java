package swing;

public class Service 
{
	String name;
	Employee [] team;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Employee[] getTeam() {
		return team;
	}
	public void setTeam(Employee[] team) {
		this.team = team;
	}
	
	@Override
	public String toString()
	{
		String s =  "nom service = " + this.getName() + " \n ------  \n Liste des employés : \n";
		for(Employee emp : this.getTeam())
		{
			System.out.println(emp.getFirstname());
			s += emp.getFirstname() + "\n";
		}
		return s;
	}
}
