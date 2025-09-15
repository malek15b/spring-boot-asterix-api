package org.example.asterixapi.component;

import org.example.asterixapi.event.CustomSpringEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CustomSpringEventListener {

    @EventListener
    public void handleUserCreated(CustomSpringEvent event) {
        System.out.println("Event recipe: Result -> " + event.getMessage());
    }
}