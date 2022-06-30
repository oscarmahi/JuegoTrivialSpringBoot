package juegotrivial.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import juegotrivial.model.Usuario;
import juegotrivial.service.UsuarioService;

@Controller
@RequestMapping("")
public class AccesoController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("")
	public String index() {
		return ("index");
	}

	@GetMapping("/inicio")
	public String irAInicio() {
		return ("/inicio");
	}

//	@GetMapping("/registrar")
//	public String irARegistro(Model model) {
//
//		Usuario usuario = new Usuario();
//		model.addAttribute("usuario", usuario);
//		return ("/comunes/registro");
//	}
//	
//	@GetMapping("/perfil")
//	public String verPerfil(Model model) {
//
//		Usuario usuario = new Usuario();
//		model.addAttribute("usuario", usuario);
//		return ("/comunes/registro");
//	}

	@PostMapping("/inicio")
	public String login(Model model, @RequestParam String loginUsuario, @RequestParam String loginPassword,
			HttpSession misesion) {

		if ((loginUsuario.length() != 0) && (loginPassword.length()) != 0) {
			Usuario usuarioM = usuarioService.findByEmailService(loginUsuario, loginPassword);
			if (usuarioM != null) {
				misesion.setAttribute("usuarioSesion", usuarioM);
				return "/inicio";
			}
		}
		return "/index";
	}

//	@PostMapping("/saveUser")
//	public String guardarUsuario(@ModelAttribute Usuario usuario, Model model, @RequestParam String clave2, String fichero) {
//		String error = null;
//
//		if (!clave2.equals(usuario.getPassword())) {
//			error = "Los campos clave han de ser iguales. Revise campos Clave.";
//			model.addAttribute("error", error);
//			model.addAttribute("usuarios", usuario);
//			return ("/comunes/registro");
//		} else {
//			if (fichero.length() != 0) {
//				fichero = "./img/avatar/".concat(fichero);
//				usuario.setImagen(fichero);
//			}
//			boolean ok = usuarioService.saveUsuarioService(usuario);		
//			if (!ok) {
//				error = "El email ya existe. Empleado no Grabado. Intente con otro.";
//				model.addAttribute("error", error);
//				model.addAttribute("usuarios", usuario);
//				return ("/comunes/registro");
//			}
//		}
//		return "/index";
//	}

}
