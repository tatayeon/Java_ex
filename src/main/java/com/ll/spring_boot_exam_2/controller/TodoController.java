package com.ll.spring_boot_exam_2.controller;

import com.ll.spring_boot_exam_2.domain.Todo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private long todosLastId = 0;
    private List<Todo> todos;

    public TodoController() {
        todos = new ArrayList<>();
    }

    @GetMapping("/list") //가지고 있는 값들을 josn형태로 돌려준다.
    public List<Todo> getTodos() {
        return todos;
    }

    @GetMapping("/add")
    public Todo add(String body){
        Todo todo = Todo
                .builder()
                .id(++todosLastId)
                .body(body)
                .build();
        todos.add(todo);
        return todo;
    }


}
