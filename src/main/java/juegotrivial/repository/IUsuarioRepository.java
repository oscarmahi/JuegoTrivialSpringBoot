package juegotrivial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import juegotrivial.model.Usuario;


public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	Usuario findByEmail(String email);
	
	Usuario findById(int id);

}
