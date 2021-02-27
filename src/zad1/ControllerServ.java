package zad1;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(urlPatterns = "/control")
public class ControllerServ extends HttpServlet {

	  private ServletContext context;
	  private Command command;            // obiekt klasy dzialania (wykonawczej)
	  private String presentationServ;    // nazwa serwlet prezentacji
	  private String getParamsServ;       // mazwa serwletu pobierania parametrów

	  public void init() {

	    context = getServletContext();

	    presentationServ = context.getInitParameter("presentationServ");
	    getParamsServ = context.getInitParameter("getParamsServ");
	    String commandClassName = context.getInitParameter("commandClassName");
	    String dbName = context.getInitParameter("dbName");

	    // Załadowanie klasy Command i utworzenie jej egzemplarza
	    // który będzie wykonywał pracę
	    try {
	      Class commandClass = Class.forName(commandClassName);
	      command = (Command) commandClass.newInstance();
	      // ustalamy, na jakiej bazie ma działać Command i inicjujemy obiekt
	      command.setParameter("dbName", dbName);
	      command.init();
	    } catch (Exception exc) {
	       // throw new Exception("Couldn't find or instantiate " +
	                                      //commandClassName);
	    }
	  }
}
