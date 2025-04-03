package empleos.modelo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import empleos.modelo.entity.Vacantes;
import empleos.modelo.repository.VacantesRepository;

@Service
public class VacantesServiceImpl implements VacantesService{
	
	@Autowired
	private VacantesRepository vrepo;

	@Override
	public List<Vacantes> buscarVacantes(String empresa, String tipoContrato, Integer idCategoria) {
	    return vrepo.buscarVacantesPorFiltro(
	        empresa == null ? "" : empresa,
	        tipoContrato == null ? "" : tipoContrato,
	        idCategoria
	    );
	}


}
