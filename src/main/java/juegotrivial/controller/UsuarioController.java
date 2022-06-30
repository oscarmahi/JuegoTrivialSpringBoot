package juegotrivial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import juegotrivial.model.Usuario;
import juegotrivial.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/registrar")
	public String irARegistro(Model model) {

		Usuario usuario = new Usuario();
		
		model.addAttribute("tipoFormulario", 2);
		model.addAttribute("usuario", usuario);
		return ("/usuario/registro");
	}
	
	@GetMapping("/perfil/{id}")
	public String verPerfil(@PathVariable("id") int id, Model model) {
		
		Usuario usuario = usuarioService.getUsuarioService(id);
		if (usuario.getImagen() != null) {
			String imagen = usuario.getImagen();
			imagen = (imagen.substring(1, imagen.length()));
			usuario.setImagen(imagen);
		}

		model.addAttribute("tipoFormulario", 1);
		model.addAttribute("usuario", usuario);
		return ("/usuario/registro");
	}	
	
	@PostMapping("/saveUser")
	public String guardarUsuario(@ModelAttribute Usuario usuario, Model model, @RequestParam String clave2, String fichero) {
		String error = null;

		if (!clave2.equals(usuario.getPassword())) {
			error = "Los campos clave han de ser iguales. Revise campos Clave.";
			model.addAttribute("error", error);
			model.addAttribute("usuarios", usuario);
			return ("/usuario/registro");
		} else {
			if (fichero.length() != 0) {
				fichero = "./img/avatar/".concat(fichero);
				usuario.setImagen(fichero);
			}
			boolean ok = usuarioService.saveUsuarioService(usuario);		
			if (!ok) {
				error = "El email ya existe. Empleado no Grabado. Intente con otro.";
				model.addAttribute("error", error);
				model.addAttribute("usuarios", usuario);
				return ("/usuario/registro");
			}
		}
		return "redirect:/";
	}
	
	@PostMapping("/updateUser")
	public String actualizarUsuario(@ModelAttribute Usuario usuario, Model model, @RequestParam String clave2, String fichero) {
		String error = null;

		if (!clave2.equals(usuario.getPassword())) {
			error = "Los campos clave han de ser iguales. Revise campos Clave.";
			model.addAttribute("error", error);
			model.addAttribute("usuarios", usuario);
			return ("/usuario/registro");
		} else {
			if (fichero.length() != 0) {
				fichero = "./img/avatar/".concat(fichero);
				usuario.setImagen(fichero);
			}
			boolean ok = usuarioService.saveUsuarioService(usuario);		
			if (!ok) {
				error = "El email ya existe. Empleado no Grabado. Intente con otro.";
				model.addAttribute("error", error);
				model.addAttribute("usuarios", usuario);
				return ("/usuario/registro");
			}
		}
		return "redirect:/usuario/perfil/" + usuario.getId();
	}	
}
