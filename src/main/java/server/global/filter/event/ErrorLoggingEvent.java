package server.global.filter.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorLoggingEvent {
    private int status;
    private String message;
}
