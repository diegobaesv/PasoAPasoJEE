package controllers;

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

@WebServlet("/usuarios")
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
			List<Usuario> usuarios = usuarioService.listarUsuarios();
	    	response.getWriter().write(new Gson().toJson(BaseResponse.success(usuarios)));
		} catch(Exception e) {
			response.getWriter().write(new Gson().toJson(BaseResponse.error(e.getMessage())));
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Haciendo peticion desde POST");
	}

}
