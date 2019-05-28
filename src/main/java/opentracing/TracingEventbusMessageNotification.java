package opentracing;

import javax.enterprise.context.ApplicationScoped;
//import javax.enterprise.event.Observes;

//import fish.payara.micro.cdi.Outbound;
//import fish.payara.notification.eventbus.EventbusMessage;
//import fish.payara.notification.requesttracing.RequestTracingNotificationData;


//-Needs enabled
// payara-api in pom.xml
// Configuration -> server-config -> Request Tracing -> Selected Notifiers -> service-cdieventbus
// Configuration -> server-config -> Notifications -> CDI Event Bus - enabled

@ApplicationScoped
public class TracingEventbusMessageNotification {


//	public void observe(@Observes @Outbound EventbusMessage event) {
//		
//		if (event.getData() instanceof RequestTracingNotificationData) {
//			RequestTracingNotificationData ev = (RequestTracingNotificationData) event.getData();
//			System.out.println("Trace:\n" + ev.getRequestTrace());
//
//		}
//	}

}
