package Inheritance;

public class Main {

	public static void main(String[] args) {
		
		CarloConti carloConti = CarloConti.creaCarloConti();
		System.out.println(carloConti);
		
		// Override nella classe anonima
		Persona gerryScotti = new Persona("Gerry", "Scotti", 7777777) {
			@Override
			public String toString() {
				return "Spawna un Gerry Scotti selvatico!\n" + super.toString();
			}
		};
		System.out.println(gerryScotti);
	}
}

class Persona {
	private String name;
	private String lastname;
	private Integer money;

	public Persona(String name, String lastname, Integer money) {
		this.name = name;
		this.lastname = lastname;
		this.money = money;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Integer getMoney() {
		return money;
	}

	public void setCF(Integer money) {
		this.money = money;
	}
	
	@Override
	public String toString() {
		return "\tNuovo tizio: [Nome = " + name + ", Cognome = " + lastname + ", Soldi = " + money + "]\n";
	}
}

class CarloConti extends Persona {

	// Costruttore privato
	private CarloConti(String name, String lastname, Integer money) {
		super(name, lastname, money);
	}
	
	public static CarloConti creaCarloConti() {
		return new CarloConti("Carlo", "Conti", 9999999);
	}
	
	@Override
	public String toString() {
		return "Spawna un Carlo Conti selvatico!\n" + super.toString();
	}
}
