
package demo;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/calculate")
public class MarksServlet extends HttpServlet {

    private final GradeCalculator calculator =
            new GradeCalculator();

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {
            int a = Integer.parseInt(request.getParameter("m1"));
            int b = Integer.parseInt(request.getParameter("m2"));
            int c = Integer.parseInt(request.getParameter("m3"));

            int total = calculator.total(a, b, c);
            double average = calculator.average(a, b, c);
            String grade = calculator.grade(a, b, c);

            request.setAttribute("total", total);
            request.setAttribute("average", average);
            request.setAttribute("grade", grade);

        } catch (RuntimeException ex) {
            request.setAttribute(
                "error",
                "Enter valid whole-number marks from 0 to 100."
            );
        }

        request.getRequestDispatcher("/marks-result.jsp")
               .forward(request, response);
    }
}
