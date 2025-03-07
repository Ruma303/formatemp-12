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
		System.out.println(gerryScotti);;
		
		Persona nomeLungo = new Persona("questoNomeE'TroppoLungo", "cognome", 100);
        System.out.println(nomeLungo);

        Persona elonMusk = new Persona("nome", "cognome", Integer.MAX_VALUE);
        System.out.println(elonMusk);
	}
}

class Persona {
	// Inizializzazioni di default
	private String name = "";
	private String lastname = "";
	private Integer money = 0;

	public Persona(String name, String lastname, Integer money) {
		// Assegnamo alle variabili di istanza i valori dai setter
		// Essendo i setter delle funzioni, possiamo aggiungere la logica che vogliamo
		// Soprattutto aggiungere controlli e verifiche.
		
		String tempName = setName(name);
        String tempLastname = setLastname(lastname);
        Integer tempMoney = setMoney(money + 100);
		
		if (tempName != null) {
            this.name = tempName;
        }
        if (tempLastname != null) {
            this.lastname = tempLastname;
        }
        if (tempMoney != null) {
            this.money = tempMoney;
        }
	}

	public String getName() {
		return name;
	}

	public String setName(String name) {
		if (name.length() > 20) {
			System.out.println("Troppi caratteri! Massimo 20!");
			return null;
		}
		return name;
	}

	public String getLastname() {
		return lastname;
	}

	public String setLastname(String lastname) {
		if (lastname.length() > 20) {
			System.out.println("Troppi caratteri! Massimo 20!");
			return null;
		}
		return lastname;
	}

	public Integer getMoney() {
		return money;
	}

	public Integer setMoney(Integer money) {
		if (money > Integer.MAX_VALUE) {
			System.out.println("Bro, se fossi Elon Musk non faresti il programmatore");
			return null;
		}
		return money;
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
	
	// No problem, lo richiamiamo da un metodo statico pubblico
	public static CarloConti creaCarloConti() {
		return new CarloConti("Carlo", "Conti", 9999999);
	}
	
	@Override
	public String toString() {
		return "Spawna un Carlo Conti selvatico!\n" + super.toString();
	}
}
