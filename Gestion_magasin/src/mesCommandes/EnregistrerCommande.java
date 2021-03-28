package mesCommandes;
 import java.io.*;

 import java.util.*;
 import javax.servlet.*;
 import javax.servlet.http.*;
 import java.sql.*;
public class EnregistrerCommande extends HttpServlet {
 Connection connexion=null;
 Statement stmt=null;
 PreparedStatement pstmt=null;
public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
 { String nom = null;
 int nbreProduit = 0;
 Cookie[] cookies = request.getCookies();
 boolean connu = false;
 nom = Identification. chercheNom (cookies);
 OuvreBase();
 AjouteNomBase(nom);

 response.setContentType("text/html");
 PrintWriter out = response.getWriter();
 out.println("<html>");
 out.println("<body>");
 out.println("<head>");
 out.println("<title> votre commande </title>");
 out.println("</head>");
 out.println("<body bgcolor=\"white\">");

 out.println("<h3>" + "Bonjour " + nom + " voici ta nouvelle commande" + "</h3>");
 HttpSession session = request.getSession();
 Enumeration names = session.getAttributeNames();
 while (names.hasMoreElements()) {
 nbreProduit++;
 String name = (String) names.nextElement();
 String value = session.getAttribute(name).toString();
 out.println(name + " = " + value + "<br>");
 }
 AjouteCommandeBase(nom,session);
 out.println("<h3>" + "et voici " + nom + " ta commande complete" + "</h3>");
 MontreCommandeBase(nom, out);
 out.println("<A HREF=mesCommandes.VidePanier> Vous pouvez commandez un autre disque </A><br> ");
 out.println("</body>");
 out.println("</html>");
 }
 protected void OuvreBase() {
 try {
 Class.forName("org.gjt.mm.mysql.Driver").newInstance();
 connexion = DriverManager.getConnection("jdbc:mysql://localhost/magasin","root","");
 connexion.setAutoCommit(true);
 stmt = connexion.createStatement();
 }
 catch (Exception E) {
 log(" -------- probeme " + E.getClass().getName() );
 E.printStackTrace();
 }
 }
 protected void fermeBase() {
 try {
 stmt.close();
 connexion.close();
 }
 catch (Exception E) {
 log(" -------- probeme " + E.getClass().getName() );
 E.printStackTrace();
 }
 }

 protected void AjouteNomBase(String nom) {
 try {
 ResultSet rset = null;
 pstmt= connexion.prepareStatement("select numero from personnel where nom=?");
 pstmt.setString(1,nom);
 rset=pstmt.executeQuery();
 if (!rset.next())
 stmt.executeUpdate("INSERT INTO personnel VALUES ('' ,'" + nom + "' )" );
 }
 catch (Exception E) {
 log(" - probeme " + E.getClass().getName() );
 E.printStackTrace();
 }
 }
 protected void AjouteCommandeBase(String nom, HttpSession session ) {
 // ajoute le contenu du panier dans la base
	 try {
		 ResultSet rset = null;
		 pstmt= connexion.prepareStatement("select numero from commande where nom=?");
		 pstmt.setString(1,nom);
		 rset=pstmt.executeQuery();
		 if (!rset.next())
		 stmt.executeUpdate("INSERT INTO commande VALUES ('' ,'" + session.getAttribute(nom) + "' )" );
		 }
		 catch (Exception E) {
		 log(" - probeme " + E.getClass().getName() );
		 E.printStackTrace();
		 }
 }
 protected void MontreCommandeBase(String nom, PrintWriter out) {
 // affiche les produits pr�sents dans la base
 }
 public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
 {
 doGet(request, response);
 }
 }