package no.hal.eclipsky.confluedit.rest;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "model")
@XmlAccessorType(XmlAccessType.FIELD)
public class EditorRestResourceModel {
    @XmlElement
    private String taskId;

    @XmlElement
    private String difficulty;

    @XmlElement
    private String effort;

    @XmlElement
    private String editable;

    @XmlElement
    private String emfs;

    private EditorRestResourceModel() {}

    public EditorRestResourceModel(String taskId, String difficulty, String effort, String editable, String emfs) {
        this.taskId = taskId;
        this.difficulty = difficulty;
        this.effort = effort;
        this.editable = editable;
        this.emfs = emfs;
    }


    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getEffort() {
        return effort;
    }

    public void setEffort(String effort) {
        this.effort = effort;
    }

    public String getEditable() {
        return editable;
    }

    public void setEditable(String editable) {
        this.editable = editable;
    }

    public String getEmfs() {
        return emfs;
    }

    public void setEmfs(String emfs) {
        this.emfs = emfs;
    }

    @Override
    public String toString() {
        return "EditorRestResourceModel{" +
                "taskId='" + taskId + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", effort='" + effort + '\'' +
                ", editable='" + editable + '\'' +
                ", emfs='" + emfs + '\'' +
                '}';
    }
}