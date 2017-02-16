/**
 * This is the SamlResponder class/servlet at the Service Provider 
 * It receives the ArtifactReponse from the Authentication Server and send Authentication Request to AS .. 
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author root
 */
public class SamlResponder extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           
            String url = request.getRequestURL().toString();
            url = (url.split("S"))[0];
            //retrieve samlAResponse ..
            
            String samlAResponse=request.getParameter("samAResponse");
            
            //Now saml artifact response has been recieved from the AS .. So Saml request is to be sent ..
            
            String samlRequest="<samlp:AuthnRequest\n"
                                    +"xmlns:samlp=\"urn:oasis:names:xtc:SAML:2.0:protocol\"\n"
                                    +"xmlns:saml=\"urn:oasis:names:tc:SAML:2.0:assertion\"\n"
                                    +"ID=\"identifier_1\"\n"
                                    +"Version=\"2.0\"\n"
                                    +"IssueInstant=\"2004-12-05T09:21:59\"\n"
                                    +"AssertionConsumerServiceIndex=\"0\">\n"
                                    +"<saml:Issuer>"+url+"</saml:Issuer>\n"
                                    +"<samlp:NameIDPolicy\n"
                                    +"AllowCreate=\"true\"\n"
                                    +"Format=\"urn:oasis:names:tc:SAML:2.0:nameid-format:transient\"/>\n"
                                    +"</samlp:AuthnRequest>\n";
            out.println(url);
            //out.println(samlAResponse);
            
            String destination = "http://14.139.152.10:8080/AS/AS/login.jsp"+"?SAMLRequest="+samlRequest;
            
            response.sendRedirect(destination);
            
            
            

        }
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
