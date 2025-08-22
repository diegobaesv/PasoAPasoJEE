package repositories;

import java.util.ArrayList;
import java.util.List;
import entities.Usuario;

public class UsuarioRepository {
	
	public void crearUsuario(Usuario usuario) {
		
	}
	
	public Usuario obtenerUsuarioPorNombreUsuario(String nombreUsuario) {
		return null;
	}
	
	public List<Usuario> listarUsuarios() {
		List<Usuario> lista = new ArrayList();
		
		Usuario usuario1 = new Usuario();
		usuario1.setIdUsuario(1);
		usuario1.setTipoDocumento("DNI");
		usuario1.setNumeroDocumento("70506030");
		usuario1.setApellidoPaterno("Gold");
		usuario1.setApellidoMaterno("Yellow");
		usuario1.setNombres("Maria");
		
		Usuario usuario2 = new Usuario();
		usuario2.setIdUsuario(2);
		usuario2.setTipoDocumento("CE");
		usuario2.setNumeroDocumento("AB90605040");
		usuario2.setApellidoPaterno("Blue");
		usuario2.setApellidoMaterno("Orange");
		usuario2.setNombres("Carlos");
		
		lista.add(usuario1);
		lista.add(usuario2);
		
		return lista;
	}
	
	
	public Usuario obtenerUsuario(Integer idUsuario) {
		return null;
	}
	
}
