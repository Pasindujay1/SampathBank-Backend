package com.example.SampathBank.controller;

import com.example.SampathBank.dto.EmployeeDTO;
import com.example.SampathBank.dto.ResponseDTO;
import com.example.SampathBank.service.EmployeeService;
import com.example.SampathBank.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping
    public ResponseEntity saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        try{
            String res = employeeService.SaveEmployee(employeeDTO);

            if(res.equals("00")){
                responseDTO.setCode(VarList.RSP_Success);
                responseDTO.setMessage("Success");
                responseDTO.setContent(employeeDTO);

                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            }else if(res.equals("06")){
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Employee already exists");
                responseDTO.setContent(employeeDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }else{
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);

            }
        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{empID}")
    public ResponseEntity updateEmployee(@PathVariable Long empID, @RequestBody EmployeeDTO employeeDTO) {
        try{
            String res = employeeService.updateEmployee(empID, employeeDTO);
            if(res.equals("00")){
                responseDTO.setCode(VarList.RSP_Success);
                responseDTO.setMessage("Success");
                responseDTO.setContent(employeeDTO);

                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            }else if(res.equals("01")){
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Not a Registered Employee");
                responseDTO.setContent(employeeDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }else{
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }

        }catch(Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity getAllEmployees(){
        try{
            List<EmployeeDTO> employeeDTOList = employeeService.getAllEmployees();

            responseDTO.setCode(VarList.RSP_Success);
            responseDTO.setMessage("Success");
            responseDTO.setContent(employeeDTOList);
            return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
        }catch(Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @GetMapping("/{id}")
    public ResponseEntity getEmployee(@PathVariable Long id){
        EmployeeDTO employeeDTO =employeeService.getEmployeeById(id);
        try{
            if(employeeDTO != null){
                responseDTO.setCode(VarList.RSP_Success);
                responseDTO.setMessage("Success");
                responseDTO.setContent(employeeDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            }else{
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Not a Registered Employee");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }

        }catch(Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmployee(@PathVariable Long id){
        String res = employeeService.deleteEmployee(id);
        try{
            if(res.equals("00")){
                responseDTO.setCode(VarList.RSP_Success);
                responseDTO.setMessage("Success");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            }else{
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Not Employee Exists for the provided ID");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }

        }catch(Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
