package org.alex.springapp.config.jwt;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.alex.springapp.config.ApplicationUserDetailsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

/**
 *
 * @author zamdirit
 */
@Component
public class JwtFilter extends GenericFilterBean {
    
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private ApplicationUserDetailsService userDetailsService;
    
    private static final Logger LOG = LogManager.getLogger(JwtFilter.class);
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc) throws IOException, ServletException {
    }
    
}
