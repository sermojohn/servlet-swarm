package gr.iserm.java.web.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/hello", asyncSupported = true)
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        AsyncContext asyncContext = req.startAsync();
        System.out.println("async servlet request received and processed by thread-"+Thread.currentThread().getId());
        resp.getWriter().write("Hello world!");
        asyncContext.complete();
    }

}
