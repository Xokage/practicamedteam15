package test.java.buscarHotel;

import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

public class TestClient {
		 
	    private final String uri;
	 
	    public TestClient(String uri) {
	        this.uri = uri;
	    }
	 
	    public WebResource resource(String todo) {
	        return TestClient.resource(uri).path("/todo/"+todo);
	    }
	 
	    public void removeTodo(String todoToRemove) {
	            resource(todoToRemove).delete();
	    }
}
