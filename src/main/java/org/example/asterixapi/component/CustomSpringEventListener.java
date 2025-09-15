package org.example.asterixapi.component;

import org.example.asterixapi.event.CustomSpringEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.ServletRequestHandledEvent;

@Component
public class CustomSpringEventListener {

    @EventListener
    public void handleUserCreated(CustomSpringEvent event) {
        System.out.println("Event recipe: Result -> " + event.getMessage());
    }

    @EventListener
    public void handleRequest(ServletRequestHandledEvent event) {
        System.out.println("Request handled: " + event.getRequestUrl()
                + " Status=" + event.getStatusCode());
        System.out.println(event.getClientAddress());
    }
}