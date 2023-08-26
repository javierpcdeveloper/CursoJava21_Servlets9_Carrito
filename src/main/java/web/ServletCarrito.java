package web;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/CarritoServlet")
public class ServletCarrito extends HttpServlet{
    @Override
    public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException{
        res.setContentType("text/html;charset=UTF-8");
        HttpSession sesion=req.getSession();
        //recuperamos la lista de articulos previos (si existen)
        Map<String,Integer> listaArticulos=(Map<String,Integer>)sesion.getAttribute("articulos");
        if(listaArticulos==null){
            listaArticulos=new HashMap<>();
            sesion.setAttribute("articulos",listaArticulos);
        }
        //procesamos el nuevo ariticulo solicitado
        String articuloNuevo=req.getParameter("articulo");
        int cantidad=Integer.valueOf(req.getParameter("cantidad"));
        if(articuloNuevo!=null && !articuloNuevo.trim().equals("") && cantidad!=0){
            listaArticulos.put(articuloNuevo, cantidad);
        }
        PrintWriter out=res.getWriter();
        out.print("<h2>Lista de articulos</h2><br>");
        for (String arti: listaArticulos.keySet()) {
            Integer canti=listaArticulos.get(arti);
            out.print("<li>"+arti+" x "+canti+"</li>");
        }
        out.print("<a href='/CursoJava21_Servlets9_Carrito'>Volver</a>");
        out.close();
    }
}