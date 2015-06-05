package com.springapp.myforum.model;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Theme")
public class Theme {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;

    @OneToMany(mappedBy = "theme", cascade = CascadeType.ALL)
    private Set<SubTheme> subThemes;

    public Set<SubTheme> getSubThemes() {
        return subThemes;
    }

    public void setSubThemes(Set<SubTheme> subThemes) {
        this.subThemes = subThemes;
    }

    @Column(name = "name")
    private String name;

    @Column(name = "author")
    private String author;

    @Column(name = "themecount")
    private Integer themeCount = 0;

    @Column(name = "created")
    @GeneratedValue
    private String created;

    public Integer getThemeCount() {
        return themeCount;
    }

    public void setThemeCount(Integer themeCount) {
        this.themeCount = themeCount;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String createdBy) {
        this.author = createdBy;
    }
}
