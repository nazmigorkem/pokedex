package tech.obss.filter;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Component
public class RequestInFilter extends CommonsRequestLoggingFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestInFilter.class);

    @Override
    protected boolean shouldLog(HttpServletRequest request) {
        return request.getRequestURI().contains("/users");
    }

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        LOGGER.info("Request filter started on path {}", request.getRequestURI());
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
        LOGGER.info("Request filter finished on path {}", request.getRequestURI());
    }
}
