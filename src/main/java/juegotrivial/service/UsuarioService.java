package juegotrivial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import juegotrivial.model.Roles;
import juegotrivial.model.Usuario;
import juegotrivial.repository.IUsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private IUsuarioRepository usuarioRepo;

	public Usuario findByEmailService(String loginUsuario, String loginPassword) {
		Usuario u = usuarioRepo.findByEmail(loginUsuario);
		if ((u.getEmail().equals(loginUsuario)) && (u.getPassword().equals(loginPassword))) {
			return u;
		} else {
			return null;
		}
	}

	public boolean saveUsuarioService(Usuario usuario) {
		boolean ok = true;

		Roles rol = new Roles(2, "jugador");
		usuario.setRoles(rol);
		usuarioRepo.save(usuario);
		
		return ok;
	}

}
