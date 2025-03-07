/*Esercitazione: Gestione di un Magazzino
Obiettivo:
Creare un programma in Java che gestisca un magazzino di prodotti, utilizzando un file di testo per memorizzare e recuperare le informazioni.
Requisiti:
Il programma deve consentire all'utente di:
1️⃣ Aggiungere un prodotto al magazzino (salvandolo nel file magazzino.txt).
2️⃣ Visualizzare l’elenco dei prodotti disponibili.
3️⃣ Cercare un prodotto per nome.
4️⃣ Aggiornare la quantità di un prodotto.
5️⃣ Eliminare un prodotto dal magazzino (opzionale, per renderlo più impegnativo).
6️⃣ Uscire dal programma.
Formato del file magazzino.txt
Ogni riga del file rappresenta un prodotto e ha il seguente formato:
"Nome,Categoria,Prezzo,Quantità"

ESEMPIO
Laptop,Elettronica,799.99,5
Mouse,Elettronica,25.50,10
Scarpe,Sport,49.90,20

TRACCIA FORMALE
Scrivere un programma Java che mostri un menu interattivo con queste opzioni:
1️⃣ Aggiungere un prodotto: l’utente inserisce nome, categoria, prezzo e quantità, e il programma salva il prodotto nel file.
2️⃣ Visualizzare tutti i prodotti: il programma legge il file magazzino.txt e mostra i dati dei prodotti.
3️⃣ Cercare un prodotto per nome: l’utente inserisce il nome di un prodotto e il programma lo cerca nel file.
4️⃣ Aggiornare la quantità di un prodotto: l’utente inserisce il nome di un prodotto e la nuova quantità, il file viene aggiornato.
5️⃣ Eliminare un prodotto (opzionale): l’utente inserisce il nome di un prodotto e il programma lo rimuove dal file.
6️⃣ Uscire dal programma.
....
Qui vi mostro un esempio di funzionamento:

===== MAGAZZINO PRODOTTI =====
1.⁠ ⁠Aggiungi prodotto
2.⁠ ⁠Visualizza prodotti
3.⁠ ⁠Cerca prodotto per nome
4.⁠ ⁠Aggiorna quantità prodotto
5.⁠ ⁠Elimina prodotto
6.⁠ ⁠Esci
Scelta: 1
Inserisci nome prodotto: Laptop
Inserisci categoria: Elettronica
Inserisci prezzo: 799.99
Inserisci quantità: 5
Prodotto aggiunto con successo!

===== MAGAZZINO PRODOTTI =====
1.⁠ ⁠Aggiungi prodotto
2.⁠ ⁠Visualizza prodotti
3.⁠ ⁠Cerca prodotto per nome
4.⁠ ⁠Aggiorna quantità prodotto
5.⁠ ⁠Elimina prodotto
6.⁠ ⁠Esci
Scelta: 2
Elenco prodotti:
Laptop - Categoria: Elettronica - Prezzo: 799.99€ - Quantità: 5
Mouse - Categoria: Elettronica - Prezzo: 25.50€ - Quantità: 10*/


package ExMagazzino;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner;

    static {
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        int choice = 0;
        do {
            menu();
            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Inserisci un numero valido!");
                scanner.nextLine();
                continue;
            }
            scanner.nextLine();

            switch (choice) {
                case 1:
                    Warehouse.add();
                    break;
                case 2:
                    Warehouse.list();
                    break;
                case 3:
                    Warehouse.find();
                    break;
                case 4:
                    Warehouse.update();
                    break;
                case 5:
                    Warehouse.del();
                    break;
                case 6:
                    System.out.println("Uscita dal programma.");
                    break;
                default:
                    System.out.println("Scelta non valida, riprova.");
            }
        } while (choice != 6);
        scanner.close();
    }

    public static void menu() {
        System.out.println("\n===== MAGAZZINO PRODOTTI =====");
        System.out.println("\t1. Aggiungi prodotto");
        System.out.println("\t2. Visualizza prodotti");
        System.out.println("\t3. Cerca prodotto per nome");
        System.out.println("\t4. Aggiorna quantità prodotto");
        System.out.println("\t5. Elimina prodotto");
        System.out.println("\t6. Esci");
        System.out.println("\n=============================\n");
    }
}
