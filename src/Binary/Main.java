package Binary;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

	protected static final String FILE_NAME = "file.bin";

	public static void main(String[] args) {

		Persona p1 = new Persona("Tizio", 12) {
			@Override
			public String toString() {
				return "Persona anonima: " + getName() + ", età: " + getAge();
			}
		};
		Persona p2 = new Persona("Caio", 69);
		Persona p3 = new NewPersona(new Persona("Sempronio", 26));
		
		ArrayList<Persona> persone = new ArrayList<Persona>(Arrays.asList(p1,p2,p3));
		List<Persona> maggiorenni = persone
				.stream()
				.filter(p -> p.getAge() > 18)
				.collect(Collectors.toList());

		p1.bWrite();
		p2.bWrite();
		p3.bWrite();

		System.out.println(p1); // Usa toString() specifico
		System.out.println(p2); // Usa toString() di Object
		System.out.println(p3); // Usa toString() di NewPersona
		
		System.out.println("\n");
		System.out.println("Tutti:");
        persone.forEach(System.out::println);
        System.out.println("\n");
        System.out.println("\nMaggiorenni:");
        maggiorenni.forEach(System.out::println);
		
		// Legge il file binario
		// bRead();
		// bReadOptimized();
	}

	// Leggere un file binario
	public static void bRead() {
		FileInputStream fis = null;

		try {
			fis = new FileInputStream("file.bin");
			int data;
			while ((data = fis.read()) != -1) {
				System.out.print((char) data);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// Metodo per leggere il file binario come oggetti
	public static void bReadOptimized() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
			while (true) {
				try {
					Object obj = ois.readObject();
					System.out.println(obj);
				} catch (EOFException e) {
					break;
				} catch (ClassNotFoundException e) {
					System.out.println("Errore: Classe non trovata.");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

// Classe Persona che implementa Serializable
class Persona implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private int age;

	public Persona(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return this.name;
	}

	public Integer getAge() {
		return this.age;
	}

	// Metodo per scrivere un oggetto Persona su file binario
	public void bWrite() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Main.FILE_NAME))) {
			oos.writeObject(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

// Classe NewPersona che estende Persona
class NewPersona extends Persona {
	private static final long serialVersionUID = 1L;
	public NewPersona(String name, Integer age) {
		super(name, age);
	}

	public NewPersona(Persona p) {
		this(p.getName(), p.getAge()); // Dati in arrivo da Persona
	}

	// Override di toString() solo per questa classe
	@Override
	public String toString() {
		return "NewPersona: " + getName() + ", età: " + getAge();
	}
}
