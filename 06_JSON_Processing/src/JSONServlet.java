import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(urlPatterns = "/json")
public class JSONServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("id","C001");
        objectBuilder.add("name","Nimal");

        JsonObject build = objectBuilder.build();

        PrintWriter writer = resp.getWriter();
        writer.print(build);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      /*  ServletInputStream inputStream = req.getInputStream();

        int read;
        while ((read = inputStream.read())!= -1){
            System.out.print((char)read);
        }*/

        JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();
        jsonObject.getString("id");
        jsonObject.getString("name");
    }
}
