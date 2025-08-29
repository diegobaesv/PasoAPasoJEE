package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import config.DatabaseAccess;
import entities.Usuario;

public class UsuarioRepository {
		
	public void crearUsuario(Usuario usuario)  throws Exception {
		Connection cn = null;
		try {
			cn = DatabaseAccess.getConnection();
			cn.setAutoCommit(false);
			String sql = " INSERT INTO usuarios (nombre_usuario, clave, tipo_documento, numero_documento, nombres, apellido_paterno, apellido_materno ) ";
			sql+="VALUES (?, ?, ?, ?, ?, ?, ?) ";
			
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, usuario.getNombreUsuario());
			pstm.setString(2, usuario.getClave());
			pstm.setString(3, usuario.getTipoDocumento());
			pstm.setString(4, usuario.getNumeroDocumento());
			pstm.setString(5, usuario.getNombres());
			pstm.setString(6, usuario.getApellidoPaterno());
			pstm.setString(7, usuario.getApellidoMaterno());
			
			pstm.executeUpdate();
			cn.commit();
			pstm.close();

		} catch (Exception e) {
			System.out.println("UsuarioRepository::crearUsuario: "+e);
			throw new Exception("Ocurrio un error al crear usuario");
		} finally {
			if(cn != null) {
				cn.close();
			}
		}
	}
	
	public Usuario obtenerUsuarioPorNombreUsuario(String nombreUsuario)  throws Exception {
		Connection cn = null;
		Usuario usuario = null;
		try {
			cn = DatabaseAccess.getConnection();
			String sql = "SELECT id_usuario, nombre_usuario, clave, tipo_documento, numero_documento, nombres, apellido_paterno, apellido_materno, numero_telefono, correo_electronico, fecha_nacimiento, estado_auditoria, fecha_creacion ";
			sql+="from usuarios ";
			sql+="where estado_auditoria = '1' ";
			sql+="and nombre_usuario = ? ";
			
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, nombreUsuario);
			
			ResultSet rs = pstm.executeQuery();
			
			if(rs.next()) {
				usuario = resultSetToUsuario(rs);
			}
			return usuario;
		} catch (Exception e) {
			System.out.println("UsuarioRepository::obtenerUsuarioPorNombreUsuario: "+e);
			throw new Exception("Ocurrio un error al obtener usuario por nombre usuario");
		} finally {
			if(cn != null) {
				cn.close();
			}
		}
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
				Usuario u = resultSetToUsuario(rs);
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
	
	
	public Usuario obtenerUsuario(Integer idUsuario) throws Exception  {
		Connection cn = null;
		Usuario usuario = null;
		try {
			cn = DatabaseAccess.getConnection();
			String sql = "SELECT id_usuario, nombre_usuario, clave, tipo_documento, numero_documento, nombres, apellido_paterno, apellido_materno, numero_telefono, correo_electronico, fecha_nacimiento, estado_auditoria, fecha_creacion ";
			sql+="from usuarios ";
			sql+="where estado_auditoria = '1' ";
			sql+="and id_usuario = ? ";
			
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setInt(1, idUsuario);
			
			ResultSet rs = pstm.executeQuery();
			
			if(rs.next()) {
				usuario = resultSetToUsuario(rs);
			}
			return usuario;
		} catch (Exception e) {
			System.out.println("UsuarioRepository::obtenerUsuario: "+e);
			throw new Exception("Ocurrio un error al obtener usuario");
		} finally {
			if(cn != null) {
				cn.close();
			}
		}
	}
	
	private Usuario resultSetToUsuario(ResultSet rs) throws Exception {
		Usuario usuario = new Usuario();
		usuario.setIdUsuario(rs.getInt("id_usuario"));
		usuario.setNombreUsuario(rs.getString("nombre_usuario"));
		usuario.setClave(rs.getString("clave"));
		usuario.setTipoDocumento(rs.getString("tipo_documento"));
		usuario.setNumeroDocumento(rs.getString("numero_documento"));
		usuario.setNombres(rs.getString("nombres"));
		usuario.setApellidoPaterno(rs.getString("apellido_paterno"));
		usuario.setApellidoMaterno(rs.getString("apellido_materno"));
		usuario.setNumeroTelefono(rs.getString("numero_telefono"));
		usuario.setCorreoElectronico(rs.getString("correo_electronico"));
		usuario.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
		usuario.setEstadoAuditoria(rs.getString("estado_auditoria"));
		usuario.setFechaCreacion(rs.getDate("fecha_creacion"));
		return usuario;
	}
	
}
