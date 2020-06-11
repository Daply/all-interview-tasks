
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

public class Server extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
    ArrayList classes = new ArrayList();
	
    public String findClassInDb(String keyword, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
    	DBConnect db = new DBConnect();
    	String foundClass = db.findClass(request, response, keyword);
    	if(!foundClass.equals(""))
    	foundClass = foundClass.substring(13, foundClass.length());
    	return foundClass;
    	}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        if(br != null){
            json = br.readLine();
        }
        ObjectMapper mapper = new ObjectMapper();
        Keyword keyword = mapper.readValue(json, Keyword.class);
        response.setContentType("application/json");            
        String res = findClassInDb(keyword.text, request, response); 
        ClassName classname = find(res);
        if(classname == null){
	        classname = new ClassName(res); 
	        classes.add(classname);
        }
        else
        	classname.setState(!classname.getState());
        	
        mapper.writeValue(response.getOutputStream(), classname);
    }
    
    protected ClassName find(String classname){
    	Iterator it = classes.iterator();
    	while(it.hasNext()){
    		ClassName cn = (ClassName) it.next();
    		if(cn.getClassName().equals(classname))
    			return cn;
    	}
    	return null;
    }
    
}