package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import config.DatabaseAccess;
import entities.Usuario;

public class UsuarioRepository {
		
	public void crearUsuario(Usuario usuario) {
		
	}
	
	public Usuario obtenerUsuarioPorNombreUsuario(String nombreUsuario) {
		return null;
	}
	
	public List<Usuario> listarUsuarios() throws Exception {
		Connection cn = null;
		List<Usuario> usuarios = null;
		try {
			cn = DatabaseAccess.getConnection();
			String sql = "SELECT id_usuario, nombre_usuario, clave, tipo_documento, numero_documento, nombres, apellido_paterno, apellido_materno, numero_telefono, correo_electronico, fecha_nacimiento, estado_auditoria, fecha_creacion ";
			sql+="from usuarios ";
			sql+="where estado_auditoria = '1'";
			
			PreparedStatement pstm = cn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			usuarios = new ArrayList();
			while(rs.next()) {
				Usuario u = new Usuario();
				u.setIdUsuario(rs.getInt("id_usuario"));
				u.setNombreUsuario(rs.getString("nombre_usuario"));
				u.setClave(rs.getString("clave"));
				u.setTipoDocumento(rs.getString("tipo_documento"));
				u.setNumeroDocumento(rs.getString("numero_documento"));
				u.setNombres(rs.getString("nombres"));
				u.setApellidoPaterno(rs.getString("apellido_paterno"));
				u.setApellidoMaterno(rs.getString("apellido_materno"));
				u.setNumeroTelefono(rs.getString("numero_telefono"));
				u.setCorreoElectronico(rs.getString("correo_electronico"));
				u.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
				u.setEstadoAuditoria(rs.getString("estado_auditoria"));
				u.setFechaCreacion(rs.getDate("fecha_creacion"));
				usuarios.add(u);
			}
			return usuarios;
		} catch (Exception e) {
			System.out.println("UsuarioRepository::listarUsuarios: "+e);
			throw new Exception("Ocurrio un error al listar usuarios");
		} finally {
			if(cn != null) {
				cn.close();
			}
		}
	}
	
	
	public Usuario obtenerUsuario(Integer idUsuario) {
		return null;
	}
	
}
