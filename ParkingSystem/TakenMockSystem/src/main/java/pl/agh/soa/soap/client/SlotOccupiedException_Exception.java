
package pl.agh.soa.soap.client;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.0
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "SlotOccupiedException", targetNamespace = "http://ws.soap.soa.agh.pl/")
public class SlotOccupiedException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private SlotOccupiedException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public SlotOccupiedException_Exception(String message, SlotOccupiedException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public SlotOccupiedException_Exception(String message, SlotOccupiedException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: pl.agh.soa.soap.client.SlotOccupiedException
     */
    public SlotOccupiedException getFaultInfo() {
        return faultInfo;
    }

}
