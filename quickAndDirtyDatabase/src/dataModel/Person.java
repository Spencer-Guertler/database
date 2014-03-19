package dataModel;

public class Person
{
	private String name;
	private String birthDate;
	private int age;
	private String deathDate;
	private boolean isMarried;
	private boolean hasChildren;
	
	public Person()
	{
		name = "";
		birthDate = "unknown";
		deathDate = "unknown";
		isMarried = false;
		hasChildren = false;
		age = -500;
		
	}
	
	public Person(String name, String DeathDate)
	{
		this.name = name;
		this.deathDate = deathDate;
	}
	
	public boolean isMarried()
	{
		return isMarried;
	}

	public void setMarried(boolean isMarried)
	{
		this.isMarried = isMarried;
	}

	public boolean isHasChildren()
	{
		return hasChildren;
	}

	public void setHasChildren(boolean hasChildren)
	{
		this.hasChildren = hasChildren;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getBirthDate()
	{
		return birthDate;
	}

	public void setBirthDate(String birthDate)
	{
		this.birthDate = birthDate;
	}

	public String getDeathDate()
	{
		return deathDate;
	}

	public void setDeathDate(String deathDate)
	{
		this.deathDate = deathDate;
	}

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}
	
	public String toString()
	{
		String personInfo = "";
		
		personInfo += "this is: " + name;
		personInfo += "; died around: " + deathDate;
		personInfo += " at age: " + age;
		
		return personInfo;
	}
}
