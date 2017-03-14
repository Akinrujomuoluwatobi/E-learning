package com.example.progtobi.e_learning.model;

public class PWork {
    private int id;
    private String worktitle;
    private String author;
    private String teachername;
    private String imgUrl, topic_note, topic_example, topic_hint, topic_questn, qst_example;

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getWorktitle() {
        return worktitle;
    }

    public void setWorktitle(String worktitle) {
        this.worktitle = worktitle;
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public String getQst_example() {
        return qst_example;
    }

    public void setQst_example(String qst_example) {
        this.qst_example = qst_example;
    }

    public String getTopic_questn() {
        return topic_questn;
    }

    public void setTopic_questn(String topic_questn) {
        this.topic_questn = topic_questn;
    }

    public String getTopic_hint() {
        return topic_hint;
    }

    public void setTopic_hint(String topic_hint) {
        this.topic_hint = topic_hint;
    }

    public String getTopic_example() {
        return topic_example;
    }

    public void setTopic_example(String topic_example) {
        this.topic_example = topic_example;
    }

    public String getTopic_note() {
        return topic_note;
    }

    public void setTopic_note(String topic_note) {
        this.topic_note = topic_note;
    }
}
