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
    int customerNum = 0, Interarrival = 0, arrival = 0, service = 0, serviceBegins = 0, waiting = 0, serviceEnds = 0, customerTime = 0, idle = 0, counter = 0; 
    do {
        int[] averageValues = new int[7]; // Stores the average value. Follow the arrangement on the specifications 1-7.
        data.clear(); // resets the data

        String[] row = new String[9];

        // Calculation area

        if(option.equals("A")){
         do{
            customerNum++;
            System.out.println(customerNum);
           
            // Per row calculations 
            if(customerNum == 1) {
                Interarrival = 0;
                arrival = 0;
            } else {
                Interarrival = interArrivalTimeCompare();

                arrival = Interarrival + Integer.valueOf(data.get(data.size() - 1)[2]);
                idle = Integer.valueOf(data.get(data.size() - 1)[6]) - arrival;
                if (arrival < Integer.valueOf(data.get(data.size() - 1)[6])){
                  serviceBegins = Integer.valueOf(data.get(data.size() - 1)[6]);
              } else{ // add another else if, if other situation is not anticipated
                  serviceBegins = Integer.valueOf(data.get(data.size() - 1)[2]); 
              }

            }

            service = serviceTimeCompare();
           
            waiting = serviceBegins - arrival;
            serviceEnds = service + serviceBegins;
            customerTime = serviceEnds - arrival;

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
         } while(customerNum != stoppingCriteria);
        } else {
         do {

            customerNum++;
           
            // Per row calculations 
            if(customerNum == 1) {
                Interarrival = 0;
                arrival = 0;
            } else {
                Interarrival = interArrivalTimeCompare();

                arrival = Interarrival + Integer.valueOf(data.get(data.size() - 1)[2]);
                idle = Integer.valueOf(data.get(data.size() - 1)[6]) - arrival;
                if (arrival < Integer.valueOf(data.get(data.size() - 1)[6])){
                  serviceBegins = Integer.valueOf(data.get(data.size() - 1)[6]);
              } else{ // add another else if, if other situation is not anticipated
                  serviceBegins = Integer.valueOf(data.get(data.size() - 1)[2]); 
              }

            }

            service = serviceTimeCompare();
            // Service begins, Waiting time, Service Ends, Customer Time, Idle
               //todo
            

           
            waiting = serviceBegins - arrival;
            serviceEnds = service + serviceBegins;
            customerTime = serviceEnds - arrival;
           

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
         } while( serviceEnds < stoppingCriteria);
        }

        // Prints Table
        stringPrinter(data);
        averageValues[0] = sumThisValues(data, 5) / Integer.valueOf(data.get(data.size() - 1)[0]);
        for (String[] string : data) {
         if(Integer.valueOf(string[5]) > 0) {
            counter++;
         }
        }

        averageValues[1] = (counter / Integer.valueOf(data.get(data.size() - 1)[0])) * 100;
        averageValues[2] = (sumThisValues(data, 8) / Integer.valueOf(data.get(data.size() - 1)[6])) * 100;
        averageValues[3] = sumThisValues(data, 3) / sumThisValues(data, 0);
        averageValues[4] = Integer.valueOf(data.get(data.size() - 1)[3]) / (Integer.valueOf(data.get(data.size() - 1)[0]) - 1);
        averageValues[5] = sumThisValues(data, 5) / counter;
        averageValues[6] = sumThisValues(data, 7) / Integer.valueOf(data.get(data.size() - 1)[0]);
        averagePrinter(averageValues);
      
    } while (createAnother());
   }

     // Iterator. Sums up all the values in the given column
     private static Integer sumThisValues(ArrayList<String[]> data, int column) {
        int sum = 0;

        for (String[] strings : data) {
            sum += Integer.valueOf(strings[column]);
        }
        return sum;
     }

     // Comparing Values
     private static Integer serviceTimeCompare(){
        int serviceTime = rand.nextInt(100);
        
        if(serviceTime <= 14){
            return 1;
        } else if (serviceTime >= 15 && serviceTime <= 44){
            return 2;
        } else if (serviceTime >= 45 && serviceTime <= 69){
            return 3;
        } else if (serviceTime >= 70 && serviceTime <= 89){
            return 4;
        } else {
            return 5;
        }
    }
    
    private static Integer interArrivalTimeCompare(){
        int randomArrival = rand.nextInt(1000);

       if( randomArrival <= 124) {
            return 1;
        } else if ( randomArrival >= 125 && randomArrival <= 249 ) {
            return 2;
        } else if ( randomArrival >= 250 && randomArrival <= 374 ) {
            return 3;
        } else if ( randomArrival >= 375 && randomArrival <= 499 ) {
            return 4;
        } else if ( randomArrival >= 500 && randomArrival <= 624 ) {
            return 5;
        } else if ( randomArrival >= 625 && randomArrival <= 749 ) {
            return 6;
        } else if ( randomArrival >= 750 && randomArrival <= 874 ) {
            return 7;
        } else {
            return 8;
        }
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

                limit = src.nextInt();
                calculate(limit,choice.toUpperCase()); // Gets the limitation and proceeds to calculations
   

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
        terminationOption();
    } else {
        return false;
    }
    return null;
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


} // end of class
