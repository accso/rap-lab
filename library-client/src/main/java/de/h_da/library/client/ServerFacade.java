package de.h_da.library.client;

import de.h_da.library.component1.usecase.UseCase1Remote;
import org.jboss.ejb.client.ContextSelector;
import org.jboss.ejb.client.EJBClientConfiguration;
import org.jboss.ejb.client.PropertiesBasedEJBClientConfiguration;
import org.jboss.ejb.client.EJBClientContext;
import org.jboss.ejb.client.remoting.ConfigBasedEJBClientContextSelector;

import javax.naming.*;
import java.util.Hashtable;
import java.util.Properties;

public class ServerFacade {

    private Context context = null;

    private static final ServerFacade _instance = new ServerFacade();

    private ServerFacade() {
        createInitialContext();
    }

    private void createInitialContext() {

        try {
            final Hashtable jndiProperties = new Hashtable();
            jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
            context = new InitialContext(jndiProperties);
            traverseJndiNode("/", context);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static void traverseJndiNode(String nodeName, Context context)  {
        try {
            NamingEnumeration<NameClassPair> list = context.list(nodeName);
            while (list.hasMore()){
                String childName = nodeName + "/" + list.next().getName();
                System.out.println(childName);
                traverseJndiNode(childName, context);
            }
        } catch (NamingException ex) {

        }
    }

    public static ServerFacade getInstance() {
        return _instance;
    }

    public<T> T lookup(String ejbName, Class<T> RemoteInterface) {

        try {

            return (T) context.lookup("//library-server-1.0/"+ejbName+"!"+RemoteInterface.getName());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void closeContext() throws NamingException {
        if (context != null) {
            context.close();
        }
    }
}
