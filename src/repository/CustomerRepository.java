/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.Customer;
import utils.Validation;

/**
 *
 * @author DELL
 */
public class CustomerRepository {
    String filePath = "data\\DataCustomer.txt";
    
    public void writeList(List<Customer> list){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            BufferedWriter write = new BufferedWriter(new FileWriter(filePath));
            for(Customer cus : list){
                String line = "ID: "+cus.getPerId() + "| Name: "+ cus.getPerName() + "| Birth: " + dateFormat.format(cus.getPerBirth()) + "| Sex: " + cus.getPerSex() + "| CMND: "+ cus.getPerCMND() + "| Phone: " +cus.getPerPhone() + "| Email: " +cus.getPerEmail() + "| Level: " +cus.getEmpLevel() + "| Address: " + cus.getEmpAddress() +"\n";
                write.write(line);
            }
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<Customer> loadListCusFromFile(){
        List<Customer> listOfCustomers = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File(filePath));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] data = line.split("\\|");
                if (data.length == 9) {
                    String idString = data[0].split(":")[1].trim();
                    boolean checkID = Validation.checkIDCus(idString, listOfCustomers);

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

                    String cusAddress = data[8].split(":")[1].trim();

                    if (checkID && perBirth_bool && perCMND_bool && perPhone_Bool) {
                        String perId = idString;
                        String perCMND = perCMND_String;
                        String perPhone = perPhone_String;
                        Date perBirth = Validation.parse(perBirth_String);

                        Customer customer = new Customer(perId, perName, perBirth, perSex, perCMND, perPhone, perEmail, emplevel, cusAddress);

                        listOfCustomers.add(customer);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return listOfCustomers;
    }
    
}
