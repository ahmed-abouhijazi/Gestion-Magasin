package mesCommandes;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class FiltrerechercheNom implements Filter {
private FilterConfig filterConfig = null;
 public void init(FilterConfig filterConfig) throws ServletException {
 this.filterConfig = filterConfig;
 }
public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
ServletException {
String nom = null;
HttpServletRequest hrequest = (HttpServletRequest) request;
HttpServletResponse hresponse = (HttpServletResponse) response;
Cookie[] cookies = hrequest.getCookies();
nom = Identification.chercheNom(cookies);
if(nom!="inconnue") {
	PrintWriter out = hresponse.getWriter();
	out.println("<html>");
	out.println("<body>");
	out.println("<head>");
	out.println("<title> Researche resutl </title>");
	out.println("</head>");
	out.println("<body bgcolor=\"white\">");
	out.println("<p>"+nom+" exist<br> ");
	out.println("</body>");
	out.println("</html>");
	}
}
public void destroy() {
this.filterConfig = null;
}
}