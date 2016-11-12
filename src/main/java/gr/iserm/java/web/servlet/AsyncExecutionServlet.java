package gr.iserm.java.web.servlet;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static gr.iserm.java.web.util.Utils.sleep;

@WebServlet(value = "/async", asyncSupported = true)
public class AsyncExecutionServlet extends HttpServlet {

    @Resource
    private ManagedExecutorService managedExecutorService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        long start_tstamp = System.currentTimeMillis();
        System.out.println("async servlet request received by thread-"+Thread.currentThread().getId());

        executeAsynchronously(req.startAsync(), start_tstamp);

        long end_tstamp = System.currentTimeMillis();
        System.out.println("servlet execution took ["+(end_tstamp-start_tstamp)+"]ms");
    }

    private void executeAsynchronously(AsyncContext asyncContext, long start_tstamp) {
        managedExecutorService.submit(() -> {
            try {
                sleep(10);
                asyncContext.getResponse().getWriter().write("Hello world!");
                long now = System.currentTimeMillis();
                System.out.println("async servlet request processed by thread-"+Thread.currentThread().getId()+" in ["+(now-start_tstamp)+"]ms");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                asyncContext.complete();
            }
        });
    }

}
