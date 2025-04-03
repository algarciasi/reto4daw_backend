package empleos.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import empleos.modelo.dto.VacantesDto;
import empleos.modelo.entity.Vacantes;
import empleos.modelo.service.EmpresasService;
import empleos.modelo.service.SolicitudesService;
import empleos.modelo.service.VacantesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/vacantes")
@Tag(name = "Vacantes", description = "Controlador de Vacantes")
public class VacantesController {
	
	@Autowired
	private VacantesService vserv;
	
	@Autowired
	private EmpresasService eserv;
	
	@Autowired
	private SolicitudesService sserv;
	
	@GetMapping("/todas")
	@Operation(summary = "Buscar todas las vacantes")
	public ResponseEntity<List<Vacantes>> listarVacantes() {
        return ResponseEntity.ok(eserv.todasVacantes());
    }
	
	@PostMapping
	@Operation(summary = "Crear una nueva vacante para una empresa")
	public ResponseEntity<Vacantes> crearVacante(@RequestBody VacantesDto request) {
	    System.out.println("Vacante recibida: " + request.getVacante());
	    System.out.println("Empresa ID: " + request.getIdEmpresa());

	    Vacantes nueva = eserv.nuevaVacantes(request.getVacante(), request.getIdEmpresa());
	    return ResponseEntity.ok(nueva);
	}
	
	@GetMapping("/buscar")
	@Operation(summary = "Buscar vacantes con filtros por empresa, tipo de contrato y categoría")
	public ResponseEntity<List<Vacantes>> buscarVacantesConFiltros(
	        @RequestParam(required = false) String empresa,
	        @RequestParam(required = false) String tipoContrato,
	        @RequestParam(required = false) Integer idCategoria) {
	    
	    List<Vacantes> resultado = vserv.buscarVacantes(empresa, tipoContrato, idCategoria);
	    return ResponseEntity.ok(resultado);
	}
	
	@PutMapping("/{id}")
	@Operation(summary = "Actualizar una vacante existente")
	public ResponseEntity<Vacantes> actualizarVacante(
	        @PathVariable int id,
	        @RequestBody Vacantes detalle) {

	    System.out.println("Actualizando vacante ID: " + id);
	    System.out.println("Nuevos datos: " + detalle);

	    Vacantes actualizada = eserv.actualizarVacantes(id, detalle);
	    return ResponseEntity.ok(actualizada);
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Cancelar una vacante")
	public ResponseEntity<Void> cancelarVacante(@PathVariable int id) {
	    System.out.println("Cancelando vacante ID: " + id);

	    eserv.cancelarVacantes(id);
	    return ResponseEntity.noContent().build(); // 204 No Content
	}
	
	@PutMapping("/asignar/{idSolicitud}")
	@Operation(summary = "Asignar una vacante a un usuario desde una solicitud")
	public ResponseEntity<Void> asignarVacante(@PathVariable int idSolicitud) {
	    System.out.println("Asignando vacante desde solicitud ID: " + idSolicitud);

	    eserv.asignarVacantes(idSolicitud);
	    return ResponseEntity.noContent().build(); // 204 No Content
	}

	




	

}
