package pl.mdados.ehealth.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Date;

@Data
@Document(collection = "pulses")
public class PulseReadout {
    @Id
    private String id;

    private String userId;

    private int pulse;

    private String comment;

    private Date createdAt = Date.from(Instant.now());
}
