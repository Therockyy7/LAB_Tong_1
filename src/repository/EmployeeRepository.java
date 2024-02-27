/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.Customer;
import model.Employee;
import utils.Validation;

/**
 *
 * @author DELL
 */
public class EmployeeRepository {
    String filePath = "src\\data\\DataEmployee.txt";

     public void writeList(List<Employee> list){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            BufferedWriter write = new BufferedWriter(new FileWriter(filePath));
            for(Employee emp : list){
                String line = "ID: "+emp.getPerId() + "| Name: "+ emp.getPerName() + "| Birth: " + dateFormat.format(emp.getPerBirth()) + "| Sex: " + emp.getPerSex() + "| CMND: "+ emp.getPerCMND() + "| Phone: " +emp.getPerPhone() + "| Email: " +emp.getPerEmail() + "| Level: " +emp.getEmpLevel() + "| Position: " + emp.getEmpPosition() + "| Salary: " + emp.getEmpSalary() +"\n";
                write.write(line);
            }
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Employee> loadListEmpFromFile() throws  IOException{
        List<Employee> listOfEmployees = new ArrayList<>();
        
            // Scanner sc = new Scanner(new FileReader(filePath));
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));

            String line;
                
                    while ((line = bufferedReader.readLine()) != null) {
                        String[] data = line.split("\\|");
                        if (data.length == 10) {
                            String idString = data[0].split(":")[1].trim();
                            boolean checkID = Validation.checkIDEmp(idString, listOfEmployees);

                            String perName = data[1].split(":")[1].trim();

                            String perBirth_String = data[2].split(":")[1].trim();
                            boolean perBirth_bool = Validation.isValidDate(perBirth_String, "dd/MM/yyyy");

                            String perSex = data[3].split(":")[1].trim();

                            String perCMND_String = data[4].split(":")[1].trim();
                            boolean perCMND_bool = Validation.isValidCMND(perCMND_String);

                            String perPhone_String = data[5].split(":")[1].trim();
                            boolean perPhone_Bool = Validation.isValidPhone(perPhone_String);

                            String perEmail = data[6].split(":")[1].trim();

                            String emplevel = data[7].split(":")[1].trim();

                            String empPosition = data[8].split(":")[1].trim();

                            String  empSalary_String = data[9].split(":")[1].trim();

                            if (checkID && perBirth_bool && perCMND_bool && perPhone_Bool) {
                                String perId = idString;
                                String perCMND = perCMND_String;
                                String perPhone = perPhone_String;
                                int empSalary = Integer.parseInt(empSalary_String);
                                Date perBirth = Validation.parse(perBirth_String);
                                Employee employee  = new Employee(perId, perName, perBirth, perSex, perCMND, perPhone, perEmail, emplevel, empPosition, empSalary);

                                listOfEmployees.add(employee);
                            }
                        }
                    }
                    bufferedReader.close();
               
           
       
        
        return listOfEmployees;
    }
}
