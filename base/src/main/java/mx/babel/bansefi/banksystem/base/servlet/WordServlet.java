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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Servlet implementation class WordServlet
 */
@WebServlet("/WordServlet")
public class WordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LogManager.getLogger(WordServlet.class.getName());
	
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException{

        String[] array=request.getParameterValues("nombreWord");
        
        if(array!=null && array.length>0){
            String wordFileName = array[0];
            try{
                File wordFile = new File(wordFileName);
               
                response.setContentType("application/msword");
                response.addHeader("Content-Disposition", "inline; filename=" + wordFileName);
                
                FileInputStream fileInputStream = new FileInputStream(wordFile);
                OutputStream responseOutputStream = response.getOutputStream();
                int bytes;
                while ((bytes = fileInputStream.read()) != -1) {
                    responseOutputStream.write(bytes);
                }
               
                response.setContentLength((int) wordFile.length());
                fileInputStream.close();
                wordFile.delete();
            }catch (NullPointerException npe){
            	LOGGER.debug("Error generando el documento: "+npe);
                response.sendRedirect("/banksystem/faces/views/error/error500.xhtml?faces-redirect=true");  
                
            }catch (FileNotFoundException fnfe){
            	LOGGER.debug("Error generando el documento: "+fnfe);
                response.sendRedirect("/banksystem/faces/views/error/error500.xhtml?faces-redirect=true");  

            }catch (IOException ioe){
            	LOGGER.debug("Error generando el documento: "+ioe);
                response.sendRedirect("/banksystem/faces/views/error/error500.xhtml?faces-redirect=true");
                
            }
        }
     }

}
