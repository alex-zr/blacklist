package com.example.sweater.service;

import com.example.sweater.controller.GreetingController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static java.lang.System.out;


@WebFilter(filterName = "Filter")
public class Filter implements javax.servlet.Filter {

    private ReadIpAddressFromFile readIpAddressFromFile;
    private GreetingController greetingController;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        resp.setContentType(greetingController.greeting());
        List<String> ipAddress = readIpAddressFromFile.getIpAddress();
        String userIp = req.getRemoteAddr();
        HttpServletResponse httpResponse = null;
        if (resp instanceof HttpServletResponse){
            httpResponse = (HttpServletResponse) resp;
        }
        if (ipAddress.contains(userIp)) {
            assert httpResponse != null;
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
            out.println("Access disallowed" + req.getRemoteAddr());
        } else {
            chain.doFilter(req, resp);
            greetingController.greeting();

        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
