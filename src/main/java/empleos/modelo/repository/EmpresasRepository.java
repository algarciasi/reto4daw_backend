package empleos.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import empleos.modelo.entity.Empresas;

public interface EmpresasRepository extends JpaRepository<Empresas, Integer>{

}
