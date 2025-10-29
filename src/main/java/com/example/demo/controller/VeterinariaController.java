package com.example.demo.controller;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Repository.IAnimalRepository;
import com.example.demo.Repository.ICitaRepository;
import com.example.demo.Repository.IDetalleRepository;
import com.example.demo.Repository.IEstadoCitaRepository;

import com.example.demo.Repository.IServicioRepository;
import com.example.demo.Repository.IVeterinarioRepository;
import com.example.demo.classes.Animal;
import com.example.demo.classes.Cita;
import com.example.demo.classes.DetalleCita;

import com.example.demo.classes.Servicio;
import com.example.demo.classes.Usuario;
import com.example.demo.classes.Veterinario;

import jakarta.servlet.http.HttpSession;

import java.time.format.DateTimeFormatter;

@Controller
public class VeterinariaController {
	
	@Autowired
	private IAnimalRepository repoAnimal;
	
	@Autowired
	private ICitaRepository repoCita;
	
	@Autowired
	private IDetalleRepository repoDetalle;
	
	@Autowired
	private IEstadoCitaRepository repoEstadoCita;
	
	
	@Autowired
	private IServicioRepository repoServicio;
	
	
	@Autowired
	private IVeterinarioRepository repoVeterinario;
	
	
	
	@GetMapping("/principal")
	public String mostrarPrincipal() {
	    return "principal"; // nombre de la plantilla en src/main/resources/templates/principal.html
	}

	
	@GetMapping("/adopcion")
	public String mostraradopcion() {
	    return "adopcion"; // nombre de la plantilla en src/main/resources/templates/principal.html
	}

	
	
	
	
	@GetMapping("/nosotros")
	public String mostrarsubpagina() {
	    return "nosotros"; // Thymeleaf buscará templates/nosotros.html
	}

	
	
	
	
	@GetMapping("/servicio")
	public String mostrarservicio() {
	    return "servicio"; // Thymeleaf buscará templates/servicio.html
	}

	
	
	@GetMapping("/equipo")
	public String mostrarequipo() {
	    return "equipo"; // Thymeleaf buscará templates/equipo.html
	}

	
	
	
	
	@GetMapping("/contacto")
	public String mostrarContacto() {
	    return "contacto"; // Thymeleaf buscará templates/contacto.html
	}

	
	
	
	
	@GetMapping("/medicinageneral")
	public String mostrarmedicinageneral() {
	    return "medicinageneral"; // Thymeleaf buscará templates/medicinageneral.html
	}

	
	
	
	
	
	@GetMapping("/cirugiasespecializados")
	public String mostrarcirugiasespecializados() {
	    return "cirugiasespecializados"; // Thymeleaf buscará templates/cirugiasespecializados.html
	}

	
	
	
	
	
	@GetMapping("/laboratorio")
	public String mostrarlaboratorio() {
	    return "laboratorio"; // Thymeleaf buscará templates/laboratorio.html
	}

	
	
	
	
	
	@GetMapping("/peluqueria")
	public String mostrarpeluqueria() {
	    return "peluqueria"; // Thymeleaf buscará templates/perluqueria.html
	}

	
	@GetMapping("/vacunacion")
	public String mostrarvacunacion() {
	    return "vacunacion"; // Thymeleaf buscará templates/vacunación.html
	}

	
	
	
	
	@GetMapping("/guarderia")
	public String mostrarguarderia() {
	    return "guarderia"; // Thymeleaf buscará templates/nosotros.html
	}

	
	
	
	@GetMapping("/login")
	public String login() {
	    return "login"; // Thymeleaf buscará templates/login.html
	}

	
	
	/*ALIMENTOS*/
	
	@GetMapping("/alimentoperro")
	public String alimentoperro() {
	    return "alimentoperro"; // Thymeleaf buscará templates/alimentoperro.html
	}

	
	
