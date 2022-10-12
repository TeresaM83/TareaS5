package idat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import idat.model.Alumno;
import idat.service.AlumnoService;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {
	
	Alumno obj;
	
	@Autowired
	private AlumnoService service;
	
	@RequestMapping(method = RequestMethod.GET, path = "/")
	public ResponseEntity<List<Alumno>> listar() {
		List<Alumno> lista=service.listar();		
		return new ResponseEntity<List<Alumno>>(lista,HttpStatus.OK);
	}
	
	
	@RequestMapping(method = RequestMethod.GET, path = "/{codigo}")
	public ResponseEntity<Alumno> buscar(@PathVariable Integer codigo) {	
		obj = service.buscar(codigo);
		if (obj!=null) {
			return new ResponseEntity<Alumno>(obj,HttpStatus.OK);
		}else {
			return new ResponseEntity<Alumno>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/")
	public ResponseEntity<Alumno> create(@RequestBody Alumno p) {
		service.save(p);	
		return new ResponseEntity<>(p,HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "/{codigo}")
	public ResponseEntity<?> actualizar(@RequestBody Alumno p, @PathVariable Integer codigo) {
		obj= service.buscar(codigo);
		if (obj!=null) {
			p.setIdAlumno(codigo);
			service.actualizar(p);
			return new ResponseEntity<>(p,HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
			
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path = "/{codigo}")
	public ResponseEntity<?> eliminar(@PathVariable Integer codigo) {
		obj= service.buscar(codigo);
		if (obj!=null) {
			service.eliminar(codigo);	
			return new ResponseEntity<>(obj,HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
}
