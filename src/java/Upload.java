
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Likhith
 */
@WebServlet(urlPatterns = {"/Upload"})
@MultipartConfig
public class Upload extends HttpServlet {

//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Cookie c[] = request.getCookies();
//        for (Cookie j : c) {
//            j.setMaxAge(0);
//        }
//        doPost(request, response);
//    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        task t = new task(request, response);
        t.start();
        System.out.println("doPost ----> " + request.getContentLength());
        try {
            t.join();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // sleep method for 2 sec set it 11 sec to upload 1 gb file 
//        task t = new task(request, response);
//        t.start();
//        System.out.println("doPost ----> " + request.getContentLength());
//        try {
//            Thread.sleep(2000);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//       
        // dt below code
//  try {
//            Part image = request.getPart("img");
//            String imageName = image.getSubmittedFileName();
//            String uploadPath = "/home/sys2031/NetBeansProjects/task12/Images/" + imageName;
//            System.out.println(uploadPath);
//            Dao d = new Dao();
//
//            d.uplink(image, uploadPath);
//            response.sendRedirect(request.getContextPath() + "/EmployeeStatus.jsp");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    }

}

class task extends Thread {

    HttpServletRequest request;
    HttpServletResponse response;

    task(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public void run() {
        System.out.println("task --> " + request.getContentLength());
        try {
            Part image = request.getPart("img");
            String imageName = image.getSubmittedFileName();
            String uploadPath = "/home/sys2031/NetBeansProjects/task12/Images/" + imageName;
            System.out.println(uploadPath);
            Dao d = new Dao();

            d.uplink(image, uploadPath);
            response.sendRedirect(request.getContextPath() + "/EmployeeStatus.jsp");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
