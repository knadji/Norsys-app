package fr.norsys.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RestrictionFilter implements Filter {

    private static final String ATT_SESSION_UTILISATEUR = "sessionUtilisateur";
    private static final String VUE_PUBLIC              = "/creationClient";

    @Override
    public void doFilter( final ServletRequest req, final ServletResponse resp, final FilterChain chain )
            throws IOException,
            ServletException {

        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) resp;

        final HttpSession session = request.getSession();

        //
        if ( session.getAttribute( ATT_SESSION_UTILISATEUR ) == null ) {
            // response.sendRedirect( request.getContextPath() + VUE_PUBLIC );
            request.getRequestDispatcher( VUE_PUBLIC ).forward( request, response );
        } else {
            chain.doFilter( request, response );
        }
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void init( final FilterConfig arg0 ) throws ServletException {
        // TODO Auto-generated method stub

    }

}
