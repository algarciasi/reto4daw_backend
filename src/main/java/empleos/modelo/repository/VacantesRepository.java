package empleos.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import empleos.modelo.entity.Vacantes;

public interface VacantesRepository extends JpaRepository<Vacantes, Integer>{
	@Query("SELECT v FROM Vacantes v WHERE "
		     + "(:nombre IS NULL OR v.nombre LIKE %:nombre%) AND "
		     + "(:categoria IS NULL OR v.categoria.nombre = :categoria) AND "
		     + "(:destacado IS NULL OR v.destacado = :destacado)")
		List<Vacantes> buscarVacantesPorFiltro(
		    @Param("nombre") String nombre,
		    @Param("categoria") String categoria,
		    @Param("destacado") Integer destacado);

}
