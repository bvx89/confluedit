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
    private String xemfs;

    private EditorRestResourceModel() {}

    public EditorRestResourceModel(String taskId, String difficulty, String effort, String xemfs) {
        this.taskId = taskId;
        this.difficulty = difficulty;
        this.effort = effort;
        this.xemfs = xemfs;
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

    public String getXEMFS() {
        return xemfs;
    }

    public void setEmfs(String emfs) {
        this.xemfs = emfs;
    }

    @Override
    public String toString() {
        return "EditorRestResourceModel{" +
                "taskId='" + taskId + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", effort='" + effort + '\'' +
                ", emfs='" + xemfs + '\'' +
                '}';
    }
}