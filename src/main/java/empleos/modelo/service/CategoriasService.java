package empleos.modelo.service;

import java.util.List;

import empleos.modelo.entity.Categorias;

public interface CategoriasService {
	
	// apartado B Rol Administrador
	
	List<Categorias> listarCategorias();
	Categorias nuevaCategoria(Categorias categoria);
	Categorias actualizarCategoria(int idCategoria, Categorias datos);
	void eliminarCategoria(int idCategoria);


}
