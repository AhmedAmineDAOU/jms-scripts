package src;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.*;
import java.io.IOException;
import java.io.PrintStream;
import javax.naming.*;
import javax.jms.*;
import java.lang.System;
import java.lang.*;
import javax.jms.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.jws.WebService;

@WebService(targetNamespace = "http://localhost:8080/")
public interface IEmetteur {


	@WebMethod
	public String emettre(String message) throws ServletException, IOException;


}
