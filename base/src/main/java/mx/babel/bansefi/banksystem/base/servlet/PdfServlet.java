package mx.babel.bansefi.banksystem.base.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;

/**
 * Servlet implementation class PdfServlet
 */
@WebServlet("/PdfServlet")
public class PdfServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException, ControlableException, NoControlableException {

        String[] array=request.getParameterValues("nombrePdf");
        
        if(array!=null && array.length>0){
            String pdfFileName = array[0];
            try{
                File pdfFile = new File(pdfFileName);
               
                FileInputStream fileInputStream = new FileInputStream(pdfFile);
                OutputStream responseOutputStream = response.getOutputStream();
                int bytes;
                while ((bytes = fileInputStream.read()) != -1) {
                    responseOutputStream.write(bytes);
                }
                response.setContentType("application/pdf");
                response.addHeader("Content-Disposition", "inline; filename=" + pdfFileName);
                response.setContentLength((int) pdfFile.length());
                fileInputStream.close();
                pdfFile.delete();
            }catch (NullPointerException npe){
                response.sendRedirect("/banksystem/faces/views/error/error500.xhtml?faces-redirect=true");  
                
            }catch (FileNotFoundException fnfe){
                response.sendRedirect("/banksystem/faces/views/error/error500.xhtml?faces-redirect=true");  

            }catch (IOException ioe){
                response.sendRedirect("/banksystem/faces/views/error/error500.xhtml?faces-redirect=true");
                
            }
        }
     }

}
