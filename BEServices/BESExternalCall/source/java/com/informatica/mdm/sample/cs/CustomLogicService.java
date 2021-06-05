package com.informatica.mdm.sample.cs;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.xml.transform.Source;
import javax.xml.ws.Provider;
import javax.xml.ws.Service.Mode;
import javax.xml.ws.ServiceMode;
import javax.xml.ws.WebServiceProvider;

import com.informatica.mdm.cs.client.CompositeServiceClient;
import com.informatica.mdm.spi.externalcall.CustomLogicFactory;
import com.informatica.mdm.spi.externalcall.ExternalCallProcessor;
import com.siperian.sif.client.EjbSiperianClient;
import com.siperian.sif.client.SiperianClient;

/**
 * Web service implementation.
 * It must accept {urn:bes-external-call.informatica.mdm}ExternalCallRequest as operation input
 * and return {urn:bes-external-call.informatica.mdm}ExternalCallResponse as output
 */
@WebServiceProvider(
        targetNamespace = "http://cs.sample.mdm.informatica.com/",
        serviceName = "CustomLogicService",
        portName = "CustomLogicServicePort",
        wsdlLocation = "WEB-INF/wsdl/custom-logic-service.wsdl"
)
@ServiceMode(Mode.PAYLOAD)
public class CustomLogicService implements Provider<Source> {

    @Override
    public Source invoke(Source request) {

        CompositeServiceClient compositeServiceClient = createCompositeServiceClient();
        CustomLogicFactory customLogicFactory = new CustomLogicFactoryImpl(compositeServiceClient);
        String appName = "<trusted user>"; // replace with proper application user name

        // create processor instance ond let it do the job.
        // all we need to provide is a custom logic factory implementation.
        ExternalCallProcessor externalCallProcessor =
                new ExternalCallProcessor(compositeServiceClient, appName, customLogicFactory);

        return externalCallProcessor.invoke(request);
    }

    private static CompositeServiceClient createCompositeServiceClient() {
        InputStream resourceAsStream = CustomLogicService.class.getResourceAsStream("/bes-client.properties");
        Properties config = new Properties();
        try {
            config.load(resourceAsStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return CompositeServiceClient.newCompositeServiceClient(config);
    }

}
