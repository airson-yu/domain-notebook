package com.airson.domain.notebook.config;

/**
 * TODO
 *
 * @author airson
 */
public class Const {

    public Const() {
        // forbid new instance
    }

    //MQ
    public static final String MQ_INSTANCE       = "kafka";
    public static final String MQ_GROUP_DEMO     = "cg-demo";
    public static final String MQ_GROUP_NOTEBOOK = "cg-notebook";
    public static final String MQ_TOPIC_DEMO     = "t-demo";
    public static final String MQ_TOPIC_REDIS     = "t-redis";
    public static final String MQ_TOPIC_NOTEBOOK = "t-notebook";

}
