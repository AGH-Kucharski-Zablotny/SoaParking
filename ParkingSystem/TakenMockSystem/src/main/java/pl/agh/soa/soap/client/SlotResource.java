
package pl.agh.soa.soap.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.0
 * Generated source version: 2.2
 * 
 */
@WebService(name = "SlotResource", targetNamespace = "http://ws.soap.soa.agh.pl/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface SlotResource {


    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "releaseParkingSlot", targetNamespace = "http://ws.soap.soa.agh.pl/", className = "pl.agh.soa.soap.client.ReleaseParkingSlot")
    @ResponseWrapper(localName = "releaseParkingSlotResponse", targetNamespace = "http://ws.soap.soa.agh.pl/", className = "pl.agh.soa.soap.client.ReleaseParkingSlotResponse")
    public void releaseParkingSlot(
        @WebParam(name = "arg0", targetNamespace = "")
        Integer arg0);

    /**
     * 
     * @param arg1
     * @param arg0
     * @throws SlotOccupiedException_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "takeParkingSlot", targetNamespace = "http://ws.soap.soa.agh.pl/", className = "pl.agh.soa.soap.client.TakeParkingSlot")
    @ResponseWrapper(localName = "takeParkingSlotResponse", targetNamespace = "http://ws.soap.soa.agh.pl/", className = "pl.agh.soa.soap.client.TakeParkingSlotResponse")
    public void takeParkingSlot(
        @WebParam(name = "arg0", targetNamespace = "")
        Integer arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1)
        throws SlotOccupiedException_Exception
    ;

}
