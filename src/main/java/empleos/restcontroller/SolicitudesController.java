package empleos.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import empleos.modelo.dto.SolicitudesDto;
import empleos.modelo.entity.Solicitudes;
import empleos.modelo.service.EmpresasService;
import empleos.modelo.service.SolicitudesService;
import empleos.modelo.service.VacantesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/solicitudes")
@Tag(name = "Solicitudes", description = "Controlador de Solicitudes")
public class SolicitudesController {
	
	@Autowired
	private VacantesService vserv;
	
	@Autowired
	private EmpresasService eserv;
	
	@Autowired
	private SolicitudesService sserv;
	
	
	@GetMapping("/todas")
	@Operation(summary = "Buscar todas las solicitudes")
	public ResponseEntity<List<Solicitudes>> listarSolicitudes() {
        return ResponseEntity.ok(eserv.todasSolicitudes());
    }
	
	@PostMapping("/nueva")
	@Operation(summary = "Enviar solicitud de empleo a una vacante")
	public ResponseEntity<Solicitudes> enviarSolicitud(@RequestBody SolicitudesDto request) {
	    System.out.println("📨 Enviando solicitud para vacante ID: " + request.getIdVacante());
	    System.out.println("👤 Usuario: " + request.getEmailUsuario());

	    Solicitudes creada = sserv.enviarSolicitud(
	        request.getIdVacante(),
	        request.getEmailUsuario(),
	        request.getSolicitud()
	    );

	    return ResponseEntity.ok(creada);
	}


}
