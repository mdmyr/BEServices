package com.informatica.mdm.sample.cs;

import java.util.Map;

import com.informatica.mdm.cs.CallContext;
import com.informatica.mdm.cs.client.CompositeServiceClient;
import com.informatica.mdm.spi.cs.StepException;
import com.informatica.mdm.spi.externalcall.CustomLogic;
import com.informatica.mdm.spi.externalcall.CustomLogicFactory;
import com.informatica.mdm.spi.externalcall.ServicePhase;
import com.informatica.mdm.spi.externalcall.Trigger;
import com.informatica.mdm.spi.externalcall.ExternalCallRequest;
import commonj.sdo.DataObject;
import commonj.sdo.helper.HelperContext;

public class CustomLogicFactoryImpl implements CustomLogicFactory {

    public static final String PERSON = "Person";

    private static final CustomLogic EMPTY_LOGIC = new EmptyLogic();

    private CompositeServiceClient besClient;

    public CustomLogicFactoryImpl(CompositeServiceClient besClient) {
        this.besClient = besClient;
    }

    @Override
    public CustomLogic create(ExternalCallRequest externalCallRequest) throws StepException {
        throw new StepException("CallContext is required!");
    }

    @Override
    public CustomLogic create(ExternalCallRequest externalCallRequest, CallContext callContext) throws StepException {
		// we interested in "Person" Business Entity only and can handle just few servcie phases
        Trigger trigger = externalCallRequest.getTrigger();
        String businessEntity = trigger.getBusinessEntity();
        ServicePhase phase = trigger.getServicePhase();

        switch (phase) {
            case WRITE_CO_BEFORE_VALIDATE:
                if (PERSON.equals(businessEntity)) {
                    return new ValidatePerson(besClient, callContext);
                }
                break;
            case PREVIEW_MERGE_CO_BEFORE_EVERYTHING:
            case MERGE_CO_BEFORE_EVERYTHING:
                if (PERSON.equals(businessEntity)) {
                    return new MergePerson();
                }
                break;
            default:
                //
        }
        return EMPTY_LOGIC; // this one will do nothing
    }

    private static class EmptyLogic implements CustomLogic {

        public static final DataObject OBJECT = null;

        @Override
        public DataObject process(HelperContext helperContext, DataObject dataObject, Map<String, Object> map,
                Map<String, Object> map1) throws StepException {
            return OBJECT;
        }
    }
}
