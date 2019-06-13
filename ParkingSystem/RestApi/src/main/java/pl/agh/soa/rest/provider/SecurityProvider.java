package pl.agh.soa.rest.provider;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.core.Headers;
import org.jboss.resteasy.core.ResourceMethodInvoker;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.util.Base64;
import pl.agh.soa.dto.UserData;
import pl.agh.soa.ejb.services.remote.AccountManagerRemote;

@Provider
public class SecurityProvider implements javax.ws.rs.container.ContainerRequestFilter
{

    @EJB(lookup = "java:global/EjbAccountImpl-1.0/AccountManagerBean!pl.agh.soa.ejb.services.remote.AccountManagerRemote")
    private AccountManagerRemote accountManager;

    @Override
    public void filter(ContainerRequestContext requestContext)
    {
        ResourceMethodInvoker methodInvoker = (ResourceMethodInvoker) requestContext.getProperty("org.jboss.resteasy.core.ResourceMethodInvoker");
        Method method = methodInvoker.getMethod();

        if(!method.isAnnotationPresent(PermitAll.class))
        {
            // get headers
            final MultivaluedMap<String, String> headers = requestContext.getHeaders();

            final List<String> authorization = headers.get("Authorization");

            // no authorization -> access denied
            if(authorization == null || authorization.isEmpty())
            {
                requestContext.abortWith(new ServerResponse("Access denied", 401, new Headers<Object>()));
                return;
            }

            // encoded username and password
            final String encodedUserPassword = authorization.get(0).replaceFirst("Basic" + " ", "");

            // decode username and password
            String usernamePassword = null;
            try
            {
                usernamePassword = new String(Base64.decode(encodedUserPassword));
            }
            catch (IOException e)
            {
                requestContext.abortWith(new ServerResponse("Server error", 500, new Headers<Object>()));
                return;
            }

            final StringTokenizer tokenizer = new StringTokenizer(usernamePassword, ":");
            final String username = tokenizer.nextToken();
            final String password = tokenizer.nextToken();

            if(method.isAnnotationPresent(RolesAllowed.class))
            {
                RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
                Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));

                //Is user valid?
                if( ! isUserAllowed(username, password, rolesSet))
                {
                    requestContext.abortWith(new ServerResponse("Access denied", 401, new Headers<Object>()));
                }
            }
        }
    }

    private boolean isUserAllowed(final String username, final String password, final Set<String> rolesSet)
    {
        boolean isAllowed = false;
        UserData user = accountManager.getUserByUsernamePassword(username, password);
        String userRole = user.getRole();

        // verify user role
        if(rolesSet.contains(userRole))
        {
            isAllowed = true;
        }
        return isAllowed;
    }
}
