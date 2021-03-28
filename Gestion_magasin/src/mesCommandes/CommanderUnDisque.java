package mesCommandes;
import java.io.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class CommanderUnDisque extends HttpServlet {
 public void doGet(HttpServletRequest request,
 HttpServletResponse response)
throws IOException, ServletException
{ String nom = null;
int nbreProduit = 0;
Cookie[] cookies = request.getCookies();
nom = Identification.chercheNom(cookies);
 response.setContentType("text/html");
 PrintWriter out = response.getWriter();
 out.println("<html>");
 out.println("<body>");
 out.println("<head>");
 out.println("<title> votre commande </title>");
 out.println("</head>");
 out.println("<body bgcolor=\"white\">");
 out.println("<h3>" + "Bonjour "+ nom + " voici votre commande" + "</h3>");
// affichage de tous les disques présents dans le panier (éléments de la session)
 Enumeration<String> Disques = request.getSession().getAttributeNames();
 while (Disques.hasMoreElements()) {
     String Disque = (String) Disques.nextElement();
     out.println(Disque+" : "+request.getSession().getAttribute(Disque));
 }
// si parametre ordre == ajouter affichage du disque à ajouter au panier
// ajout du nouveau disque dans le panier
out.println("<A HREF=achat> Vous pouvez commandez un autre disque </A><br> ");
 out.println("<A HREF=enregistre> Vous pouvez enregistrer votre commande </A><br> ");
 out.println("</body>");
 out.println("</html>");
}
public void doPost(HttpServletRequest request,
 HttpServletResponse response)
throws IOException, ServletException
 {
 doGet(request, response);
 }
}