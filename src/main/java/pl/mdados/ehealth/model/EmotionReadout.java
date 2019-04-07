package pl.mdados.ehealth.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Date;

@Data
@Document(collection = "emotions")
public class EmotionReadout {
    @Id
    private String id;

    private String userId;

    private double fear;
    private double anger;
    private double sadness;
    private double happiness;
    private double disgust;
    private double surprise;

    private String comment;

    private Date createdAt = Date.from(Instant.now());
}
