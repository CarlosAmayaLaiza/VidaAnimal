package com.example.demo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.Repository.IUsuarioRepository;
import com.example.demo.classes.Usuario;
import com.example.demo.classes.UsuarioService;
import com.example.demo.classes.correo.EmailService;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = "https://frontend-vidaanimal.azurewebsites.net")
@RequestMapping("/api/pago")
public class UsuarioController {

    private final UsuarioService usuarioService;
	
	
	@Autowired
	private IUsuarioRepository repoUsuario;
	
	@Autowired
	private EmailService emailService;



    UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
	

    @PostMapping("/registrarUsuario")
    public String registrarUsuario(@RequestParam String username,
                                   @RequestParam String email,
                                   @RequestParam String password,
                                   Model model) {

        Usuario nuevo = new Usuario();
        nuevo.setUsername(username);
        nuevo.setEmail(email);
        nuevo.setPassword(password);
        repoUsuario.save(nuevo);

        try {
            emailService.sendWelcomeEmail(email, username);
            model.addAttribute("mensaje", "✅ Usuario registrado correctamente. Revisa tu correo de bienvenida.");
        } catch (MessagingException e) {
            e.printStackTrace();
            model.addAttribute("mensaje", "⚠️ Usuario registrado, pero falló el envío del correo.");
        }

        return "login";
    }
    
    
	
    
	
    @GetMapping("/login")
    public String mostrarLogin() {
        return "login"; // tu template Thymeleaf de login
    }

	
	// CARGAR FORMULARIO PARA INGRESAR AL LOGIN
	
    @PostMapping("/login")
    public String validarLogin(@RequestParam String email, @RequestParam String password, Model model, HttpSession session) {

        Usuario usuario = repoUsuario.findByEmail(email);

        if (usuario != null && usuario.getPassword().equals(password)) {
            System.out.println("Usuario logueado: " + usuario.getUsername());
            session.setAttribute("usuarioLogueado", usuario);
            return "principal";
        } else {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "login";
        }
    }

	

	
	
    @GetMapping("/logout")
    public String cerrarSesion(HttpSession session) {
        session.invalidate();
        return "principal";
    }

    @GetMapping("/perfil")
    public String mostrarPerfil(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return "principal";
        }
        model.addAttribute("usuario", usuario);
        return "perfil";
    }


	
	
	

    @PostMapping("/actualizarPerfil")
    public String actualizarPerfil(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        
        if (usuario != null) {
            usuario.setEmail(email);
            usuario.setPassword(password);
            usuarioService.actualizar(usuario);
            redirectAttributes.addFlashAttribute("mensaje", "Perfil actualizado correctamente.");
        }

        return "redirect:/perfil";
    }

	
	
	
	
	@PostMapping("/enviar-correo")
    public ResponseEntity<String> enviarCorreo(@RequestBody Map<String, Object> datos) {
        try {
            emailService.enviarCorreoDeCompra(datos);
            return ResponseEntity.ok("Correo enviado exitosamente.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al enviar correo: " + e.getMessage());
        }
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
