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
    public static final String MQ_GROUP_IM       = "cg-im";
    public static final String MQ_GROUP_NOTEBOOK = "cg-notebook";
    public static final String MQ_TOPIC_DEMO     = "t-demo";
    public static final String MQ_TOPIC_IM       = "t-im";
    public static final String MQ_TOPIC_REDIS    = "t-redis";
    public static final String MQ_TOPIC_NOTEBOOK = "t-notebook";

    // WEBSOCKET
    public static final String WS_TOPIC         = "/topic/greetings";
    public static final String WS_ENDPOINT      = "/gs-guide-websocket";
    public static final String WS_APP_PREFIX    = "/app";
    public static final String WS_HELLO_MAPPING = "/hello";

}
