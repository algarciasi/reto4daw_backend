package empleos.modelo.dto;

import empleos.modelo.entity.Vacantes;
import lombok.Data;

@Data
public class VacantesDto {

	private Vacantes vacante;
    private int idEmpresa;
}
