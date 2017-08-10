package ua.home.myphotos.web.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vov on 10.08.2017.
 */
@WebFilter(filterName = "CurrentRequestFilter", asyncSupported = true)
public class CurrentRequestFilter extends AbstractFilter {
    public static final String CURRENT_REQUEST_URL = "currentRequestUrl";
    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        req.setAttribute(CURRENT_REQUEST_URL, req.getRequestURI());
        filterChain.doFilter(req, resp);

    }
}
