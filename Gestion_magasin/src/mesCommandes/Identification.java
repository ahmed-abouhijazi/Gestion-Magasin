package mesCommandes;
import javax.servlet.http.*;
public class Identification {
 static String chercheNom (Cookie [] cookies) {
 // cherche dans les cookies la valeur de celui qui se nomme "nom"
// retourne la valeur de ce nom au lieu de inconnu
	 String value = "inconnue";
	 if (cookies != null) {
         for (Cookie cookie : cookies) {
             if (cookie.getName().equals("nom")) {
            	 value = cookie.getValue();            	 
             }
         }
     }
	 return value;
}


}