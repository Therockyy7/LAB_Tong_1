package service;

import model.Facility;
import model.Villa;
import repository.FacilityRepository;
import repository.Repository;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.TreeSet;
import model.Booking;
import model.Constract;

public class BookingService {

    public TreeSet<Booking> listBooking = new TreeSet<>();
    Queue<Booking> bookingBill = new LinkedList<>();
    ArrayList<Constract> listConstract = new ArrayList<>();
    static LinkedHashMap<Facility, Integer> bill = new LinkedHashMap<>();
//    FacilityRepository fr = new FacilityRepository();
    Map<Booking, Integer> oorder = new HashMap<>();  //luu cac phong trong 1 don
    Scanner in = new Scanner(System.in);
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    CustomerService customerService = new CustomerService();
    FacilityService fc = new FacilityService();

    public BookingService() {
       
    }

    public BookingService(CustomerService customerService, FacilityService fc){
        
    }

    public static LinkedHashMap<Facility, Integer> getMap(){
        return bill;
    }
    

    public void readInf() throws ParseException {
        // Facility r = new Villa("SVVL-0001","Villa 1",600, 2000, 10, "Villa", "luxury", "80", "4");
        // System.out.println(r);
        // bill.put(r, 2);
        // listBooking.add(new Booking("0001", checkdate("20/2/2024"), checkdate("21/2/2024"), checkdate("25/2/2024"), "KH0001", bill));
        // listBooking.add(new Booking("0002", checkdate("19/2/2024"), checkdate("23/2/2024"), checkdate("27/2/2024"), "KH0002", bill));
        // listBooking.add(new Booking("0003", checkdate("21/2/2024"), checkdate("23/2/2024"), checkdate("25/2/2024"), "KH0003", bill));
        // listBooking.add(new Booking("0004", checkdate("20/2/2024"), checkdate("26/2/2024"), checkdate("28/2/2024"), "KH0004", bill));
        // listBooking.add(new Booking("0005", checkdate("20/2/2024"), checkdate("24/2/2024"), checkdate("27/2/2024"), "KH0005", bill));
    }

    public void addBooking() throws ParseException, IOException {
        String bookingId, customerId, serviceId;
        Date checkinDate, checkoutDate, now;
        Facility f;

        // ID
        do {
            bookingId = createBooingId();
        } while (!checkExistBookingId(bookingId));

        //Curent date
        now = new Date();
        System.out.println(now);
        //check in-out
        do {
            System.out.println("Input Checkin Date: ");
            checkinDate = getDateFromInput();
            if (now.after(checkinDate)) {
                System.out.println("Please input checkin date after today!");
            }
        } while (checkinDate.before(now));

        do {
            System.out.println("Input Checkout Date: ");
            checkoutDate = getDateFromInput();
            if (checkoutDate.before(checkinDate)) {
                System.out.println("Please input checkout date after checkin");
            }
        } while (checkoutDate.before(checkinDate));

        //------display customer id ...... code phuc
        customerService.displayList();

        System.out.println("Input customer Id: ");
        customerId = in.nextLine();
        do {

            //------display facility id......
            fc.displayFacility();//code quang
            System.out.println("Input service Id: ");

            serviceId = in.nextLine();
            f = fc.searchFacility(serviceId); // return room or vila or house
            System.out.println(f);
            System.out.println("Input quantity: "); // check quan ddur k
            int quan = in.nextInt();
            bill.put(f, quan);
            in.nextLine();

        } while (confirm("continute booking"));
        Booking b = new Booking(bookingId, now, checkinDate, checkoutDate, customerId, bill);
        listBooking.add(b);
        //System.out.println(b);
        System.out.println("Booking complete successful!");
        System.out.println("Your Booking ID is: " + bookingId);
        //bill.clear();
        //------display room inf
    }
//------------------------------------------------------------------------------ 

    int caculateTotal(String bookingId) {
        int total = 0;
        Booking b = searchBooking(bookingId);
        Map<Facility, Integer> bill = b.getBill();
        for (Map.Entry<Facility, Integer> entry : bill.entrySet()) {
            Facility f = entry.getKey();
            Integer value = entry.getValue();

            total += f.getPrice() * value;
        }
        return total;
    }

//------------------------------------------------------------------------------   
    public void displayListBooking() throws ParseException  {
        readInf();
        
        System.out.println("Booking List: ");
        System.out.println("+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+");
        System.out.printf("| %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |\n", "Booking ID", "Booking Date", "Check-in Date", "Check-out Date", "Customer ID", "Service ID", "Quantity");

        for (Booking b : listBooking) {
            Map<Facility, Integer> bill = b.getBill();
            for (Map.Entry<Facility, Integer> entry : bill.entrySet()) {
                Facility f = entry.getKey();
                Integer value = entry.getValue();
                System.out.println("+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+");
                System.out.printf("| %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |\n", b.getBookingId(), dateFormat.format(b.getBookingDate()), dateFormat.format(b.getStart()), dateFormat.format(b.getFinish()), b.getCusId(), f.getName(), value);
                
            }
            System.out.println("+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+");

            System.out.println("|TOTAL: " + caculateTotal(b.getBookingId()) + "\n");
        }

    }
//------------------------------------------------------------------------------ 

