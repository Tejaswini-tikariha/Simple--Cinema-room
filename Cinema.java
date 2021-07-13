package Cinema;
import java.util.Scanner;
public class Cinema {
    final static Scanner sc = new Scanner(System.in);
    static int totalRows = 0, totalSeats = 0;
    static int aRow = 0, aSeat = 0;
    static int countTen = 0, countEight = 0;
    public static void main(String[] args) {
        try { 
            System.out.println("Enter the number of rows:");
            totalRows = sc.nextInt();
            System.out.println("Enter the number of seats in each row:");
            totalSeats = sc.nextInt();   
            String[][] arr = new String[totalRows][totalSeats];
            for (int i = 0; i < totalRows; i++) {
                for (int j = 0; j < totalSeats; j++) {
                    arr[i][j] = " S";
                }
            }
            askInputes(arr);
        } catch (NumberFormatException e) {
            System.out.println("Wrong input!");
        }        
    }
    public static void askInputes(String[][] arr) {
        System.out.println("\n1. Show the seats\n" +
                            "2. Buy a ticket\n" +
                            "3. Statistics\n" +
                            "0. Exit");
        
        int input = sc.nextInt();
        if (input >= 0) { 
            switch(input) { 
                case 1:
                    printCinema(arr);
                    break;
                case 2:
                    takeBooks(arr);
                    break;
                case 3:
                    statistics(arr);
                    break;
                case 0:
                    break;
                default :
                    System.out.println("Wrong input!");
                    askInputes(arr);
                    break;
            }
        } else {
            System.out.println("Wrong input!");
            askInputes(arr);
        }
    }
    public static void statistics(String[][] arr) {
        System.out.println("Number of purchased tickets: " + getBookedTickets());
        System.out.println("Percentage: " + String.format("%.2f", getPercentage(arr)) + "%");
        System.out.println("Current income: $" + currentIncome());
        System.out.println("Total income: $" + TotalIncome());
        askInputes(arr);
    }
    public static void takeBooks(String[][] arr) {
        try { 
            System.out.println("\nEnter a row number:");
            aRow = sc.nextInt();
            System.out.println("Enter a seat number in that row:");
            aSeat = sc.nextInt();     
            if (checkBook(arr)) {
                takeBooks(arr);
            } else {
                arr[aRow - 1][aSeat - 1] = " B";
            }
            
            if(totalRows * totalSeats <= 60) {
                System.out.println("\nTicket price: $10");
                countTen++;
                
            } else {
                if (aRow <= totalRows / 2) {
                    System.out.println("\nTicket price: $10");
                    countTen++;
                } else { 
                    System.out.println("\nTicket price: $8");  
                    countEight++;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Wrong input!");
        }
        askInputes(arr);
    }
    public static void printCinema(String[][] arr) {
        System.out.println("Cinema:");
        System.out.print(" ");
        for (int i = 1; i <= totalSeats; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        for (int i = 0; i < totalRows; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < totalSeats; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }   
        askInputes(arr);
    }    
    
    public static int getBookedTickets() {
        return countTen + countEight;
    }
    public static double getPercentage(String[][] arr) {
        double booked = (double)getBookedTickets();
        double percent = (double)(booked / (totalRows * totalSeats)) * 100;
        return percent;
    }
    public static int currentIncome() {
        return countTen * 10 + countEight * 8;
    }
    public static int TotalIncome() {
        if (totalRows * totalSeats <= 60) {
            return totalRows * totalSeats * 10;
        } else {
            int half = totalRows / 2;
            int backHalf = totalRows - half;
            return half * totalSeats * 10 + backHalf * totalSeats * 8;
        }
    }
    public static boolean checkBook(String[][] arr) {
        boolean isBooked = false;   
        for (int i = 0; i < totalRows; i++) {
            for (int j = 0; j < totalSeats; j++) {
                if (arr[aRow - 1][aSeat - 1].equals(" B")) {
                    System.out.println("That ticket has already been purchased!");
                    isBooked = true;
                    break;  
                } 
            }
            if (isBooked) {
                return isBooked;
            }
        }        
        return isBooked;
    }
}
