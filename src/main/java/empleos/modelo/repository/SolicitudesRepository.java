package empleos.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import empleos.modelo.entity.Solicitudes;

public interface SolicitudesRepository extends JpaRepository<Solicitudes, Integer>{
    List<Solicitudes> findByVacanteIdVacante(int idVacante);
    List<Solicitudes> findByUsuarioEmail(String email);


}