    public void displayBooking(String bookingId) {
        Booking b = searchBooking(bookingId);
        Map<Facility, Integer> bill = b.getBill();
        for (Map.Entry<Facility, Integer> entry : bill.entrySet()) {
            Facility f = entry.getKey();
            Integer value = entry.getValue();
            System.out.println("+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+");
            System.out.printf("| %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |\n", b.getBookingId(), dateFormat.format(b.getBookingDate()), dateFormat.format(b.getStart()), dateFormat.format(b.getFinish()), b.getCusId(), f.getName(), value);
            
        }
        System.out.println("+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+");


    }
//------------------------------------------------------------------------------ 

    public void addConstract() {
        String constractId, bookingId, deposit, total;
        do {
            System.out.println("Input Booking Id :");
            bookingId = in.nextLine();
        } while (!checkExistBookingId(bookingId));
        constractId=createConstractId();
        displayBooking(bookingId);
        System.out.println("Your Deposit amount is 50% : " + caculateTotal(bookingId) * 0.5);
        System.out.println("Your Total amount is : " + caculateTotal(bookingId));
        Constract c = new Constract(constractId, bookingId, caculateTotal(bookingId) * 50 / 100, caculateTotal(bookingId));
        if(confirm("create constract")){
            listConstract.add(c); 
            System.out.println("Constract have been created !");
        }
       
        else
            System.out.println("Constract have not been created !");
    }

//------------------------------------------------------------------------------ 
    public String createBooingId() {
        int max = 0;
        for (Booking b : listBooking) {
            if (Integer.parseInt(b.getBookingId()) > max) {
                max = Integer.parseInt(b.getBookingId());
            }
        }
        return String.valueOf(max + 1);
    }
//------------------------------------------------------------------------------ 
    public String createConstractId() {
        int max = 0;
        for (Constract c : listConstract) {
            if (Integer.parseInt(c.getConstractId()) > max) {
                max = Integer.parseInt(c.getConstractId());
            }
        }
        return String.valueOf(max + 1);
    }
//------------------------------------------------------------------------------  

    public Boolean checkExistBookingId(String id) {
        for (Booking b : listBooking) {
            if (b.getBookingId().equals(b)) {
                return false;
            }
        }
        return true;
    }
//------------------------------------------------------------------------------  

    public Booking searchBooking(String bookingId) {
        for (Booking b : listBooking) {
            if (b.getBookingId().equalsIgnoreCase(bookingId)) {
                return b;
            }
        }
        return null;
    }
//------------------------------------------------------------------------------    
// check in here

    public Date getDateFromInput() throws ParseException {
        while (true) {
            String dateString = in.nextLine();
            if (!dateString.isEmpty()) {
                try {
                    Date date = dateFormat.parse(dateString);
                    return date;
                } catch (ParseException e) {
                    System.out.print("Invalid date format. Please enter again (dd/MM/yyyy):");
                }
            } else {
                System.out.print("Date cannot be empty. Please enter again (dd/MM/yyyy):");
            }
        }
    }

    public Date checkdate(String dateString) throws ParseException {
        while (true) {

            if (!dateString.isEmpty()) {
                try {
                    Date date = dateFormat.parse(dateString);
                    return date;
                } catch (ParseException e) {
                    System.out.print("Invalid date format. Please enter again (dd/MM/yyyy):");
                }
            } else {
                System.out.print("Date cannot be empty. Please enter again (dd/MM/yyyy):");
            }
        }
    }

    public static Boolean confirm(String msg) {
        System.out.println("Do you want to " + msg + " Y/N ?");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        input.toUpperCase();
        if (input.equalsIgnoreCase("Y")) {
            return true;
        }
        return false;
    }

    public void displayListConstract() {
        System.out.println("Constract List: ");
        System.out.println("+-----------------+-----------------+-----------------+-----------------+");
        System.out.printf("| %-15s | %-15s | %-15s | %-15s |\n", "Constract ID", "Booking ID", "Deposit amount", "Total amount");

        for (Constract c : listConstract) {
            System.out.println("+-----------------+-----------------+-----------------+-----------------+");
            System.out.printf("| %-15s | %-15s | %-15s | %-15s |\n", c.getConstractId(), c.getBookingId(), c.getDeposit(), c.getTotal());
        }
        System.out.println("+-----------------+-----------------+-----------------+-----------------+");     
}
}
