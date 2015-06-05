package com.springapp.myforum.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "subtheme")
public class SubTheme {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;

    @OneToMany(mappedBy = "subTheme", cascade = CascadeType.ALL)
    private Set<Message> messages;

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    @ManyToOne
    @JoinColumn(name = "theme_id")
    private Theme theme;

    @Column(name = "name")
    private String name;

    @Column(name = "author")
    private String author;

    @Column(name = "messages")
    private Integer messagesCount = 0;

    @Column(name = "lastmessage")
    private String lastMessage = "No messages";

    public Integer getMessagesCount() {
        return messagesCount;
    }

    public void setMessagesCount(Integer messagesCount) {
        this.messagesCount = messagesCount;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
