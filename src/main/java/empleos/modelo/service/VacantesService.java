package empleos.modelo.service;

import java.util.List;

import empleos.modelo.entity.Vacantes;

public interface VacantesService {
	List<Vacantes> buscarVacantes(String empresa, String tipoContrato, Integer idCategoria);

}
