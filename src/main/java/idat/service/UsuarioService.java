package idat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idat.model.Usuario;
import idat.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	public void save(Usuario p) {
		repository.save(p);
	}
	
	public void actualizar(Usuario p) {
		repository.saveAndFlush(p);
	}
	
	public void eliminar(Integer codigo) {
		repository.deleteById(codigo);
	}

	public Usuario buscar(Integer codigo) {
		return repository.findById(codigo).orElse(null);
	}

	public List<Usuario> listar() {
		return repository.findAll();
	}
}
