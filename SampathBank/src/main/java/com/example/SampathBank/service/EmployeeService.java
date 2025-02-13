package com.example.SampathBank.service;

import com.example.SampathBank.dto.EmployeeDTO;
import com.example.SampathBank.entity.Employee;
import com.example.SampathBank.repo.EmployeeRepo;
import com.example.SampathBank.util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private  EmployeeRepo employeeRepo;

    @Autowired
    private ModelMapper modelMapper;

    public String SaveEmployee(EmployeeDTO employeeDTO) {
        if(employeeRepo.existsById(employeeDTO.getEmpID())){
            return VarList.RSP_DUPLICATED;
        }else{
            Employee employee = new Employee();
            employee.setFirstName(employeeDTO.getFirstName());
            employee.setLastName(employeeDTO.getLastName());
            employee.setEmail(employeeDTO.getEmail());
            employee.setSalary(employeeDTO.getSalary());



            Employee savedEmployee = employeeRepo.save(employee);

            employeeDTO.setEmpID(savedEmployee.getEmpID());

            employeeRepo.save(employee);
            return VarList.RSP_Success;
        }
    }

    public String updateEmployee(Long empId,EmployeeDTO employeeDTO) {
        if(employeeRepo.existsById(empId)){
            employeeRepo.save(modelMapper.map(employeeDTO, Employee.class));
            return VarList.RSP_Success;
        }else{
            return VarList.RSP_DUPLICATED;
        }
    }

    public List<EmployeeDTO> getAllEmployees(){
       List<Employee> employeeList = employeeRepo.findAll();
       return modelMapper.map(employeeList, new TypeToken<List<Employee>>(){}.getType());
    }

    public EmployeeDTO getEmployeeById(Long empId) {
        if(employeeRepo.existsById(empId)){
            return modelMapper.map(employeeRepo.findById(empId).orElse(null), EmployeeDTO.class);
        }else{
            return null;
        }
    }

    public String deleteEmployee(Long empId) {
        if(employeeRepo.existsById(empId)){
            employeeRepo.deleteById(empId);
            return VarList.RSP_Success;
        }else{
            return VarList.RSP_NO_DATA_FOUND;
        }
    }


}
