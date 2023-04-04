package ibfday21.app.model;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonValue;

public class Customer {
    // (id, company, last_name, first_name, email_address, job_title,
    // business_phone, home_phone, mobile_phone, address, state_province)
    private int id;
    private String company;
    private String lastName;
    private String firstName;
    private String emailAddress;
    private String jobTitle;
    private String businessPhone;
    private String homePhone;
    private String mobilePhone;
    private String address;
    private String stateProvince;
    
    public Customer(){

    }

    public Customer(int id, String company, String lastName, String firstName, String emailAddress, String jobTitle,
            String businessPhone, String homePhone, String mobilePhone, String address, String stateProvince) {
        this.id = id;
        this.company = company;
        this.lastName = lastName;
        this.firstName = firstName;
        this.emailAddress = emailAddress;
        this.jobTitle = jobTitle;
        this.businessPhone = businessPhone;
        this.homePhone = homePhone;
        this.mobilePhone = mobilePhone;
        this.address = address;
        this.stateProvince = stateProvince;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    
    public String getCompany() {return company;}
    public void setCompany(String company) {this.company = company;}
    
    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}

    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getEmailAddress() {return emailAddress;}
    public void setEmailAddress(String emailAddress) {this.emailAddress = emailAddress;}
    
    public String getJobTitle() {return jobTitle;}
    public void setJobTitle(String jobTitle) {this.jobTitle = jobTitle;}
    
    public String getBusinessPhone() {return businessPhone;}
    public void setBusinessPhone(String businessPhone) {this.businessPhone = businessPhone;}
    
    public String getHomePhone() {return homePhone;}
    public void setHomePhone(String homePhone) {this.homePhone = homePhone;}
    
    public String getMobilePhone() {return mobilePhone;}
    public void setMobilePhone(String mobilePhone) {this.mobilePhone = mobilePhone;}
    
    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}
    
    public String getStateProvince() {return stateProvince;}
    public void setStateProvince(String stateProvince) {this.stateProvince = stateProvince;}


    
    @Override
    public String toString() {
        return "Customer [id=" + id + ", company=" + company + ", lastName=" + lastName + ", firstName=" + firstName
                + ", emailAddress=" + emailAddress + ", jobTitle=" + jobTitle + ", businessPhone=" + businessPhone
                + ", homePhone=" + homePhone + ", mobilePhone=" + mobilePhone + ", address=" + address
                + ", stateProvince=" + stateProvince + "]";
    }

    public static Customer create(SqlRowSet rs) {
        Customer customer = new Customer();

        customer.setId(rs.getInt("id"));
        customer.setCompany(rs.getString("company"));
        customer.setLastName(rs.getString("last_name"));
        customer.setFirstName(rs.getString("first_name"));

        return customer;
    }

    public JsonValue toJson() {


        return Json.createObjectBuilder()
                .add("id", getId())
                .add("company", getCompany())
                .add("last_name", getLastName())
                .add("first_name", getFirstName())
                .build();
    }

}
