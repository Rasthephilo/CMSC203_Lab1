package movie.driver;
/*
 * Class: CMSC203 
 * Instructor: Ahemed Terek
 * Description: This program collects and displays movie data (title, rating, tickets sold)
 * using the Movie class. It allows the user to enter movie information via the console.
 * Task 1 collects one movie entry. Task 2 loops to collect multiple movies.
 * Due: 4/30/2025
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming assignment independently. 
 * I have not copied the code from a student or any source. 
 * I have not given my code to any student.
 * Print your Name here: Ransford Apau
 */


import java.util.Scanner;

public class MovieDriver_Task1 {
    public static void main(String[] args) {
    	
    	// Create Scanner object to read user input
        Scanner scanner = new Scanner(System.in);
     // new Movie object
        Movie movie = new Movie();
     // Ask user for the movie title

        System.out.println("Enter the name of a movie:");
        String title = scanner.nextLine();
        movie.setTitle(title);
     // Asking user for the movie rating
        System.out.println("Enter the rating of the movie:");
        String rating = scanner.nextLine();
        movie.setRating(rating);

        System.out.println("Enter the number of tickets sold:");
        int tickets = scanner.nextInt();
        movie.setSoldTickets(tickets);
     // Print the movie's information
        System.out.println();
        System.out.println("Movie Info: " + movie.toString());

        scanner.close();
    }
}
