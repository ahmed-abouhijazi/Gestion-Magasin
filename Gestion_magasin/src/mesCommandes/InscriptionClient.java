    package mesCommandes;
    import java.io.IOException;
    import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InscriptionClient
 */
//@WebServlet("/InscriptionClient")
    public class InscriptionClient extends HttpServlet {
    private static final long serialVersionUID = 1L;
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     String nomRecu=null, motPasseRecu=null;
     String nomCookie=null, motPasseCookie=null;
     // initialisation cookies et paramètres
     response.setContentType("text/html");
     PrintWriter out = response.getWriter();

     if (nomCookie==null && nomRecu==null){
     // Cas 1 : cas où il n'y a ni de cookies ni de parametres
     out.println("<html>");
     out.println("<body>");
     out.println("<head>");
     out.println("<title> inscription d'un client </title>");
     out.println("</head>");
     out.println("<body bgcolor='white' >");
     out.println( nomRecu +" | "+ motPasseRecu +" | "+ nomCookie +" | "+ motPasseCookie );
     out.println("<h3>" + "Bonjour, vous devez vous inscrire " + "</h3>");
     out.println("<h3>" + "Attention mettre nom et le mot de passe avec plus de 3 caracteres" + "</h3>"); 
     out.print(" <form action='sinscrire' method='GET' > ");
     out.println("nom");
     out.println("<input type='text' size='20' name='nom' >");
     out.println("<br>");
     out.println("mot de passe");
     out.println("<input type='password' size='20' name='motdepasse'> <br>");
     out.println("<input type='submit' value='inscription'>");
     out.println("</form>");
     out.println("</body>");
     out.println("</html>");
     } else if (nomCookie==null && nomRecu!=null){
    	// Cas 2 : cas où il n'y a pas de cookies mais les paramètres nom et mot de passes sont présents :

    	 Cookie NomCookie = new Cookie(request.getParameter("nom"),request.getParameter("motdepasse"));
    	 
    	 // Add both the cookies in the response header.
    	 response.addCookie(NomCookie);


     }
     else if (identique(nomRecu,nomCookie) && identique(motPasseRecu,motPasseCookie))
     {
     // Cas 4 : cas où le nom et le mot passe sont correctes, appel à la servlet achat
    	 response.setContentType("text/html");
    	 PrintWriter out1 = response.getWriter();
    	 out1.println("<html>");
    	 out1.println("<body>");
    	 out1.println("<head>");
    	 out1.println("<title> Acheter </title>");
    	 out1.println("</head>");
    	 out1.println("<body bgcolor=\"white\">");
    	out1.println("<A HREF=achat> Vous pouvez commandez un autre disque </A><br> ");
    	 out1.println("</body>");
    	 out1.println("</html>");
    	 
     }
     else {
     // Cas 3 : les cookies sont présents demande de s'identifier
    	 out.println("<html>");
         out.println("<body>");
         out.println("<head>");
         out.println("<title> inscription d'un client </title>");
         out.println("</head>");
         out.println("<body bgcolor='white' >");
         out.println( nomRecu +" | "+ motPasseRecu  );
         out.println("<h3>" + "Bonjour, vous devez vous inscrire " + "</h3>");
         out.println("<h3>" + "Attention mettre nom et le mot de passe avec plus de 3 caracteres" + "</h3>"); 
         out.print(" <form action='sinscrire' method='GET' > ");
         out.println("nom");
         out.println("<input type='text' size='20' name='nom' >");
         out.println("<br>");
         out.println("mot de passe");
         out.println("<input type='password' size='20' name='motdepasse'> <br>");
         out.println("<input type='submit' value='inscription'>");
         out.println("</form>");
         out.println("</body>");
         out.println("</html>");
     }
     }

     public void doPost(HttpServletRequest request,
     HttpServletResponse response)
     throws IOException, ServletException
     {
     doGet(request, response);
     }
     boolean identique (String recu, String cookie) {
     return ((recu != null) && (recu.length() >3) && (cookie != null) && (recu.equals(cookie) ));
     }
    }


