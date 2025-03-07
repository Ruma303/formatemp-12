package ExMagazzino;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Warehouse {

    private static final String FILE_NAME;
    private static final Scanner scanner;
    private static ArrayList<Product> products;

    static {
        FILE_NAME = "magazzino.txt";
        scanner = new Scanner(System.in);
        products = new ArrayList<>();
        // Se il file esiste, possiamo caricare i prodotti nell'ArrayList (opzionale)
        loadProductsFromFile();
    }
    
    // Carica i prodotti dal file nell'ArrayList products (se esistente)
    private static void loadProductsFromFile() {
        File file = new File(FILE_NAME);
        
        if (file.exists() && file.isFile()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
                String line = reader.readLine();
                while (line != null) {
                    String[] data = line.split(",");
                    if (data.length == 4) {
                        String name = data[0];
                        String category = data[1];
                        Double price = Double.parseDouble(data[2]);
                        Integer quantity = Integer.parseInt(data[3]);
                        products.add(new Product(name, category, price, quantity));
                    }
                    line = reader.readLine();
                }
            } catch (IOException e) {
                System.out.println("Errore nel caricamento dei prodotti: " + e.getMessage());
            }
        }
    }

    // Salva l'intera lista di prodotti sul file (riscrittura completa)
    private static void saveProductsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Product p : products) {
                writer.write(p.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Errore nella scrittura dei prodotti: " + e.getMessage());
        }
    }

    public static void add() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            System.out.print("Inserisci nome: ");
            String name = scanner.nextLine().trim();

            System.out.print("Inserisci categoria: ");
            String category = scanner.nextLine().trim();

            System.out.print("Inserisci prezzo: ");
            Double price = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Inserisci quantità: ");
            Integer quantity = scanner.nextInt();
            scanner.nextLine();

            Product p = new Product(name, category, price, quantity);

            // Controllo duplicati: se esiste già un prodotto con lo stesso nome, non lo aggiungo
            if (findProductByName(name) != null) {
                System.out.println("Prodotto già esistente!");
                return;
            }

            writer.write(p.toString());
            writer.newLine();
            products.add(p);
            System.out.println("Prodotto " + name + " aggiunto con successo!\n");
        } catch (IOException e) {
            System.out.println("Errore nella scrittura: " + e.getMessage());
        }
    }

    public static void list() {
    	if(!productsExist()) return;
        
        System.out.println("\nElenco prodotti:");
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            System.out.println((i + 1) + ". " + p.getName() + " - Categoria: " + p.getCategory() 
                    + " - Prezzo: " + p.getPrice() + "€ - Quantità: " + p.getQuantity());
        }
    }

    public static void find() {
    	if(!productsExist()) return;
    	
        System.out.print("Inserisci il nome del prodotto da cercare: ");
        String searchName = scanner.nextLine().trim().toLowerCase();
        
        boolean found = false;
        
        for (Product p : products) {
            if (p.getName().toLowerCase().contains(searchName)) {
                System.out.println("\nProdotto trovato: " + p.getName() + " - Categoria: " 
                        + p.getCategory() + " - Prezzo: " + p.getPrice() + "€ - Quantità: " 
                        + p.getQuantity());
                found = true;
            }
        }
        if (!found) {
            System.out.println("Prodotto non trovato.");
        }
    }

    public static void update() {
    	if(!productsExist()) return;
        
        System.out.print("Inserisci il nome del prodotto da aggiornare: ");
        String searchName = scanner.nextLine().trim().toLowerCase();
        
        for (Product p : products) {
            if (p.getName().toLowerCase().equals(searchName)) {
                
            	System.out.print("Inserisci la nuova quantità: ");
                int newQuantity = scanner.nextInt();
                scanner.nextLine();
                
                p.setQuantity(newQuantity);
                System.out.println("Prodotto aggiornato: " + p.getName() + " ora ha quantità " + newQuantity);
                saveProductsToFile(); // Aggiorna il file
                return;
            }
        }
        System.out.println("Prodotto non trovato.");
    }

    public static void del() {
    	if(!productsExist()) return;
    	
        System.out.print("Inserisci il nome del prodotto da eliminare: ");
        String searchName = scanner.nextLine().trim().toLowerCase();
        
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            
            if (p.getName().toLowerCase().equals(searchName)) {
                products.remove(i);
                System.out.println("Prodotto " + p.getName() + " eliminato con successo!");
                saveProductsToFile(); // Aggiorna il file
                return;
            }
        }
        System.out.println("Prodotto non trovato.");
    }

    // Metodo di supporto per trovare un prodotto per nome (ricerca esatta)
    private static Product findProductByName(String name) {
        for (Product p : products) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    // Controlla se il file esiste
    private static boolean fileExists() {
        File file = new File(FILE_NAME).getAbsoluteFile();
        if (file.exists() && file.isFile())
            return true;
        else {
            System.out.println("\nIl file " + FILE_NAME + " non esiste. Aggiungi un prodotto per crearlo.\n");
            return false;
        }
    }
    
   // Verifica se ci sono prodotti
    private static boolean productsExist() {
    	if (products.isEmpty()) {
            System.out.println("Non ci sono prodotti in magazzino.");
            return false;
        }
    	return true;
    }
}
