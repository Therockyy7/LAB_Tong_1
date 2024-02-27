package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import utils.Validation;
import model.Customer;
import model.Employee;
import repository.EmployeeRepository;

public class EmployeeService  {
    EmployeeRepository employeeRepository = new EmployeeRepository();
    List<Employee> listEmp;

    public EmployeeService(){
        listEmp = new ArrayList<>();
    }

    public boolean checkExistID(String id, List<Employee> empList){
        for(Employee emp : empList){
            if(emp.getPerId().equals(id)){
                return true;
            }
        }
        return false;
    }


    public void displayList(){
      if(!listEmp.isEmpty()){

            System.out.println("|+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+|");
            System.out.println("|                                                                                             List of Employee                                                                                                           |");
            System.out.println("|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");

            System.out.println(String.format("| %-12s |  %-30s | %-20s   | %-12s   | %-20s   | %-12s  | %-25s  | %-12s  | %-20s  | %-12s |", "Customer code", "Customer name", "Date of Birth", "Sex", "CMND", "Phone", "Email", "Level", "Position", "Salary"));
            System.out.println("|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
            System.out.println("|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");

            for(Employee cus : listEmp){
                System.out.println(cus.toString());
            }
            System.out.println("|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");


        }else{
            System.out.println("List is Empty!");
        }
    }

    public void addEmp(){
        String ans;
        int count = listEmp.size();
        do{

            
            String empId = null;
            System.out.println("\nAdd new Employee");
            
            for(Employee emp : listEmp){
                if(emp.getPerId().contains("NV")){
                    count++;
                    empId = "NV-" + String.format("%04d", ++count);
                    if (!empId.equals(emp.getPerId())) {
                        break;
                    } 
                }
            } 
            // empId = "NV-" + String.format("%04d", ++count);
            
            System.out.println("Employee ID: "+ empId);

            String perName = Validation.getPerNameFromInput("Name");

            Date perBirth = Validation.getBirthFromInput("Birth <dd/MM/yyyy>");

            String perSex = Validation.getStringFromInput("Sex");

            String perCMND = Validation.getPerCMNDFromInput("CMND");

            String perPhone = Validation.getPerPhoneFromInput("Phone number");

            String perEmail = Validation.getStringFromInput("Email");

            String empLevel = Validation.getStringFromInput("Level");

            String empPosition = Validation.getStringFromInput("Position");
            
            int empSalary = Validation.getIntFromInput("salary");

            Employee emp = new Employee(empId, perName, perBirth, perSex, perCMND, perPhone, perEmail, empLevel, empPosition, empSalary);

            listEmp.add(emp);

            System.out.println("Do you want to continue ?[Y/N]");
            ans = Validation.getChoiceYesNoNFromInput("Answer");
        }while(ans.equalsIgnoreCase("y"));
       
    }

    public void editEmp(){
        try {
        boolean check = false;
        String perId;
        System.out.println("                          \t~~~~~~~~~~~~~~~~~~~~~~~~ EDIT Employee~~~~~~~~~~~~~~~~~~~~~~~~");
        do{

            perId = Validation.getEmpIDFromInput("ID <NV-YYYY> (Y is number)");
            if (checkExistID(perId, listEmp)) {
                System.out.println("~~~~~~~~~What type do you want to change?");
                System.out.println(
                    "1: Name\n"+
                    "2: Date of birth\n"+
                    "3: Sex\n"+
                    "4: CMND\n"+
                    "5: Phone number\n"+
                    "6: Email\n"+
                    "7: Level\n"+
                    "8: Position\n"+
                    "9: Salary"

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
                for(Employee emp : listEmp){
                    if (emp.getPerId().equals(perId)) {
                        emp.setPerName(name);
                    }
                }
                break;
            case 2: 
                 System.out.println("~~~~~~~~~What date of birth you want to change?");
                
                    Date date = Validation.getBirthFromInput("Birth <dd/MM/yyyy>");
                    for(Employee emp : listEmp){
                        if (emp.getPerId().equals(perId)) {
                            emp.setPerBirth(date);
                        }
                    }
                
                break;
            case 3:
                System.out.println("~~~~~~~~~What SEX you want to change?");
                String sex = Validation.getStringFromInput("Sex");
                for(Employee emp : listEmp){
                    if (emp.getPerId().equals(perId)) {
                        emp.setPerSex(sex);
                    }
                }
                break;
            case 4:
                 System.out.println("~~~~~~~~~What CMND you want to change?");
                String CMND = Validation.getPerCMNDFromInput("CMND");
                for(Employee emp : listEmp){
                    if (emp.getPerId().equals(perId)) {
                        emp.setPerCMND(CMND);
                    }
                }
                break;
            case 5:
                System.out.println("~~~~~~~~~What Phone number you want to change?");
                String phoneNumber = Validation.getPerPhoneFromInput("Phone number");
                for(Employee emp : listEmp){
                    if (emp.getPerId().equals(perId)) {
                        emp.setPerPhone(phoneNumber);
                    }
                }
                break;
            case 6:
                 System.out.println("~~~~~~~~~What Email you want to change?");
                String email = Validation.getStringFromInput("Email");
                for(Employee emp : listEmp){
                    if (emp.getPerId().equals(perId)) {
                        emp.setPerEmail(email);
                    }
                }
                break;
            case 7:
                System.out.println("~~~~~~~~~What Level you want to change?");
                String level = Validation.getStringFromInput("Level");
                for(Employee emp : listEmp){
                    if (emp.getPerId().equals(perId)) {
                        emp.setEmpLevel(level);
                    }
                }
                break;
            case 8:
                System.out.println("~~~~~~~~~What Position you want to change?");
                String position = Validation.getStringFromInput("Position");
                for(Employee emp : listEmp){
                    if (emp.getPerId().equals(perId)) {
                        emp.setEmpPosition(position);
                    }
                }
                break;
            case 9:
                System.out.println("~~~~~~~~~What Salary you want to change?");
                int salary = Validation.getIntFromInput("salary");
                for(Employee emp : listEmp){
                    if (emp.getPerId().equals(perId)) {
                        emp.setEmpSalary(salary);
                    }
                }
                break;

            default:
                System.out.println("!!!!!!!!!!!!!!!!None of choice please try again!!!!!!!!!!!!!!!!");
                break;
        }

        } catch (NumberFormatException e) {
            System.out.println("!!!!!!!!!!!!!!!!Wrong Format input!!!!!!!!!!!!!!!!");
        } 
    }

    // public List<Employee> listFromFile(){
    //     List<Employee> listFromFile = employeeRepository.loadListEmpFromFile();
    //     List<Employee> listFromFileFinal = new ArrayList<>();
    //     for(Employee std : listFromFile){
    //         if (!checkExistID(std.getPerId(), listFromFileFinal)) {
    //             listFromFileFinal.add(std);
    //         }
    //     }
    //     return listFromFileFinal;
    // }

    public void getListFromFile(){
        try {
            listEmp = employeeRepository.loadListEmpFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        displayList();
    }

    public void saveFile(){
        if (!listEmp.isEmpty()) {
            employeeRepository.writeList(listEmp);
            System.out.println("!!!!!!!!Save complete!!!!!!!!");
        }else{
            System.out.println("Not have any information in List.");
        }
    }
}
