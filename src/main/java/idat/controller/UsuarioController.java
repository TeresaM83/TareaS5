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

import idat.model.Usuario;
import idat.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	Usuario obj;
	
	@Autowired
	private UsuarioService service;
	
	@RequestMapping(method = RequestMethod.GET, path = "/")
	public ResponseEntity<List<Usuario>> listar() {
		List<Usuario> lista=service.listar();		
		return new ResponseEntity<List<Usuario>>(lista,HttpStatus.OK);
	}
	
	
	@RequestMapping(method = RequestMethod.GET, path = "/{codigo}")
	public ResponseEntity<Usuario> buscar(@PathVariable Integer codigo) {	
		obj = service.buscar(codigo);
		if (obj!=null) {
			return new ResponseEntity<Usuario>(obj,HttpStatus.OK);
		}else {
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/")
	public ResponseEntity<Usuario> create(@RequestBody Usuario p) {
		service.save(p);	
		return new ResponseEntity<>(p,HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "/{codigo}")
	public ResponseEntity<?> actualizar(@RequestBody Usuario p, @PathVariable Integer codigo) {
		obj= service.buscar(codigo);
		if (obj!=null) {
			p.setIdUsuario(codigo);
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
