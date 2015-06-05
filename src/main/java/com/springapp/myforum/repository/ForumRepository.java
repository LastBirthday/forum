package com.springapp.myforum.repository;


import com.springapp.myforum.model.Message;
import com.springapp.myforum.model.SubTheme;
import com.springapp.myforum.model.Theme;
import com.springapp.myforum.model.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ForumRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public void addTheme (Theme theme) {
        this.sessionFactory.getCurrentSession().save(theme);
    }

    public Theme getTheme (Integer id) {
        return (Theme) this.sessionFactory.getCurrentSession().createQuery("from Theme where id = " +id).uniqueResult();
    }

    public void addSubTheme (Theme theme, SubTheme subTheme) {
        subTheme.setTheme(theme);
        this.sessionFactory.getCurrentSession().save(subTheme);
    }

    public SubTheme getSubTheme (Integer id) {
        return (SubTheme) this.sessionFactory.getCurrentSession().createQuery("from SubTheme where id = " + id).uniqueResult();
    }

    public void addMessage (Message message, SubTheme subTheme) {
        message.setSubTheme(subTheme);
        this.sessionFactory.getCurrentSession().save(message);
    }

    public List<Theme> listAll () {
        return this.sessionFactory.getCurrentSession().createQuery("from Theme ").list();
    }

    public List<SubTheme> listALLSub (Integer id) {
        return this.sessionFactory.getCurrentSession().createQuery("from SubTheme where theme = " + id).list();
    }

    public List<Message> listALLMessages (Integer id) {
        return this.sessionFactory.getCurrentSession().createQuery("from Message where subTheme = " + id).list();
    }

    public List<String> getNames () {
        return this.sessionFactory.getCurrentSession().createQuery("SELECT name FROM User").list();
    }

    public void removeTheme (Integer id) {
        Theme contact = (Theme) this.sessionFactory.getCurrentSession().load(Theme.class, id);
        if (null != contact) {
            this.sessionFactory.getCurrentSession().delete(contact);
        }
    }

    public void removeSubTheme (Integer id) {
        SubTheme contact = (SubTheme) this.sessionFactory.getCurrentSession().load(SubTheme.class, id);
        if (null != contact) {
            this.sessionFactory.getCurrentSession().delete(contact);
        }
    }

    public void removeMessage (Integer id) {
        Message contact = (Message) this.sessionFactory.getCurrentSession().load(Message.class, id);
        if (null != contact) {
            this.sessionFactory.getCurrentSession().delete(contact);
        }
    }

    public void updateTheme (Theme theme) {
        this.sessionFactory.getCurrentSession().update(theme);
    }

    public void updateSubTheme (Theme theme, SubTheme subTheme) {
        subTheme.setTheme(theme);
        this.sessionFactory.getCurrentSession().update(subTheme);
    }

    public void addUser (User user) {
        this.sessionFactory.getCurrentSession().save(user);
    }

}
