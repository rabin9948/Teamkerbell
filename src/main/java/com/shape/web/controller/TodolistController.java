package com.shape.web.controller;

import com.shape.web.entity.Project;
import com.shape.web.entity.Todolist;
import com.shape.web.entity.User;
import com.shape.web.service.ProjectService;
import com.shape.web.service.TodolistService;
import com.shape.web.service.UserService;
import org.apache.taglibs.standard.tag.common.core.NullAttributeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by seongahjo on 2016. 6. 14..
 */
@Controller
public class TodolistController {

    private static final Logger logger = LoggerFactory.getLogger(ProcessController.class);

    @Autowired
    UserService us;

    @Autowired
    ProjectService pjs;

    @Autowired
    TodolistService ts;

    /*
   To make to-do list
   */
    @RequestMapping(value = "/todolist", method = RequestMethod.POST)
    @ResponseBody
    public String makeTodolist(@RequestParam("projectIdx") Integer projectIdx,
                               @RequestParam("userId") String userId,
                               @ModelAttribute("todolist") Todolist todolist) {
        Project project = pjs.get(projectIdx);
        User user = us.getById(userId);
        todolist.setProject(project); // todolist가 어디 프로젝트에서 생성되었는가
        todolist.setUser(user); // todolist가 누구것인가
        ts.save(todolist); // todolist 생성
        logger.info("todolist 만듬");
        return "Ok";
    }
    @RequestMapping(value = "/todolist", method = RequestMethod.PUT)
    @ResponseBody
    public void modifyTodolist(@RequestBody Todolist todolist){
        Todolist t=ts.get(todolist.getTodolistidx());
        if (todolist.getStartdate() != null)
            t.setStartdate(todolist.getStartdate());
        if (todolist.getEnddate() != null)
            t.setEnddate(todolist.getEnddate());
        if (todolist.getContent() != null)
            t.setContent(t.getContent());
            ts.save(t);

    }
    /*
     To accomplish to-do list
     */
    @RequestMapping(value = "/todocheck", method = RequestMethod.GET)
    @ResponseBody
    public void todocheck(@RequestParam(value = "id") Integer id) {
        Todolist todolist = ts.get(id);
        todolist.setOk(!todolist.getOk());
        ts.save(todolist);
    }
}