package com.linus.jersey.spring.jerseysprstudy.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.linus.jersey.spring.jerseysprstudy.bo.Employee;
import com.linus.jersey.spring.jerseysprstudy.repositories.EmployeeRepository;

@Path("/employees")
public class EmplyeeResource {
	@Autowired
    private EmployeeRepository employeeRepository;
	
	@GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Employee getEmployee(@PathParam("id") Long id) {
        return employeeRepository.getEmployee(id);
    }
}