	@GetMapping("/alimentogato")
	public String alimentogato() {
	    return "alimentogato"; // Thymeleaf buscará templates/alimentoperro.html
	}

	
	
	
	
	//JUGUETES
	
	
	@GetMapping("/jugueteperro")
	public String jugueteperro() {
	    return "jugueteperro"; // Thymeleaf buscará templates/alimentoperro.html
	}

	
	
	@GetMapping("/juguetegato")
	public String juguetegato() {
	    return "juguetegato"; // Thymeleaf buscará templates/alimentoperro.html
	}

	
	
	//ACCESORIOS
	
	
	@GetMapping("/accesoriosperro")
	public String accesoriosperro() {
	    return "accesoriosperro"; // Thymeleaf buscará templates/alimentoperro.html
	}
	
	
	
	@GetMapping("/accesoriosgato")
	public String accesoriosgato() {
	    return "accesoriosgato"; // Thymeleaf buscará templates/alimentoperro.html
	}
	
	
	
	
	
	@GetMapping("/carrocompras")
	public String carrocompras() {
	    return "carrocompras"; // Thymeleaf buscará templates/alimentoperro.html
	}

	
	
	
	
	
	

	
	
	
	
	

//DEBEMOS TENER CLASE CITA YA QUE NECESITA ESE PARAMETRO POR LA BASE DE DATOS 
	@GetMapping("/prueba")
	public String mostrarprueba(Model model) {
	    model.addAttribute("cita", new Cita());  // Crear un objeto vacío para el formulario
	    return "prueba";
	}

	
	

@GetMapping("/cargar")
public String cargarPaginaRegistrar(Model model) {
    Cita cita = new Cita();
    cita.setAnimal(new Animal());
    cita.setPrecio_cita(new BigDecimal("50.00"));

    model.addAttribute("cita", cita);

    // Obtener todos los servicios
    List<Servicio> listaServicios = repoServicio.findAll();
    System.out.println("Servicios encontrados: " + listaServicios.size()); // 🔍 Debug
    model.addAttribute("servicios", listaServicios);

    // Veterinarios
    List<Veterinario> listaVeterinarios = repoVeterinario.findAll();
    model.addAttribute("veterinarios", listaVeterinarios);

    return "prueba";  // nombre de la plantilla Thymeleaf
}


	

	

	
	
	@GetMapping("/form-cita")
	public String mostrarFormulario(Model model) {
	    Cita cita = new Cita();
	    BigDecimal precioCita = new BigDecimal("50.00");
	    cita.setPrecio_cita(precioCita);
	    cita.setTotal(precioCita);  // inicial total = precio cita sin servicios
	    cita.setVeterinario(null);
	    model.addAttribute("cita", cita);
	    model.addAttribute("ltsServicios", repoServicio.findAll());  // servicios con id y precio

	    return "form-cita";
	}

	
	
	
	
	@GetMapping("/servicio/precio/{id}")
	@ResponseBody
	public BigDecimal obtenerPrecioServicio(@PathVariable("id") int idServicio) {
	    Servicio servicio = repoServicio.findById(idServicio).orElse(null);
	    if (servicio != null) {
	        return servicio.getPrecio();
	    }
	    return BigDecimal.ZERO;
	}

	
	
	
	
	
	
	
	
	
	
