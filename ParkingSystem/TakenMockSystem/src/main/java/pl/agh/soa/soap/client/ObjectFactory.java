
package pl.agh.soa.soap.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the pl.agh.soa.soap.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ReleaseParkingSlot_QNAME = new QName("http://ws.soap.soa.agh.pl/", "releaseParkingSlot");
    private final static QName _ReleaseParkingSlotResponse_QNAME = new QName("http://ws.soap.soa.agh.pl/", "releaseParkingSlotResponse");
    private final static QName _TakeParkingSlot_QNAME = new QName("http://ws.soap.soa.agh.pl/", "takeParkingSlot");
    private final static QName _TakeParkingSlotResponse_QNAME = new QName("http://ws.soap.soa.agh.pl/", "takeParkingSlotResponse");
    private final static QName _SlotOccupiedException_QNAME = new QName("http://ws.soap.soa.agh.pl/", "SlotOccupiedException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: pl.agh.soa.soap.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ReleaseParkingSlot }
     * 
     */
    public ReleaseParkingSlot createReleaseParkingSlot() {
        return new ReleaseParkingSlot();
    }

    /**
     * Create an instance of {@link ReleaseParkingSlotResponse }
     * 
     */
    public ReleaseParkingSlotResponse createReleaseParkingSlotResponse() {
        return new ReleaseParkingSlotResponse();
    }

    /**
     * Create an instance of {@link TakeParkingSlot }
     * 
     */
    public TakeParkingSlot createTakeParkingSlot() {
        return new TakeParkingSlot();
    }

    /**
     * Create an instance of {@link TakeParkingSlotResponse }
     * 
     */
    public TakeParkingSlotResponse createTakeParkingSlotResponse() {
        return new TakeParkingSlotResponse();
    }

    /**
     * Create an instance of {@link SlotOccupiedException }
     * 
     */
    public SlotOccupiedException createSlotOccupiedException() {
        return new SlotOccupiedException();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReleaseParkingSlot }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ReleaseParkingSlot }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.soap.soa.agh.pl/", name = "releaseParkingSlot")
    public JAXBElement<ReleaseParkingSlot> createReleaseParkingSlot(ReleaseParkingSlot value) {
        return new JAXBElement<ReleaseParkingSlot>(_ReleaseParkingSlot_QNAME, ReleaseParkingSlot.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReleaseParkingSlotResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ReleaseParkingSlotResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.soap.soa.agh.pl/", name = "releaseParkingSlotResponse")
    public JAXBElement<ReleaseParkingSlotResponse> createReleaseParkingSlotResponse(ReleaseParkingSlotResponse value) {
        return new JAXBElement<ReleaseParkingSlotResponse>(_ReleaseParkingSlotResponse_QNAME, ReleaseParkingSlotResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TakeParkingSlot }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TakeParkingSlot }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.soap.soa.agh.pl/", name = "takeParkingSlot")
    public JAXBElement<TakeParkingSlot> createTakeParkingSlot(TakeParkingSlot value) {
        return new JAXBElement<TakeParkingSlot>(_TakeParkingSlot_QNAME, TakeParkingSlot.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TakeParkingSlotResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TakeParkingSlotResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.soap.soa.agh.pl/", name = "takeParkingSlotResponse")
    public JAXBElement<TakeParkingSlotResponse> createTakeParkingSlotResponse(TakeParkingSlotResponse value) {
        return new JAXBElement<TakeParkingSlotResponse>(_TakeParkingSlotResponse_QNAME, TakeParkingSlotResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SlotOccupiedException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SlotOccupiedException }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.soap.soa.agh.pl/", name = "SlotOccupiedException")
    public JAXBElement<SlotOccupiedException> createSlotOccupiedException(SlotOccupiedException value) {
        return new JAXBElement<SlotOccupiedException>(_SlotOccupiedException_QNAME, SlotOccupiedException.class, null, value);
    }

}
