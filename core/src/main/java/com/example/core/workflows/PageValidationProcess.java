package com.example.core.workflows;

import com.day.cq.workflow.WorkflowSession;
import com.day.cq.workflow.exec.WorkItem;
import com.day.cq.workflow.exec.WorkflowProcess;
import com.day.cq.workflow.metadata.MetaDataMap;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(
        service = WorkflowProcess.class,
        property = {
                "process.label=Page Validation"
        }
)
public class PageValidationProcess implements WorkflowProcess {

    private static final Logger LOG = LoggerFactory.getLogger(PageValidationProcess.class);

    @Override
    public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap) {
        String payloadPath = workItem.getWorkflowData().getPayload().toString();
        LOG.info("✅ Custom workflow step executed for payload: {}", payloadPath);



    }
}
