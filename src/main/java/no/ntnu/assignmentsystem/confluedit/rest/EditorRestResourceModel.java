package no.ntnu.assignmentsystem.confluedit.rest;

import javax.xml.bind.annotation.*;
@XmlRootElement(name = "message")
@XmlAccessorType(XmlAccessType.FIELD)

/**
 * Created by Lasse on 24.11.2014.
 */
public class EditorRestResourceModel {
    @XmlAttribute
    private String key;


    @XmlElement(name = "value")
    private String message;

    public EditorRestResourceModel() {
    }

    public EditorRestResourceModel(String key, String message) {
        this.key = key;
        this.message = message;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
