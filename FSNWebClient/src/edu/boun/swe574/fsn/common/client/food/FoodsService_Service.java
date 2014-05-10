
package edu.boun.swe574.fsn.common.client.food;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.1-b03-
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "FoodsService", targetNamespace = "http://ws.backend.fsn.swe574.boun.edu/", wsdlLocation = "http://localhost:8080/FSN_SERVER/fsnws_food?wsdl")
public class FoodsService_Service
    extends Service
{

    private final static URL FOODSSERVICE_WSDL_LOCATION;

    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/FSN_SERVER/fsnws_food?wsdl");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        FOODSSERVICE_WSDL_LOCATION = url;
    }

    public FoodsService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public FoodsService_Service() {
        super(FOODSSERVICE_WSDL_LOCATION, new QName("http://ws.backend.fsn.swe574.boun.edu/", "FoodsService"));
    }

    /**
     * 
     * @return
     *     returns FoodsService
     */
    @WebEndpoint(name = "FoodsServicePort")
    public FoodsService getFoodsServicePort() {
        return (FoodsService)super.getPort(new QName("http://ws.backend.fsn.swe574.boun.edu/", "FoodsServicePort"), FoodsService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns FoodsService
     */
    @WebEndpoint(name = "FoodsServicePort")
    public FoodsService getFoodsServicePort(WebServiceFeature... features) {
        return (FoodsService)super.getPort(new QName("http://ws.backend.fsn.swe574.boun.edu/", "FoodsServicePort"), FoodsService.class, features);
    }

}