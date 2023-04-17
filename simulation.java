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
        int customerNum = 0, Interarrival = 0, arrival = 0, service = 0, serviceBegins = 0, waiting = 0, serviceEnds = 0, customerTime = 0, idle = 0; 
        
        data.clear(); // resets the data

        // Calculation area

        if(option.equals("A")){
            do{
            customerNum++;
            String[] row = new String[9];
            
            // Per row calculations 
            if(customerNum == 1) {
                Interarrival = 0;
                arrival = 0;
            } else {
                Interarrival = interArrivalTimeCompare();

                arrival = Interarrival + Integer.valueOf(data.get(data.size() - 1)[2]);
                
                if (arrival < Integer.valueOf(data.get(data.size() - 1)[6])){
                    serviceBegins = Integer.valueOf(data.get(data.size() - 1)[6]);
                } else{ // add another else if, if other situation is not anticipated
                    serviceBegins = arrival; 
                }

                if (arrival > Integer.valueOf(data.get(data.size() - 1)[6])){
                    idle = arrival - Integer.valueOf(data.get(data.size() - 1)[6]);
                } else {
                    idle = 0;
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
            String[] row = new String[9];
            // Per row calculations 
            if(customerNum == 1) {
                Interarrival = 0;
                arrival = 0;
            } else {
                Interarrival = interArrivalTimeCompare();

                arrival = Interarrival + Integer.valueOf(data.get(data.size() - 1)[2]);
                
                if (arrival < Integer.valueOf(data.get(data.size() - 1)[6])){
                    serviceBegins = Integer.valueOf(data.get(data.size() - 1)[6]);
                } else{ // add another else if, if other situation is not anticipated
                    serviceBegins = arrival; 
                }

                if (arrival > Integer.valueOf(data.get(data.size() - 1)[6])){
                    idle = arrival - Integer.valueOf(data.get(data.size() - 1)[6]);
                } else {
                    idle = 0;
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
        averagePrinter(data);

        

        System.out.println("Do you want to run another simulation? (Yes/No)");
        String answer = src.next();
        if (answer.equalsIgnoreCase("Yes")) {
           terminationOption();
        } else {
            System.out.println("Thank you for using Single Channel Queuing System Simulation!");
            return;
        }
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

    String[] sum = totalValues(data);

    System.out.println();
    System.out.printf("\n%6s %18s %18s %15s %18s %16s %18s %28s %26s",sum[0],sum[1],sum[2],sum[3],sum[4],sum[5],sum[6],sum[7],sum[8]);

    buffer();
   }

   private static String[] totalValues(ArrayList<String[]> data){
    String[] finalValues = new String[9]; 
    int iAT = 0, aT = 0, sT = 0, tSB = 0, wT = 0, tSE = 0, tcSS = 0, iT = 0;
    finalValues[0] = "TOTAL";

    for(String[] s : data){
            iAT += Integer.valueOf(s[1]);
            aT += Integer.valueOf(s[2]);
            sT += Integer.valueOf(s[3]);
            tSB += Integer.valueOf(s[4]);
            wT += Integer.valueOf(s[5]);
            tSE += Integer.valueOf(s[6]);
            tcSS += Integer.valueOf(s[7]);
            iT += Integer.valueOf(s[8]);
    }

    finalValues[1] = Integer.toString(iAT);
    finalValues[2] = Integer.toString(aT);
    finalValues[3] = Integer.toString(sT);
    finalValues[4] = Integer.toString(tSB);
    finalValues[5] = Integer.toString(wT);
    finalValues[6] = Integer.toString(tSE);
    finalValues[7] = Integer.toString(tcSS);
    finalValues[8] = Integer.toString(iT);

    return finalValues;
   }

   private static void averagePrinter(ArrayList<String[]> data){
    System.out.println("\nAverage Waiting time for customer : " + calculateAverageWaitingTime(data) + " mins" +
                       "\nThe probability that a customer has to wait in the queue : " + calculateCustomersWithWaitTime(data) + " mins" +
                       "\nThe proportion of idle time of the server : " + String.format("%.2f",calculateProportionIdleTime(data)) + " mins" +
                       "\nThe average service time : " +  String.format("%.2f",calculateAverageServiceTime(data)) + " mins" +
                       "\nAverage time between arrivals : " + String.format("%.2f",calculateAverageTimeBetweenArrivals(data))  + " mins" + 
                       "\nThe average waiting time for those who wait in queue : " + String.format("%.2f",calculateTotalWaitingTimeWithQueue(data)) + " mins" +
                       "\nThe average time a customer spends in the system : " + String.format("%.2f",calculateAverageTimeCustomerSpends(data)) + " mins");
    buffer();
   }

   private static void buffer(){
       System.out.println();
       System.out.println();
   }


   private static double calculateAverageWaitingTime(ArrayList<String[]> data) {
    int totalWaitingTime = 0;
    for (String[] row : data) {
        totalWaitingTime += Integer.parseInt(row[5]); // Waiting time is stored at index 5
    }
    return (double) totalWaitingTime / data.size();
}

private static double calculateCustomersWithWaitTime(ArrayList<String[]> data){
    int customersWithWaitTime = 0;
    for (String[] row : data) {
        if (Integer.parseInt(row[5]) > 0) { // If waiting time is greater than 0, customer had to wait in the queue
            customersWithWaitTime++;
        }
    }
    
    return (double) customersWithWaitTime / data.size();
}

private static double calculateProportionIdleTime(ArrayList<String[]> data){
    int totalIdleTime = 0;
    for (String[] row : data) {
        totalIdleTime += Integer.parseInt(row[8]); // Idle time is stored at index 8
    }
    return (double) totalIdleTime / Integer.parseInt(data.get(data.size()-1)[6]);
}

private static double calculateAverageServiceTime(ArrayList<String[]> data){
    int totalServiceTime = 0;
    for (String[] row : data) {
        totalServiceTime += Integer.parseInt(row[3]); // Service time is stored at index 3
    }
    return (double) totalServiceTime / data.size();
}

private static double calculateAverageTimeBetweenArrivals(ArrayList<String[]> data){
    int totalInterarrivalTime = 0;
    for (int i = 1; i < data.size(); i++) { // Interarrival time starts from the second row
        totalInterarrivalTime += Integer.parseInt(data.get(i)[1]); // Interarrival time is stored at index 1
    }
    return (double) totalInterarrivalTime / (data.size()-1); // Number of interarrivals is one less than the number of customers
}

private static double calculateTotalWaitingTimeWithQueue(ArrayList<String[]> data){
    int totalWaitingTimeWithQueue = 0;
    int customersWithQueueWaitTime = 0;
    for (String[] row : data) {
        if (Integer.parseInt(row[5]) > 0) { // If waiting time is greater than 0, customer had to wait in the queue
            totalWaitingTimeWithQueue += Integer.parseInt(row[5]); // Waiting time is stored at index 5
            customersWithQueueWaitTime++;
    }
}
    return (double) totalWaitingTimeWithQueue / customersWithQueueWaitTime;
}

private static double calculateAverageTimeCustomerSpends(ArrayList<String[]> data){
    int totalTimeCustomerSpends = 0;
    for (String[] row : data) {
        totalTimeCustomerSpends += Integer.parseInt(row[7]); // Service time is stored at index 3
    }
    return (double) totalTimeCustomerSpends / data.size();
}
} // end of class
