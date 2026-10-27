package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Workshop;
import mk.ukim.finki.wp.lab.service.WorkshopService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "workshop-list-servlet", urlPatterns = {"", "/home"})
public class WorkshopListServlet extends HttpServlet {

    private final WorkshopService workshopService;
    private final SpringTemplateEngine springTemplateEngine;

    public WorkshopListServlet(WorkshopService workshopService, SpringTemplateEngine springTemplateEngine) {
        this.workshopService = workshopService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String text = req.getParameter("text");
        String maxPriceStr = req.getParameter("maxPrice");

        if(text != null && text.isBlank()){
            text = null;
        }

        Double maxPrice = null;
        if(maxPriceStr != null && !maxPriceStr.isBlank()){
            try{
                maxPrice = Double.parseDouble(maxPriceStr);
            }catch (NumberFormatException ignored){
            }
        }

        List<Workshop> workshops;
        if(text != null || maxPrice != null){
            workshops = workshopService.search(text,maxPrice);
        }else{
            workshops = workshopService.listAll();
        }

        resp.setContentType("text/html; charset=UTF-8");
        var webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req,resp);
        WebContext context = new WebContext(webExchange);

        context.setVariable("workshops", workshops);
        context.setVariable("errorMessage", req.getParameter("errorMessage"));

        springTemplateEngine.process("listWorkshops.html", context, resp.getWriter());
    }
}

