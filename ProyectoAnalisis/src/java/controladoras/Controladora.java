/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladoras;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jorge Alejandro
 */
@WebServlet(name = "Controladora", urlPatterns = {"/Controladora"})
public class Controladora extends HttpServlet {

    public static boolean moverArch(String archNombre) {
        boolean efectuado = false;
        File arch = new File(archNombre);
        if (arch.exists()) {
            System.out.println("\n*** Moviendo " + arch + " \n***");
            Path currentRelativePath = Paths.get("");
            String nuevoDir = currentRelativePath.toAbsolutePath().toString()
                    + File.separator + "src" + File.separator
                    + "controladoras" + File.separator + arch.getName();
            File archViejo = new File(nuevoDir);
            archViejo.delete();
            if (arch.renameTo(new File(nuevoDir))) {
                System.out.println("\n*** Generado " + archNombre + "***\n");
                efectuado = true;
            } else {
                System.out.println("\n*** No movido " + archNombre + " ***\n");
            }
        } else {
            System.out.println("\n*** Codigo no existente ***\n");
        }
        return efectuado;
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();
        String operacion = request.getParameter("operacion");
        if ("generar".equals(operacion)) {
            System.out.println("\n*** Generando ***\n");
            String archLexico = "";
            String archSintactico = "";
            System.out.println("\n*** Procesando archivo default ***\n");
            archLexico = "lexico.flex";
            archSintactico = "sintactico.cup";
            String[] alexico = {archLexico};
            String[] asintactico = {"-parser", "AnalizadorSintactico", archSintactico};
            jflex.Main.main(alexico);
            try {
                java_cup.Main.main(asintactico);
            } catch (Exception ex) {
                System.out.println("Error al generar el analizador sintactico");
            }
            //movemos los archivos generados
            boolean mvAL = moverArch("AnalizadorLexico.java");
            boolean mvAS = moverArch("AnalizadorSintactico.java");
            boolean mvSym = moverArch("sym.java");
            if (mvAL && mvAS && mvSym) {
                System.exit(0);
            }
            System.out.println("Generado!");
        } else if ("analizar".equals(operacion)) {

        }

//        try {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet Controladora</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet Controladora at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        } finally {
//            out.close();
//        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