	@PostMapping("/grabar")
	public String grabar(@ModelAttribute Cita cita,
	                     @RequestParam("veterinarioId") int veterinarioId,
	                     @RequestParam("id_servicio") int idServicio,
	                     Model model,
	                     HttpSession session) {

	    try {
	        // 0. Obtener usuario logueado de la sesión
	        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");
	        if (usuarioLogueado == null) {
	            return "redirect:/login"; // si no hay usuario, redirige al login
	        }
	        cita.setUsuario(usuarioLogueado);

	        // 1. Precio base fijo
	        BigDecimal precioCita = new BigDecimal("50.00");
	        cita.setPrecio_cita(precioCita);

	        // 2. Validar Animal
	        Animal animal = cita.getAnimal();
	        if (animal == null) {
	            model.addAttribute("mensaje", "El animal es obligatorio");
	            model.addAttribute("cssmensaje", "alert alert-danger");
	            model.addAttribute("cita", cita);
	            model.addAttribute("veterinarios", repoVeterinario.findAll());
	            model.addAttribute("ltsServicios", repoServicio.findAll());
	            return "prueba";
	        }

	        // 3. Guardar Animal si es nuevo
	        if (animal.getId_animal() == 0) {
	            animal = repoAnimal.save(animal);
	        }
	        cita.setAnimal(animal);

	        // 4. Asignar Veterinario
	        Veterinario vet = repoVeterinario.findById(veterinarioId).orElse(null);
	        if (vet == null) {
	            model.addAttribute("mensaje", "Veterinario no encontrado");
	            model.addAttribute("cssmensaje", "alert alert-danger");
	            model.addAttribute("cita", cita);
	            model.addAttribute("veterinarios", repoVeterinario.findAll());
	            model.addAttribute("ltsServicios", repoServicio.findAll());
	            return "prueba";
	        }
	        cita.setVeterinario(vet);

	        // 5. Asignar Servicio
	        Servicio servicio = repoServicio.findById(idServicio).orElse(null);
	        if (servicio == null) {
	            model.addAttribute("mensaje", "Servicio no encontrado");
	            model.addAttribute("cssmensaje", "alert alert-danger");
	            model.addAttribute("cita", cita);
	            model.addAttribute("veterinarios", repoVeterinario.findAll());
	            model.addAttribute("ltsServicios", repoServicio.findAll());
	            return "prueba";
	        }
	        cita.setServicio(servicio);

	        // 6. Guardar la Cita
	        Cita citaGuardada = repoCita.save(cita);

	        // 7. Crear y guardar detalle de la cita
	        DetalleCita detalle = new DetalleCita();
	        detalle.setCita(citaGuardada);
	        detalle.setServicio(servicio);
	        detalle.setPrecio_servicio(servicio.getPrecio());
	        repoDetalle.save(detalle);

	        // 8. Calcular total y actualizar la cita
	        BigDecimal total = precioCita.add(servicio.getPrecio());
	        citaGuardada.setTotal(total);
	        repoCita.save(citaGuardada);

	        model.addAttribute("mensaje", "Registro OK");
	        model.addAttribute("cssmensaje", "alert alert-success");

	    } catch (Exception e) {
	        model.addAttribute("mensaje", "Error al registrar: " + e.getMessage());
	        model.addAttribute("cssmensaje", "alert alert-danger");
	    }

	    // Preparar formulario limpio
	    model.addAttribute("cita", new Cita());
	    model.addAttribute("veterinarios", repoVeterinario.findAll());
	    model.addAttribute("ltsServicios", repoServicio.findAll());
	    return "prueba";
	}

	
	
	
	
	
	
	
	
	
	
	
	//LISTAR LAS CITAS MEDINATE USUARIO LOGEADO
	
	//lista las citas Registradas
	
	@GetMapping("/citas")
	public String listarCitas(Model model, HttpSession session) {
	    Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");
	    if (usuarioLogueado == null) {
	        return "redirect:/login";
	    }

	    List<Cita> citas = repoCita.findByUsuarioId(usuarioLogueado.getId());
	    model.addAttribute("citas", citas);

	    return "listarRegistro"; // o el nombre de tu template Thymeleaf
	}

	
	
	
	
	
	//LISTAR LOS REGISTROS DE LA PERSONA  
	// EN LA PRINCIPAL 
	
	 @GetMapping("/listarRegistro")
	    public String listarRegistros(Model model) {
	        // Aquí agregar lógica para llenar el modelo si es necesario
	        return "listarRegistro"; // Asegúrate de tener listarRegistro.html o .jsp en templates
	    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
