package com.example.SampathBank.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class EmployeeDTO {
    private Long empID;
    private String firstName;
    private String lastName;
    private String email;
    private double salary;

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "empID=" + empID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", salary=" + salary +
                '}';
    }

    public Long getEmpID() {
        return empID;
    }

    public void setEmpID(Long empID) {
        this.empID = empID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
