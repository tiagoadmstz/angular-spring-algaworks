package io.github.tiagoadmstz.algamoney.api.security.filters;

import org.apache.catalina.util.ParameterMap;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RefreshTokenPostProcessorFilter extends FilterAdapter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        if ("/oauth/token".equalsIgnoreCase(req.getRequestURI())
                && "refresh_token".equals(req.getParameter("grant_type"))
                && req.getCookies() != null) {
            String refreshToken = Arrays.asList(req.getCookies()).stream()
                    .filter(cookie -> "refreshToken".equals(cookie.getName()))
                    .map(Cookie::getValue)
                    .findFirst()
                    .orElseGet(String::new);
            req = new MyServletRequestMapperWrapper(req, refreshToken);
        }

        chain.doFilter(req, response);
    }

    static class MyServletRequestMapperWrapper extends HttpServletRequestWrapper {

        private String refreshToken;

        /**
         * Constructs a request object wrapping the given request.
         *
         * @param request The request to wrap
         * @throws IllegalArgumentException if the request is null
         */
        public MyServletRequestMapperWrapper(HttpServletRequest request, String refreshToken) {
            super(request);
            this.refreshToken = refreshToken;
        }

        @Override
        public Map<String, String[]> getParameterMap() {
            ParameterMap<String, String[]> parameterMap = new ParameterMap(getRequest().getParameterMap());
            parameterMap.put("refresh_token", new String[]{refreshToken});
            return parameterMap;
        }

    }

}
