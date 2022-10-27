import java.util.ArrayList;
import java.io.IOException;
import java.net.URI;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    int num = 0;
    ArrayList<String> query = new ArrayList<String>();
    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("Missing query");
        } 
        
        else if(url.getPath().contains("/add")){
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/add")) {
                String[] parameters = url.getQuery().split("=");
                query.add(parameters[1]);
                return ("added successfully");
            }
            
        }
        else if (url.getPath().contains("/search")){
            String[] parameters = url.getQuery().split("=");
            ArrayList<String> query1 = new ArrayList<String>();
            for(String i: query){
                // System.out.println(i);
                // boolean found = query.stream().anyMatch(s -> s.contains(parameters[1]));
                if(i.contains(parameters[1])){
                    // System.out.println(i);
                    query1.add(i);
                    //return String.format(i);
                }
            }
            // query1.toString();
            String delimiter = " ";
            return String.join(delimiter, query1.toString());
        }
        return "404 Not Found!";
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
    
        
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
