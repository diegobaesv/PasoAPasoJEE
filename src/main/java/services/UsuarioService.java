package services;

import java.util.List;
import entities.Usuario;
import repositories.UsuarioRepository;

public class UsuarioService {
	
	private final UsuarioRepository usuarioRepository;
	
	public UsuarioService() {
		usuarioRepository = new UsuarioRepository();
	}
	
	public void crearUsuario(Usuario usuario) throws Exception {
		Usuario usuarioExistente = usuarioRepository.obtenerUsuarioPorNombreUsuario(usuario.getNombreUsuario());
		if(usuarioExistente == null) {
			usuarioRepository.crearUsuario(usuario);
			return;
		}
		throw new Exception("Usuario ya existe");
	}
	
	public List<Usuario> listarUsuarios() throws Exception {
		return usuarioRepository.listarUsuarios();
	}
	
	public Usuario obtenerUsuario(Integer idUsuario) throws Exception {
		return usuarioRepository.obtenerUsuario(idUsuario);
	}
		
}
