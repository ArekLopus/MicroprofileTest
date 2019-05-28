package opentracing;

//	Info
//https://groups.google.com/forum/#!topic/payara-forum/BLTEOMGXuYI
//https://docs.payara.fish/documentation/microprofile/opentracing.html
//https://docs.payara.fish/documentation/payara-server/request-tracing-service/configuration.html
//https://blog.payara.fish/troubleshooting-your-java-ee-applications-part-2
//https://github.com/lreimer/mp-tracing-payara-demo
//https://blog.payara.fish/request-tracing-service-in-payara-server-payara-micro

//	Configuration
//-Login to the admin console - localhost:4848
//-Configuration -> server-config -> Request Tracing / Notifications


//-Example config from https://github.com/lreimer/mp-tracing-payara-demo for use with asadmin
//-Enables RequestTracing and Logs notifier
// set-requesttracing-configuration --thresholdValue=25 --enabled=true --target=server-config --thresholdUnit=MICROSECONDS --dynamic=true
// requesttracing-log-notifier-configure --dynamic=true --enabled=true --target=server-config

public class Info_Payara {}