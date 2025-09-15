package org.example.asterixapi.event;

import lombok.Data;
import lombok.Value;

@Data
@Value
public class CustomSpringEvent {
    String message;
}
