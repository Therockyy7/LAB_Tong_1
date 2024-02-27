package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import utils.Validation;
import model.Customer;
import model.Employee;
import repository.CustomerRepository;

public class CustomerService {
    CustomerRepository customerRepository = new CustomerRepository();
    List<Customer> listCus;
    public CustomerService(){
        listCus = new ArrayList<>();
    } 

    public boolean checkExistID(String id, List<Customer> cusList){
        for(Customer cus : cusList){
            if(cus.getPerId().equals(id)){
                return true;
            }
        }
        return false;
    }

    public void displayList(){

        if(!listCus.isEmpty()){

            System.out.println("+--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+");
            System.out.println("|                                                                                                            List of Customer                                                                                  |");
            System.out.println("|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
            System.out.println(String.format("| %-12s | %-30s  | %-20s | %-12s  | %-20s  | %-12s  | %-25s  | %-12s  | %-30s |", "Customer code", "Customer name", "Date of Birth", "Sex", "CMND", "Phone", "Email", "Level", "Address"));
            System.out.println("|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
            System.out.println("|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");

            for(Customer cus : listCus){
                System.out.println(cus.toString());
            }
            System.out.println("|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");


        }else{
            System.out.println("List is Empty!");
        }
    }

    public void addCus(){
        String ans;
        int count = listCus.size();
        do{

            
            String cusId = null;
            System.out.println("\nAdd new Customer");     

            for(Customer cus : listCus){
                if (cus.getPerId().contains("KH")) {
                    count++;
                    cusId = "KH-" + String.format("%04d", ++count);
                    if (!cusId.equals(cus.getPerId())) {
                        break;
                    }
                }
            }
            // cusId = "KH-" + String.format("%04d", ++count);

            System.out.println("Customer ID: "+ cusId);

            String perName = Validation.getPerNameFromInput("Name");
        
            Date perBirth = Validation.getBirthFromInput("Birth <dd/MM/yyyy>");

            String perSex = Validation.getStringFromInput("Sex");

            String perCMND = Validation.getPerCMNDFromInput("CMND");

            String perPhone = Validation.getPerPhoneFromInput("Phone number");

            String perEmail = Validation.getStringFromInput("Email");

            String cusLevel = Validation.getStringFromInput("level");

            String cusAddress = Validation.getStringFromInput("Address");

            Customer cus = new Customer(cusId, perName, perBirth, perSex, perCMND, perPhone, perEmail, cusLevel, cusAddress);
            listCus.add(cus);

            System.out.println("Do you want to continue ?[Y/N]");
            ans = Validation.getChoiceYesNoNFromInput("Answer");
        }while(ans.equalsIgnoreCase("y"));
      
    }

    public void editCus(){
        
        try{

       
            boolean check = false;
            String perId;
            System.out.println("                          \t~~~~~~~~~~~~~~~~~~~~~~~~ EDIT Employee~~~~~~~~~~~~~~~~~~~~~~~~");
            do{

                perId = Validation.getStringFromInput("ID <KH-YYYY> (Y is number)");
                if (checkExistID(perId, listCus)) {
                    System.out.println("~~~~~~~~~What type do you want to change?");
                    System.out.println(
                        "1: Name\n"+
                        "2: Date of birth\n"+
                        "3: Sex\n"+
                        "4: CMND\n"+
                        "5: Phone number\n"+
                        "6: Email\n"+
                        "7: Level\n"+
                        "8: Address"
                    );
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    check = true;
                }else{
                    System.out.println("ID has not Exist!!");
                    check = false;
                }
            }while(!check);

            int choice = Validation.getIntFromInput("Choice");
            
            switch (choice) {
                    case 1:
                        System.out.println("~~~~~~~~~What name you want to change?");
                        String name = Validation.getPerNameFromInput("Name");
                        for(Customer cus : listCus){
                            if (cus.getPerId().equals(perId)) {
                                cus.setPerName(name);
                            }
                        }
                        break;
                    case 2: 
                        System.out.println("~~~~~~~~~What date of birth you want to change?");
                        
                            Date date = Validation.getBirthFromInput("Birth <dd/MM/yyyy>");
                            for(Customer cus : listCus){
                                if (cus.getPerId().equals(perId)) {
                                    cus.setPerBirth(date);
                                }
                            }
                        
                        break;
                    case 3:
                        System.out.println("~~~~~~~~~What SEX you want to change?");
                        String sex = Validation.getStringFromInput("Sex");
                        for(Customer cus : listCus){
                            if (cus.getPerId().equals(perId)) {
                                cus.setPerSex(sex);
                            }
                        }
                        break;
                    case 4:
                        System.out.println("~~~~~~~~~What CMND you want to change?");
                        String CMND = Validation.getPerCMNDFromInput("CMND");
                        for(Customer cus : listCus){
                            if (cus.getPerId().equals(perId)) {
                                cus.setPerCMND(CMND);
                            }
                        }
                        break;
                    case 5:
                        System.out.println("~~~~~~~~~What Phone number you want to change?");
                        String phoneNumber = Validation.getPerPhoneFromInput("Phone number");
                        for(Customer  cus : listCus){
                            if ( cus.getPerId().equals(perId)) {
                                 cus.setPerPhone(phoneNumber);
                            }
                        }
                        break;
                    case 6:
                        System.out.println("~~~~~~~~~What Email you want to change?");
                        String email = Validation.getStringFromInput("Email");
                        for(Customer  cus : listCus){
                            if ( cus.getPerId().equals(perId)) {
                                 cus.setPerEmail(email);
                            }
                        }
                        break;
                    case 7:
                        System.out.println("~~~~~~~~~What Level you want to change?");
                        String level = Validation.getStringFromInput("Level");
                        for(Customer  cus : listCus){
                            if ( cus.getPerId().equals(perId)) {
                                 cus.setEmpLevel(level);
                            }
                        }
                        break;
                    case 8:
                        System.out.println("~~~~~~~~~What Address you want to change?");
                        String address = Validation.getStringFromInput("Address");
                        for(Customer cus : listCus){
                            if ( cus.getPerId().equals(perId)) {
                                 cus.setEmpAddress(address);
                            }
                        }
                        break;
        
                    default:
                        System.out.println("!!!!!!!!!!!!!!!!None of choice please try again!!!!!!!!!!!!!!!!");
                    break;
            }

        }catch (NumberFormatException e) {
            System.out.println("!!!!!!!!!!!!!!!!Wrong Format input!!!!!!!!!!!!!!!!");
        }

    }

    public void getListFromFile(){
        listCus = customerRepository.loadListCusFromFile();
        displayList();
    }

    public void saveFile(){
        if (!listCus.isEmpty()) {
            customerRepository.writeList(listCus);
            System.out.println("!!!!!!!!Save complete!!!!!!!!");
        }else{
            System.out.println("Not have any information in List.");
        }
    }
}
