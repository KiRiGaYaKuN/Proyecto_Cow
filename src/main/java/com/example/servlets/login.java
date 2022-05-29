/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.servlets;
import com.example.services.ConsultaService;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import javax.servlet.*;
import javax.servlet.http.*;
/**
 *
 * @author estev
 */
public class login extends HttpServlet { public void doGet(HttpServletRequest request,

HttpServletResponse response)throws ServletException, IOException {

// Use "request" to read incoming HTTP headers (e.g. cookies) // and HTML form data (e.g. data the user entered and submitted)

// Use "response" to specify the HTTP response line and headers // (e.g. specifying the content type, setting cookies).
String cedula = request.getParameter("cedula");
String clave = request.getParameter("clave");
String tipo = request.getParameter("tipo");
String clave2="a";

if (tipo.equals("emprendedor")) {
    //clave2=ConsultaService.veremprendedor1(cedula);
    if(clave==clave2){
        out.println("<meta http-equiv=\"refresh\" content=\"0; url=http://localhost:8080/Proyecto_Crow/emprendedor.jsp\" />");
    }
} 
else if(tipo.equals("donante")){
    
} else{
    out.println("<meta http-equiv=\"refresh\" content=\"0; url=http://localhost:8080/Proyecto_Crow/index.html\" />");
}



}
}


