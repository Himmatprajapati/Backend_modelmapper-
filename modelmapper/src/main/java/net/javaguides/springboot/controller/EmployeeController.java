package net.javaguides.springboot.controller;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import jakarta.validation.Valid;
import net.javaguides.springboot.dto.EmployeeDto;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	//get employees
	@GetMapping(value = {"","/"})
	public List<Employee> getAllEmployees(){

		return this. employeeRepository.findAll();
	}
	
	//get employee by id
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id")Long employeeId)
			throws ResourceNotFoundException{
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(()-> new ResourceNotFoundException("Employee not found for this id :: "+ employeeId));
			return ResponseEntity.ok().body(employee);
	}
	
	//save employee
	 @PostMapping("/")
	    public Employee createEmployee(@Valid  @RequestBody Employee employee) {
	        return employeeRepository.save(employee);
	    }
	 
	 //update employee
	    @PutMapping("/{id}")
	    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
	         @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
	        Employee employee = employeeRepository.findById(employeeId)
	        .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

	        employee.setEmail(employeeDetails.getEmail());
	        employee.setLastName(employeeDetails.getLastName());
	        employee.setFirstName(employeeDetails.getFirstName());
	        final Employee updatedEmployee = employeeRepository.save(employee);
	        return ResponseEntity.ok(updatedEmployee);
	    }
	
	//delete employee
	    @DeleteMapping("/{id}")
	    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id")Long employeeId) throws ResourceNotFoundException {
	    	Employee employee = employeeRepository.findById(employeeId)
					.orElseThrow(()-> new ResourceNotFoundException("Employee not found for this id :: "+ employeeId));
	    	this.employeeRepository.delete(employee);
	    	
	    	Map<String, Boolean> response = new HashMap<>();
	    	response.put("deleted",Boolean.TRUE);
	    	 
	    	return response;
	    	
	    }
	    
	    
	    
	    
	    @Autowired
	    private ModelMapper modelMapper;

	    
	    @GetMapping("/get/{id}")
		public ResponseEntity<EmployeeDto> getAllEmployees(@PathVariable("id")long id){
	    	Employee employee = employeeRepository.findById(id).get();
	    	EmployeeDto map = modelMapper.map(employee, EmployeeDto.class);
			return ResponseEntity.ok(map);
		}
}
