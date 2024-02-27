package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Customer extends Person{
    String cusLevel;
    String cusAddress;

    public Customer(String perId, String cusLevel, String cusAddress) {
        super(perId);
        this.cusLevel = cusLevel;
        this.cusAddress = cusAddress;
    }

    public Customer(String perId, String perName, Date perBirth, String perSex, String perCMND, String perPhone,
            String perEmail, String cusLevel, String cusAddress) {
        super(perId, perName, perBirth, perSex, perCMND, perPhone, perEmail);
        this.cusLevel = cusLevel;
        this.cusAddress = cusAddress;
    }


    public String getEmpLevel() {
        return cusLevel;
    }
    public void setEmpLevel(String empLevel) {
        this.cusLevel = empLevel;
    }
    public String getEmpAddress() {
        return cusAddress;
    }
    public void setEmpAddress(String empAddress) {
        this.cusAddress = empAddress;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        // return "Employee ["+"perID ="+perId+",perName = "+perName+",perBirth= "+dateFormat.format(perBirth)+",perSex= "+perSex+",perCMND = "+perCMND+",perPhone= "+perPhone+",perEmail= "+perEmail+",cusLevel=" + cusLevel + ", cusAddress=" + cusAddress + "]";
        return (String.format("| %-12s  | %-30s  | %-20s  | %-12s  | %-20s  | %-12s  | %-25s  | %-12s  | %-30s|", getPerId(),getPerName(),dateFormat.format(getPerBirth()),getPerSex(),getPerCMND(),getPerPhone(),getPerEmail(),getEmpLevel(),getEmpAddress()));
    }
  

    
}
