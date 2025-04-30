package movie.driver;

import java.util.Scanner;

public class MovieDriver_Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String continueInput;

        do {
            Movie movie = new Movie();
//asking the user to enter a move name
            System.out.println("Enter the name of a movie:");
            String title = scanner.nextLine();
            movie.setTitle(title);

            System.out.println("Enter the rating of the movie:");
            String rating = scanner.nextLine();
            movie.setRating(rating);

            System.out.println("Enter the number of tickets sold:");
            int tickets = scanner.nextInt();
            movie.setSoldTickets(tickets);
//printing move info on the console 
            System.out.println();
            System.out.println("Movie Info: " + movie.toString());

            scanner.nextLine(); // new line
            System.out.println("Do you want to enter another movie? (y/n):");
            continueInput = scanner.nextLine();

        } while (continueInput.equalsIgnoreCase("y"));
//printing out the ending message
        System.out.println("Goodbye!");
        scanner.close();
    }
}
