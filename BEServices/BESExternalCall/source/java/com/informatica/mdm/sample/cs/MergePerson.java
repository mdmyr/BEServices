package com.informatica.mdm.sample.cs;

import java.util.List;
import java.util.Map;

import org.eclipse.persistence.sdo.SDOChangeSummary;

import com.informatica.mdm.spi.cs.StepException;
import com.informatica.mdm.spi.externalcall.CustomLogic;
import commonj.sdo.DataObject;
import commonj.sdo.helper.HelperContext;

/**
 * This piece of code supposed to be called before "Person" Business Entity record is merged (or previewMerge is called)
 */
public class MergePerson implements CustomLogic {

    public static final String KEYS_AND_OVERRIDES = "keysAndOverrides";
    public static final String OVERRIDES = "overrides";
    public static final String MERGE = "MERGE";

    @Override
    public DataObject process(HelperContext helperContext, DataObject inputSdo,
            Map<String, Object> inParams,
            Map<String, Object> outParams) throws StepException {

        DataObject keysAndOverrides = (DataObject) inParams.get(KEYS_AND_OVERRIDES);

        if (keysAndOverrides != null) {
            DataObject overrides = keysAndOverrides.getDataObject(OVERRIDES);
            if (overrides != null) {
                // if "overrides" are provided, then user is not simply merging several records, but
                // tryes to merge some child records or provides some winner override
                List<DataObject> list = overrides.getList("Person/TelephoneNumbers/item");
                if ((list != null) && !list.isEmpty()) {
                    SDOChangeSummary changeSummary = (SDOChangeSummary) overrides.getChangeSummary();
                    changeSummary.resumeLogging();
                    // remove "MERGE" element from all "Telephone" items, this will prevent mering of "Telephone" child records
                    for (DataObject dataObject : list) {
                        if (dataObject.isSet(MERGE)) {
                            dataObject.unset(MERGE);
                        }
                    }
                    // send updated "overrides" back to Hub
                    outParams.put(KEYS_AND_OVERRIDES, keysAndOverrides);
                }
            }
        }
        return inputSdo;
    }

}
