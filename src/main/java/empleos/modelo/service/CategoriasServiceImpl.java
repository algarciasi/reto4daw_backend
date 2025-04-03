package empleos.modelo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import empleos.modelo.entity.Categorias;
import empleos.modelo.repository.CategoriasRepository;

@Service
public class CategoriasServiceImpl implements CategoriasService{
	
	@Autowired
	private CategoriasRepository crepo;

	@Override
	public List<Categorias> listarCategorias() {
		return crepo.findAll();
	}

	@Override
	public Categorias nuevaCategoria(Categorias categoria) {
		return crepo.save(categoria);
	}

	@Override
	public Categorias actualizarCategoria(int idCategoria, Categorias datos) {
		return crepo.findById(idCategoria)
		        .map(cat -> {
		            cat.setNombre(datos.getNombre());
		            cat.setDescripcion(datos.getDescripcion());
		            return crepo.save(cat);
		        })
		        .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + idCategoria));
	}

	@Override
	public void eliminarCategoria(int idCategoria) {
		crepo.deleteById(idCategoria);
		
	}

}
