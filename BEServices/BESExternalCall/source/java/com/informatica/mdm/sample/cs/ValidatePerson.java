package com.informatica.mdm.sample.cs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.informatica.mdm.cs.CallContext;
import com.informatica.mdm.cs.api.CompositeServiceException;
import com.informatica.mdm.cs.client.CompositeServiceClient;
import com.informatica.mdm.sdo.cs.base.ValidationError;
import com.informatica.mdm.sdo.cs.base.ValidationErrors;
import com.informatica.mdm.spi.cs.StepException;
import com.informatica.mdm.spi.externalcall.CustomLogic;
import commonj.sdo.DataObject;
import commonj.sdo.helper.DataFactory;
import commonj.sdo.helper.HelperContext;
import mdm.informatica.cs_ors.ReadEntity;

/**
 * This is piece of logic for validating "Person" Business Entity.
 * All we do here is force user to provide at least one Address for the Person.
 */
public class ValidatePerson implements CustomLogic {

    private CompositeServiceClient besClient;
    private CallContext callContext;

    public ValidatePerson(CompositeServiceClient besClient, CallContext callContext) {
        this.besClient = besClient;
        this.callContext = callContext;
    }

    @Override
    public DataObject process(HelperContext helperContext, DataObject inputSdo,
            Map<String, Object> inParams,
            Map<String, Object> outParams) throws StepException {

        try {
            // make sure "Person" SDO has at least one "Address" item
            List<?> list = inputSdo.getList("Person/Addresses/item");
            if ((list == null) || list.isEmpty()) {

                DataFactory dataFactory = helperContext.getDataFactory();

                String personId = inputSdo.getString("Person/rowidObject");
                if(personId != null) { // check if address already exist
                    DataObject readEntity = dataFactory.create(ReadEntity.class);
                    DataObject personFilter = readEntity.createDataObject("parameters").createDataObject("coFilter").createDataObject("object");
                    personFilter.setString("name", "Person");
                    personFilter.createDataObject("key").setString("rowid", personId);
                    DataObject addressFilter = personFilter.createDataObject("object");
                    addressFilter.setString("name", "Addresses");
                    addressFilter.createDataObject("pager").setInt("recordsToReturn", 1);

                    DataObject readEntityReturn = besClient.process(callContext, readEntity);

                    List existingList = readEntityReturn.getList("object/Person/Addresses/item");
                    if(existingList != null && !existingList.isEmpty()) {
                        return null;
                    }
                }

                List<ValidationError> errorList = new ArrayList<>();

                ValidationError error = (ValidationError) dataFactory.create(ValidationError.class);
                error.setCode("CUSTOM-00001");
                error.setMessage("Person must have at least one Address");
                error.setField(Collections.singletonList("Person.Addresses"));
                errorList.add(error);

                ValidationErrors errors = (ValidationErrors) dataFactory.create(ValidationErrors.class);
                errors.setError(errorList);
                throw new StepException((DataObject) errors, "SIP-50022");
            }
        } catch (CompositeServiceException e) {
            throw new StepException(e);
        }

        return null;
    }

}
