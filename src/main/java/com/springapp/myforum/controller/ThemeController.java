package com.springapp.myforum.controller;

import com.springapp.myforum.model.Message;
import com.springapp.myforum.model.SubTheme;
import com.springapp.myforum.model.Theme;
import com.springapp.myforum.repository.ForumRepository;
import com.springapp.myforum.validation.MessageValidator;
import com.springapp.myforum.validation.ThemeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ThemeController {

    private ForumRepository forumRepository;

    private ThemeValidator themeValidator;

    private MessageValidator messageValidator;

    private Theme parentTheme;

    private SubTheme parentSubTheme;

    @Autowired
    public ThemeController (ForumRepository forumRepository, ThemeValidator themeValidator, MessageValidator messageValidator) {
        this.forumRepository = forumRepository;
        this.themeValidator = themeValidator;
        this.messageValidator = messageValidator;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getTheme (Model model) {
        List<Theme> themes = this.forumRepository.listAll();
        model.addAttribute("themes", themes);
        return "index";
    }

    @RequestMapping(value = "subTheme/{id}", method = RequestMethod.GET)
    public String getSubTheme (Model model, @PathVariable Integer id) {
        List<SubTheme> subThemes = this.forumRepository.listALLSub(id);
        model.addAttribute("subthemes", subThemes);
        parentTheme = this.forumRepository.getTheme(id);
        return "subTheme";
    }

    @RequestMapping(value = "message/{id}", method = RequestMethod.GET)
    public String getMessages (Model model, @PathVariable Integer id) {
        List<Message> messages = this.forumRepository.listALLMessages(id);
        parentSubTheme = this.forumRepository.getSubTheme(id);
        model.addAttribute("messages", messages);
        model.addAttribute("message", new Message());
        return "message";
    }

    @RequestMapping(value = "messageErr/{id}", method = RequestMethod.GET)
    public String getMessagess (Model model, @PathVariable Integer id) {
        List<Message> messages = this.forumRepository.listALLMessages(id);
        parentSubTheme = this.forumRepository.getSubTheme(id);
        model.addAttribute("messages", messages);
        model.addAttribute("message", new Message());
        return "messageErr";
    }

    @RequestMapping(value = "message/{id}", method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated()")
    public String addMessage(@ModelAttribute("message") Message message, @PathVariable Integer id, BindingResult bindingResult) {
        this.messageValidator.validate(message, bindingResult);
        if (bindingResult.hasErrors()) {
            return "redirect:/messageErr/" + id;
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        message.setAuthor(auth.getName());
        this.forumRepository.addMessage(message, parentSubTheme);
        SubTheme tobeUpdated = parentSubTheme;
        tobeUpdated.setMessagesCount(tobeUpdated.getMessagesCount() + 1);
        tobeUpdated.setLastMessage(auth.getName());
        this.forumRepository.updateSubTheme(parentTheme, tobeUpdated);
        return "redirect:/message/" + id;
    }

    @RequestMapping(value = "messageErr/{id}", method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated()")
    public String addMessagee(@ModelAttribute("message") Message message, @PathVariable Integer id, BindingResult bindingResult) {
        this.messageValidator.validate(message, bindingResult);
        if (bindingResult.hasErrors()) {
            return "redirect:/messageErr/" + id;
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        message.setAuthor(auth.getName());
        this.forumRepository.addMessage(message, parentSubTheme);
        SubTheme tobeUpdated = parentSubTheme;
        tobeUpdated.setMessagesCount(tobeUpdated.getMessagesCount() + 1);
        tobeUpdated.setLastMessage(auth.getName());
        this.forumRepository.updateSubTheme(parentTheme, tobeUpdated);
        return "redirect:/message/" + id;
    }

    @RequestMapping(value = "subTheme/addSubTheme", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
    public String addSubTheme (Model model) {
        model.addAttribute("subtheme", new SubTheme());
        return "addSubTheme";
    }

    @RequestMapping(value = "subTheme/addSubTheme", method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated()")
    public String addSubTheme (@ModelAttribute("subtheme") SubTheme subTheme, BindingResult bindingResult) {
        this.themeValidator.validate(subTheme, bindingResult);
        if (bindingResult.hasErrors()) {
            return "addSubTheme";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        subTheme.setAuthor(auth.getName());
        this.forumRepository.addSubTheme(parentTheme, subTheme);
        Theme tobeUpdated = parentTheme;
        tobeUpdated.setThemeCount(tobeUpdated.getThemeCount() + 1);
        this.forumRepository.updateTheme(parentTheme);
        return "redirect:/subTheme/" + parentTheme.getId();
    }

    @RequestMapping(value = "addTheme", method = RequestMethod.GET)
    @PreAuthorize("hasRole('admin')")
    public String addTheme (Model model) {
        model.addAttribute("theme", new Theme());
        return "addTheme";
    }

    @RequestMapping(value = "addTheme", method = RequestMethod.POST)
    @PreAuthorize("hasRole('admin')")
    public String addTheme(@ModelAttribute("theme") Theme theme, BindingResult bindingResult) {
        this.themeValidator.validate(theme, bindingResult);
        if (bindingResult.hasErrors()) {
            return "addTheme";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        theme.setAuthor(auth.getName());
        this.forumRepository.addTheme(theme);
        return "redirect:/";
    }

    @RequestMapping(value = "deleteTheme/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('admin')")
    public String deleteTheme(@PathVariable Integer id) {
        this.forumRepository.removeTheme(id);
        return "redirect:/";
    }

    @RequestMapping(value = "deleteSubTheme/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('admin') or hasRole('mod')")
    public String deleteSubTheme(@PathVariable Integer id) {
        this.forumRepository.removeSubTheme(id);
        Theme tobeUpdated = parentTheme;
        tobeUpdated.setThemeCount(parentTheme.getThemeCount() - 1);
        this.forumRepository.updateTheme(tobeUpdated);
        return "redirect:/subTheme/" + parentTheme.getId();
    }

    @RequestMapping(value = "deleteMessage/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('admin') or hasRole('mod')")
    public String deleteMessage(@PathVariable Integer id) {
        this.forumRepository.removeMessage(id);
        SubTheme tobeUpdated = parentSubTheme;
        tobeUpdated.setMessagesCount(parentSubTheme.getMessagesCount() - 1);
        this.forumRepository.updateSubTheme(parentTheme, tobeUpdated);
        return "redirect:/message/" + parentSubTheme.getId();
    }

    @RequestMapping(value = "updateTheme/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('admin')")
    public String updateTheme(Model model, @PathVariable Integer id) {
        model.addAttribute("updatetheme", new Theme());
        return "updateTheme";
    }

    @RequestMapping(value = "updateTheme/{id}", method = RequestMethod.POST)
    @PreAuthorize("hasRole('admin')")
    public String updateTheme(@ModelAttribute("updatetheme") Theme theme, @PathVariable Integer id, BindingResult bindingResult) {
        this.themeValidator.validate(theme, bindingResult);
        if (bindingResult.hasErrors()) {
            return "updateTheme";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        theme.setAuthor(auth.getName());
        theme.setThemeCount(this.forumRepository.getTheme(id).getThemeCount());
        this.forumRepository.updateTheme(theme);
        return "redirect:/";
    }

    @RequestMapping(value = "subTheme/updateSubTheme/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('admin') or hasRole('mod')")
    public String updateSubTheme(Model model, @PathVariable Integer id) {
        model.addAttribute("updatesubtheme", new SubTheme());
        return "updateSubTheme";
    }

    @RequestMapping(value = "subTheme/updateSubTheme/{id}", method = RequestMethod.POST)
    @PreAuthorize("hasRole('admin') or hasRole('mod')")
    public String updateSubTheme (@ModelAttribute("updatesubtheme") SubTheme subTheme, BindingResult bindingResult) {
        this.themeValidator.validate(subTheme, bindingResult);
        if (bindingResult.hasErrors()) {
            return "updateSubTheme";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        subTheme.setAuthor(auth.getName());
        this.forumRepository.updateSubTheme(parentTheme, subTheme);
        return "redirect:/subTheme/" + parentTheme.getId();
    }

    @RequestMapping(value = "redirectBack", method = RequestMethod.GET)
    public String redirectBack (Model model) {
        return "redirect:/subTheme/" + parentTheme.getId();
    }

}
