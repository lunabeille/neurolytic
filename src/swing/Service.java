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
		return name;
	}
}
