package idat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.model.Alumno;
import idat.repository.AlumnoRepository;

@Service
public class AlumnoService {
	
	@Autowired
	private AlumnoRepository repository;
	
	public void save(Alumno p) {
		repository.save(p);
	}
	
	public void actualizar(Alumno p) {
		repository.saveAndFlush(p);
	}
	
	public void eliminar(Integer codigo) {
		repository.deleteById(codigo);
	}

	public Alumno buscar(Integer codigo) {
		return repository.findById(codigo).orElse(null);
	}

	public List<Alumno> listar() {
		return repository.findAll();
	}
}
