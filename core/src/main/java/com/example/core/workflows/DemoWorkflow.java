package com.example.core.workflows;

import com.day.cq.wcm.webservicesupport.Service;
import com.day.cq.workflow.WorkflowException;
import com.day.cq.workflow.WorkflowSession;
import com.day.cq.workflow.exec.WorkItem;
import com.day.cq.workflow.exec.WorkflowProcess;
import com.day.cq.workflow.metadata.MetaDataMap;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = WorkflowProcess.class,
property = {"process.label=testWorkflow"})

public class DemoWorkflow implements WorkflowProcess {

    public  static  final Logger log = LoggerFactory.getLogger(DemoWorkflow.class);

    @Override
    public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap)
            throws WorkflowException {



    }
}
