package org.example.invocationapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "invocations")
public class Invocation {
    @Id
    private String id;
    private String monstreId;
    private LocalDateTime timestamp;
    private boolean processed;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMonstreId() {
        return monstreId;
    }

    public void setMonstreId(String monstreId) {
        this.monstreId = monstreId;
    }

}
