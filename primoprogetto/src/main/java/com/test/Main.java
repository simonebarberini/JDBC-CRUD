package com.test;
import java.util.List;
import java.util.Scanner;

import com.test.dbservice.UserDao;
import com.test.model.User;
public class Main 
{
    public static void main( String[] args ){
        UserDao userdao = new UserDao();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Scegli un opzione:");
            System.out.println("1. Inserisci un nuovo utente");
            System.out.println("2. Visualizza tutti gli utenti");
            System.out.println("3. Aggiorna un utente");
            System.out.println("4. Cancella un utente");
            System.out.println("5. Esci");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Inserisci il nome: ");
                    String nome = scanner.nextLine();
                    System.out.println("inserisci email: ");
                    String email = scanner.nextLine();
                    User newUser = new User(0, nome, email);
                    userdao.createUser(newUser);
                    System.out.println("Utente creato con successo");
                    break;
                case 2:
                    List<User> utenti = userdao.getAllUsers();
                    System.out.println("Eleno utenti");
                    for(User user: utenti){
                        System.out.println(user.getId()+", "+user.getNome()+", "+user.getEmail());
                    }
                    break;
                case 3:
                    System.out.println("Inserisci l'ID dell'utente da aggiornare: ");
                    int idUpdate = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Inserisci il nuovo nome");
                    String nuovoNome = scanner.nextLine();
                    System.out.println("Inserisci la nuova email");
                    String nuovaEmail = scanner.nextLine();
                    User userToUpdate = new User(idUpdate, nuovoNome, nuovaEmail);
                    userdao.updateUser(userToUpdate);
                    System.out.println("Utente aggiornato con successo");
                    break;
                case 4:
                    System.out.println("Inserisci l'ID dell'utente da cancellare ");
                    int idDelete = scanner.nextInt();
                    userdao.deleteUser(idDelete);
                    System.out.println("Utente cancellato con successo!");
                    break;
                case 5:
                    running = false;
                    System.out.println("Uscita dal programma");
                    break;
                default:
                    System.out.println("Scelta non valida, riprova, capra.");
                    break;
            }
        }
        scanner.close();
    }
}
