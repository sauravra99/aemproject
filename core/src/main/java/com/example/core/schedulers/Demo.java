package com.example.core.schedulers;

import com.example.core.config.SchedulerConfig;
import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.*;
import org.osgi.service.metatype.annotations.Designate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = Runnable.class, enabled = true, immediate = true)

@Designate(ocd = SchedulerConfig.class)

public class Demo  implements  Runnable{

    @Reference
    private Scheduler scheduler;

    private int schedulerId;

    private static final Logger log = LoggerFactory.getLogger(Demo.class);



    @Modified
    @Activate
    public void activate(SchedulerConfig config){

      schedulerId= config.hashCode();

        addScheduler(config);




        log.info("this is the expression {} ",config.schedulerExpression());

    }


    @Deactivate

    public void deactivate(SchedulerConfig config){

        deactivate();

    }

    public void addScheduler (SchedulerConfig config){

        ScheduleOptions expr = scheduler.EXPR(config.schedulerExpression());

        expr.name(config.name());

        expr.canRunConcurrently(false);

        scheduler.schedule(this,expr);

    }


    public void deactivate(){
        scheduler.unschedule(String.valueOf(schedulerId));

        log.info("Deactivated");
    }


    @Override
    public void run() {

        log.info("this is my first scheduler");

    }
}
