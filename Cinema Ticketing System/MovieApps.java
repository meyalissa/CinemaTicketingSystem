
/**
 * Application
 */
import java.util.Scanner;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;

public class MovieApps
{
    public static void main (String args[]) 
    {
        //input Scanner
        Scanner inputxt = new Scanner(System.in);
        Scanner inputnumb = new Scanner(System.in);
        Scanner inputchar = new Scanner(System.in);
        //Declare & create
        String staffName="null",staffID,staffPass,formattedTime;
        String name,memberID,phoneNo,email;
        int movieTitle,seatQty,rowNumber=0, columnNumber=0,points,method;
        char member='N';
        int c=0; 
        Customer[] cust = new Customer[10];
        // Define the seating arrangement for each movie
        int[][][] movieSeats = new int[6][8][10];
        // Initialize all seats as available
        for (int r = 0; r < movieSeats.length; r++) {
            for (int j = 0; j < movieSeats[r].length; j++) {
                for (int k = 0; k < movieSeats[r][j].length; k++) {
                    movieSeats[r][j][k] = 0;
                }
            }
        }
        //declare this for system to generate the time that cashier check in or out
        LocalTime clockOutTime, clockInTime;
        //to make sure the time that had display followed the time format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        
        //cashier checkin
        System.out.println("Do you want to clock in? (Y/N)");
        char cashier = inputchar.next().charAt(0);
        while (cashier=='Y' )
        {
            char redeem='N';
            System.out.println("Enter name");
            staffName=inputxt.nextLine();
            System.out.println("Enter your Staff ID");
            staffID=inputxt.nextLine();
            System.out.println("Enter password");
            staffPass=inputxt.nextLine();
            clockInTime=LocalTime.now();
            formattedTime = clockInTime.format(formatter);
            System.out.println("\nHi, " +staffName+ " you have check in on " +formattedTime);
            
            //cust start ordering
            System.out.println("\nNew Order? (Y/N)");
            char NewOrder = inputchar.next().charAt(0);
            while (NewOrder=='Y')
            {
                cust[c] = new Customer();
                //claim name
                System.out.println("Enter Customer Name ");
                name = inputxt.nextLine();
                // Ask the customer to choose movie
                System.out.println("                        MAD CINEMA                              ");                            
                System.out.println("----------------------------------------------------------------");
                System.out.println("|                    Choose your movie                         |");
                System.out.println("----------------------------------------------------------------");
                System.out.println("\n[1] Insidious: The Red Door \t [4] Barbie");
                System.out.println("\n[2] The Super Mario WBros Movie\t [5] Spider-Man : Across The Spider Verse");
                System.out.println("\n[3] The Little Mermaid \t\t [6] Misson Impossible: Dead Reckoning");
                System.out.println("----------------------------------------------------------------");
                movieTitle = inputnumb.nextInt();
                System.out.println("Quantity of Seat");
                seatQty = inputnumb.nextInt();
                cust[c]= new Customer(name,new Ticket(movieTitle,seatQty,rowNumber, columnNumber),member);
                //Seat Selection
                int movieIndex = movieTitle - 1; // Adjust movie number to match array index
                // Display the seating arrangement for the selected movie
                System.out.println("----------------------------------------------------------------");
                for(int q=0;q<seatQty;q++)
                {
                    System.out.println("\nMovie : " + cust[c].getMovie().StringTitle() + " - Seating Arrangement:");
                    System.out.println("\n------SCREEN-------");
                    for (int r = 0; r < movieSeats[movieIndex].length; r++) {
                        for (int j = 0; j < movieSeats[movieIndex][r].length; j++) {
                            System.out.print((movieSeats[movieIndex][r][j] == 0 ? "O" : "X") + " ");
                        }
                        System.out.println();
                    }
                    // Ask the customer to select a seat
                    System.out.print("Enter the row number (1-8): ");
                    rowNumber = inputnumb.nextInt();
                    System.out.print("Enter the column number (1-10): ");
                    columnNumber = inputnumb.nextInt();
                    // Check if the row and column numbers are valid
                    if (rowNumber < 1 || rowNumber > 8 || columnNumber < 1 || columnNumber > 10) {
                        System.out.println("Invalid row or column number. Please try again.");
                        q=q-1;
                        continue;
                    }
                    // Adjust row and column numbers to match array index
                    int rowIndex = rowNumber - 1;
                    int columnIndex = columnNumber - 1;
                    // Check if the selected seat is available
                    if (movieSeats[movieIndex][rowIndex][columnIndex] == 0) {
                        // Reserve the seat
                        movieSeats[movieIndex][rowIndex][columnIndex] = 1;
                        System.out.println("Seat " + rowNumber + "-" + columnNumber + " for Movie " + cust[c].getMovie().StringTitle() + " reserved.");
                    } else {
                        System.out.println("Seat " + rowNumber + "-" + columnNumber + " is already taken. Please select another seat.");
                        q=q-1;
                    }
                }
                System.out.println("----------------------------------------------------------------");
                //Ask the customer to clarify Membership
                System.out.println("\nMembership Validation (Y/N)");
                member= inputchar.next().charAt(0);
                
                double finalPrice =0;
                if (member=='Y') //customer that have a Membership 
                {   
                    System.out.println("Enter Membership ID");
                    memberID = inputxt.nextLine();
                    System.out.println("Enter member points remaining");
                    points = inputnumb.nextInt();
                    cust[c]= new Membership(name,new Ticket(movieTitle, seatQty, rowNumber, columnNumber), member, memberID, points);
                    cust[c].memberValidation();// "This customer have membership"
                    if( cust[c] instanceof Membership)
                    {
                        Membership M = (Membership) cust[c];
                        System.out.println(cust[c].getName() + "\nPoint :" + M.updatePoint());
                        if(M.checkPoint())
                        {
                            System.out.println("This customer can redeem point");
                            System.out.println("Redeem point? (Y/N)");
                            redeem= inputchar.next().charAt(0);
                            finalPrice = M.calcFinalPrice(redeem);
                        }
                        else
                        {
                            finalPrice = M.calcFinalPrice(redeem);
                        }
                    }
                }
                else //customer that does not have a Membership 
                {   
                    cust[c] = new NonMembership();
                    cust[c].memberValidation();
                    System.out.println("Create Membership?(Y/N)");
                    char createMember= inputchar.next().charAt(0);
                    if (createMember=='Y') //if the customer want to create a membership
                    {
                        System.out.println("Enter Phone Number");
                        phoneNo = inputxt.nextLine();
                        System.out.println("Enter active email");
                        email = inputxt.nextLine();
                        cust[c]=new NonMembership(name,new Ticket(movieTitle, seatQty, rowNumber, columnNumber), member, phoneNo, email);
                        if( cust[c] instanceof NonMembership) //
                        {
                            NonMembership NM = (NonMembership) cust[c];
                            NM.createMembership();
                            finalPrice =NM.calcFinalPrice();
                            
                            System.out.println("Confirm Phone Number and Email?");
                            char confirmMember= inputchar.next().charAt(0);
                            if (confirmMember!='Y')
                            {
                                System.out.println("Please rewrite your phone number and your email");
                                System.out.println("Enter Phone Number");
                                phoneNo = inputxt.nextLine();
                                System.out.println("Enter active email");
                                email = inputxt.nextLine();
                                NM.setPhoneNo(phoneNo);
                                NM.setEmail(email);
                            }
                            NM.createMembership();
                        }
                    }
                    else //if the customer does not want to create a membership
                    {
                        cust[c]=new Customer(name,new Ticket(movieTitle, seatQty, rowNumber, columnNumber), member);
                        finalPrice =cust[c].calcTotPrice();
                    }
                } 
                //get total final price
                System.out.println(cust[c].receipt(finalPrice,redeem));
                System.out.println("\nTotal Cost is : RM" + finalPrice);
                System.out.println("--------------------------");
                System.out.println("Choose your Payment Method");
                System.out.println("[1] Cash");
                System.out.println("[2] Card");
                System.out.println("[3] QR");
                System.out.println("--------------------------");
                method = inputnumb.nextInt();
                cust[c].setPayMethod(method);
                System.out.println("Paid by : " + cust[c].StringPayMethod(method));
                // customer buat payment
                System.out.println("\nProceed Payment? (Y/N)");
                char paid= inputchar.next().charAt(0);
                if ( paid == 'Y' )
                {
                    System.out.println("You have paid. Thankyou, Please come again next time");
                    System.out.println("####################################################");
                    
                }   
                else
                {
                    System.out.println("Made up your mind? (Once only)");
                    System.out.println("[1] Change Payment Method");
                    System.out.println("[2] Cancel Order");
                    int update = inputnumb.nextInt();
                    
                    if (update==1) // Customer Want to Change Payment Method
                    {
                        System.out.println("--------------------------");
                        System.out.println("Choose your Payment Method");
                        System.out.println("[1] Cash");
                        System.out.println("[2] Card");
                        System.out.println("[3] QR");
                        System.out.println("--------------------------");
                        method = inputnumb.nextInt();
                        cust[c].setPayMethod(method);
                        System.out.println("Paid by : " + cust[c].StringPayMethod(method));
                        System.out.println("You have paid. Thankyou, Please come again next time");
                        System.out.println("####################################");
                        
                    }
                    else if (update==2) // If Customer Cancel Order
                    {
                        c=c-1;
                    }
                }
                
                //proceed new Customer/Stop Shift
                System.out.println("\nNew Order? (Y/N)");
                NewOrder = inputchar.next().charAt(0);
                c++;
            }
            //checkout
            System.out.println("Clock out Now?(Y/N)");
            char cashierOut=inputxt.next().charAt(0);
            if(cashierOut=='Y')
                cashier='N';
            else if (cashierOut=='N')
                NewOrder='Y'; 
        }
        clockOutTime=LocalTime.now();
        String formattedTime1 = clockOutTime.format(formatter);
        System.out.println("Have a nice day");  
        System.out.println("You have clock out on " +formattedTime1);
        
        //Print onto Output file
        try
        {
            //To print total Sales receipt
            FileWriter fwData=new FileWriter("Sales.txt");
            BufferedWriter bwData=new BufferedWriter(fwData);
            PrintWriter pwData=new PrintWriter(bwData);
            pwData.println("\nShift Sales for " + staffName);
            pwData.println("--------------------------------------");
            for(int i=0;i<c;i++)
            {
                pwData.println("\nCustomer Name: " + cust[i].getName() + 
                                "\nTotal:RM" + cust[i].calcTotPrice());
            }
            //calculate sales
            double totSales =0;
            for(int i=0;i<c;i++)
            {
                totSales= totSales + cust[i].calcTotPrice();
            }
            pwData.println("--------------------------------------");
            pwData.println("\nTotal sales is RM" +totSales);
            //highest sales
            double highest =0;
            for(int i=0;i<c;i++)
            {
                if(cust[i].calcTotPrice() > highest)
                    highest = cust[i].calcTotPrice();
            }
            pwData.println("Highest sales is RM" +highest);
            
            //To print active membership details
            FileWriter fwData2=new FileWriter("membership.txt");
            BufferedWriter bwData2=new BufferedWriter(fwData2);
            PrintWriter pwData2=new PrintWriter(bwData2);
            int countmember=0;
            pwData2.println("\nList Of MAD CINEMA Membership");
            pwData2.println("---------------------------------");
            for(int i=0;i<c;i++)
            {
                if(cust[i] instanceof Membership)
                {
                    Membership M = (Membership) cust[i];
                    pwData2.println("\nName: " +M.getName()+
                                    "\nMembership id: " +M.getMemberID());
                    countmember++;
                }
            }
            pwData2.println("---------------------------------");
            pwData2.println("\nTotal Customer with membership : " + countmember);
            
            pwData.close();
            pwData2.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }
}
    


