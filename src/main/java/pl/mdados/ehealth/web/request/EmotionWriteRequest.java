package pl.mdados.ehealth.web.request;

import lombok.Data;

@Data
public class EmotionWriteRequest {
    private double fear;
    private double anger;
    private double sadness;
    private double happiness;
    private double disgust;
    private double surprise;
}
