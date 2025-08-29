package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import entities.Usuario;
import services.UsuarioService;
import shared.BaseResponse;

@WebServlet("/usuarios/*")
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final UsuarioService usuarioService;

    public UsuarioServlet() {
        super();
        usuarioService = new UsuarioService();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
    	try {
    		String pathParam = request.getPathInfo();
    		System.out.println("pathParam:: "+pathParam);
    		if(pathParam == null || pathParam.equals("/")) {
    			System.out.println("Ejecutando listarUsuarios");
    			List<Usuario> usuarios = usuarioService.listarUsuarios();
    	    	response.getWriter().write(new Gson().toJson(BaseResponse.success(usuarios)));
    		} else {
    			System.out.println("Ejecutando obtenerUsuario");
    			Integer idUsuario = Integer.parseInt(pathParam.replace("/", "").trim());
    			Usuario usuario = usuarioService.obtenerUsuario(idUsuario);
    			response.getWriter().write(new Gson().toJson(BaseResponse.success(usuario)));
    		}
		} catch(Exception e) {
			response.getWriter().write(new Gson().toJson(BaseResponse.error(e.getMessage())));
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		try {
			BufferedReader reader = request.getReader(); //captura el BODY String Json
			Usuario usuario = new Gson().fromJson(reader, Usuario.class);
			usuarioService.crearUsuario(usuario);
			response.getWriter().write(new Gson().toJson(BaseResponse.success(null)));
		} catch (Exception e) {
			response.getWriter().write(new Gson().toJson(BaseResponse.error(e.getMessage())));
		}
	}

}
