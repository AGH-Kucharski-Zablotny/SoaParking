
package pl.agh.soa.soap.client;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.0
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "SlotResourceImplService", targetNamespace = "http://impl.ws.soap.soa.agh.pl/", wsdlLocation = "file:/C:/Users/Piotr/Desktop/SOA/SoaParking/ParkingSystem/TakenMockSystem/src/main/java/pl/agh/soa/soap/client/SlotResourceImpl.wsdl")
public class SlotResourceImplService
    extends Service
{

    private final static URL SLOTRESOURCEIMPLSERVICE_WSDL_LOCATION;
    private final static WebServiceException SLOTRESOURCEIMPLSERVICE_EXCEPTION;
    private final static QName SLOTRESOURCEIMPLSERVICE_QNAME = new QName("http://impl.ws.soap.soa.agh.pl/", "SlotResourceImplService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/C:/Users/Piotr/Desktop/SOA/SoaParking/ParkingSystem/TakenMockSystem/src/main/java/pl/agh/soa/soap/client/SlotResourceImpl.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        SLOTRESOURCEIMPLSERVICE_WSDL_LOCATION = url;
        SLOTRESOURCEIMPLSERVICE_EXCEPTION = e;
    }

    public SlotResourceImplService() {
        super(__getWsdlLocation(), SLOTRESOURCEIMPLSERVICE_QNAME);
    }

    public SlotResourceImplService(WebServiceFeature... features) {
        super(__getWsdlLocation(), SLOTRESOURCEIMPLSERVICE_QNAME, features);
    }

    public SlotResourceImplService(URL wsdlLocation) {
        super(wsdlLocation, SLOTRESOURCEIMPLSERVICE_QNAME);
    }

    public SlotResourceImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SLOTRESOURCEIMPLSERVICE_QNAME, features);
    }

    public SlotResourceImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SlotResourceImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns SlotResource
     */
    @WebEndpoint(name = "SlotResourceImplPort")
    public SlotResource getSlotResourceImplPort() {
        return super.getPort(new QName("http://impl.ws.soap.soa.agh.pl/", "SlotResourceImplPort"), SlotResource.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SlotResource
     */
    @WebEndpoint(name = "SlotResourceImplPort")
    public SlotResource getSlotResourceImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://impl.ws.soap.soa.agh.pl/", "SlotResourceImplPort"), SlotResource.class, features);
    }

    private static URL __getWsdlLocation() {
        if (SLOTRESOURCEIMPLSERVICE_EXCEPTION!= null) {
            throw SLOTRESOURCEIMPLSERVICE_EXCEPTION;
        }
        return SLOTRESOURCEIMPLSERVICE_WSDL_LOCATION;
    }

}
