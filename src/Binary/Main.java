package Binary;

import java.io.*;

public class Main {

	protected static final String FILE_NAME = "file.bin";

	public static void main(String[] args) {

		Persona p1 = new Persona("Tizio", 99) {
			@Override
			public String toString() {
				return "Persona anonima: " + getName() + ", età: " + getAge();
			}
		};
		Persona p2 = new Persona("Caio", 69);
		Persona p3 = new NewPersona("Sempronio", 10);

		p1.bWrite();
		p2.bWrite();
		p3.bWrite();


		// Legge il file binario
		// bRead();
		bReadOptimized();
		System.out.println(p1); // Deve stampare toString() solo di NewPersona
		System.out.println(p2); // Deve stampare toString() solo di NewPersona
		System.out.println(p3); // Deve stampare toString() solo di NewPersona
	}

	// Leggere un file binario
	public static void bRead() {
		FileInputStream fis = null;

		try {
			fis = new FileInputStream("file.bin");
			int data;
			while ((data = fis.read()) != -1) {
				// Stampa i byte letti come caratteri
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
					break; // Fine del file, interrompiamo la lettura
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
	private static final long serialVersionUID = 1L; // Aggiunto per sicurezza nella serializzazione
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

	@Override
	public String toString() {
		return "Persona: " + name + ", Età: " + age;
	}
}

// Classe NewPersona che estende Persona
class NewPersona extends Persona {
	private static final long serialVersionUID = 1L;
	public NewPersona(String name, Integer age) {
		super(name, age);
	}

	public NewPersona() {
		super("Default", 0); // Valori di default
	}

	// Override di toString() solo per questa classe
	@Override
	public String toString() {
		return "NewPersona: " + getName() + ", età: " + getAge();
	}
}
