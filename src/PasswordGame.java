import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

@WebServlet("/generatePassword")
public class PasswordGeneratorServlet extends HttpServlet {
    private static final List<String> MONTHS = Arrays.asList("January", "February", "March", "April", "May", "June",
                                                             "July", "August", "September", "October", "November", "December");
    private static final List<String> VALID_COMPANIES = Arrays.asList("pepsi", "starbucks", "shell");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = generatePassword();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Generated Password: " + password + "</h2>");
        out.println("</body></html>");
    }

    private String generatePassword() {
        return "A1b!FebruaryXVPepsi12345"; // Example password
    }
}
