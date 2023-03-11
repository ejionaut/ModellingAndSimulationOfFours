import java.lang.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;


public class simulation {
   static Scanner src = new Scanner(System.in);
   static Random rand = new Random();
   public static void main(String[] args) {
       terminationOption();
   }

   // Calculations

   private static void calculate(int stoppingCriteria, String option){
    ArrayList<String[]> data = new ArrayList<String[]>(); // Stores per row
    do {
        int customerNum = 0, Interarrival = 0, arrival = 0, service = 0, serviceBegins = 0, waiting = 0, serviceEnds = 0, customerTime = 0, idle = 0; 
        int[] averageValues = new int[7]; // Stores the average value. Follow the arrangement on the specifications 1-7.
        data.clear(); // resets the data

        String[] row = new String[9];

        // Calculation area
        do{
            customerNum++;
           
            // Per row calculations 
            if(customerNum == 1) {
                Interarrival = 0;
                arrival = 0;
            } else {
                Interarrival = interArrivalTimeCompare();
                arrival = Interarrival + Integer.valueOf(data.get(customerNum - 1)[2]);
            }

            service = serviceTimeCompare();
            // Service begins, Waiting time, Service Ends, Customer Time, Idle
               //todo

            // Setting of Values;
            row[0] = Integer.toString(customerNum);
            row[1] = Integer.toString(Interarrival);
            row[2] = Integer.toString(arrival);
            row[3] = Integer.toString(service);
            row[4] = Integer.toString(serviceBegins);
            row[5] = Integer.toString(waiting);
            row[6] = Integer.toString(serviceEnds);
            row[7] = Integer.toString(customerTime);
            row[8] = Integer.toString(idle);
            data.add(row);

        } while(option.equals("A") && customerNum == stoppingCriteria || option.equals("B") && serviceEnds >= stoppingCriteria);

        // Prints Table
        stringPrinter(data);
       
       // Average Calculations
            //to do

        // Print summary
        averagePrinter(averageValues);
    } while (createAnother());
   }

     // Comparing Values
     private static Integer serviceTimeCompare(){
        int serviceTime = rand.nextInt(100);
        // Todo
        return serviceTime;
    }
    
    private static Integer interArrivalTimeCompare(){
        int randomArrival = rand.nextInt(1000);

        if( randomArrival <= 124) {
            randomArrival = 1;
        } else if ( randomArrival >= 125 && randomArrival <= 249 ) {
            randomArrival = 2;
        } else if ( randomArrival >= 250 && randomArrival <= 374 ) {
            randomArrival = 3;
        } else if ( randomArrival >= 375 && randomArrival <= 499 ) {
            randomArrival = 4;
        } else if ( randomArrival >= 500 && randomArrival <= 624 ) {
            randomArrival = 5;
        } else if ( randomArrival >= 625 && randomArrival <= 749 ) {
            randomArrival = 6;
        } else if ( randomArrival >= 750 && randomArrival <= 874 ) {
            randomArrival = 7;
        } else {
            randomArrival = 8;
        }

        return randomArrival;
    }

   // User Choices

   private static void terminationOption() {
       System.out.println("Choose an Option: " + 
                                "\n\tA: Customer Based "+
                                "\n\tB: Number of Minutes ");
       buffer();
       System.out.println("Enter you choice: ");
       String choice = src.nextLine();
       int limit;


       if( choice.equals("A")|| choice.equals("B") || choice.equals("a") || choice.equals("b")){

            if(choice.equals("A") || choice.equals("a")){
                System.out.println("Enter the amount of customers: ");
            } else {
                System.out.println("Enter the time limit (minutes): ");
            }

            try{
                limit = src.nextInt();
                calculate(limit,choice.toUpperCase()); // Gets the limitation and proceeds to calculations
            } catch (Exception e) {
                System.out.println("Error in input.. Redo option Choice");
                terminationOption();
            }

       } else {
           System.out.println("Error in input try again.");
           buffer();
           terminationOption();
       }
   }

   private static Boolean createAnother(){
    System.out.println("Do you want to create another simulation? ");
    String choice = src.nextLine();

    if ( choice.equals("yes") || choice.equals("YES") || choice.equals("Yes") || choice.equals("y") || choice.equals("Y")) {
        return true;
    } else {
        return false;
    }
   }

   // Printers

   private static void stringPrinter(ArrayList<String[]> data){
    System.out.printf("%10s %20s %15s %15s %21s %14s %20s %35s %15s","Customer No.", "Interarrival Time",
            "Arrival Time", "Service Time","Time Service Begins","Waiting Time",
            "Time Service Ends", "Time Customer Spends in System", "Idle Time");


    for (int x = 0; x < data.size(); x++) {
        String cN = data.get(x)[0], iT = data.get(x)[1], aT = data.get(x)[2],
                sT = data.get(x)[3], tSB = data.get(x)[4], wT = data.get(x)[5],
                tSE = data.get(x)[6], tCSS = data.get(x)[7], iTS = data.get(x)[8];

        System.out.printf("\n%6s %18s %18s %15s %18s %16s %18s %28s %26s",cN,iT,aT,sT,tSB,wT,tSE,tCSS,iTS);
    }

    buffer();
   }

   private static void averagePrinter(int[] data){
    System.out.println("\nAverage Waiting time for customer : " + data[0] +
                       "\nThe probability that a customer has to wait in the queue : " + data[1] +
                       "\nThe proportion of idle time of the server : " + data[2] +
                       "\nThe average service time : " + data[3] +
                       "\nAverage time between arrivals : " + data[4] +
                       "\nThe average waiting time for those who wait in queue : " + data[5] +
                       "\nThe average time a customer spends in the system : " + data[6]);
    buffer();
   }

   private static void buffer(){
       System.out.println();
       System.out.println();
   }

}
