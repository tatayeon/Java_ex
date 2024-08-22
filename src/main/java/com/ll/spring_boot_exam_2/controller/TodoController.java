package com.ll.spring_boot_exam_2.controller;

import com.ll.spring_boot_exam_2.domain.Todo;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/todos")
@Transactional(readOnly = true)
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

    @GetMapping("/detail/{id}") //가지고 있는 값들을 josn형태로 돌려준다.
    public Todo getTodo(@PathVariable("id") long id) {
        return todos
                .stream() //여기서 하나 찾으면 리턴하고 없으면 null로 리턴을 해라
                .filter(
                        todo -> todo.getId() == id
                ).findFirst()
                .orElse(null);

    }

    @GetMapping("/add")
    @Transactional
    public Todo add(String body){
        Todo todo = Todo
                .builder()
                .id(++todosLastId)
                .body(body)
                .build();
        todos.add(todo);
        return todo;
    }

    @GetMapping("/remove/{id}")
    public String removeTodo(@PathVariable("id") long id){
        todos.removeIf(todo -> todo.getId() == id);

        return id + "가 삭제되었습니다.";

    }

    @GetMapping("modify/{id}")
    @Transactional
    public boolean modifyTodo(@PathVariable("id") long id, String body){
        Todo todo = todos.stream()
                .filter(
                        _todo -> _todo.getId() == id
                )
                .findFirst()
                .orElse(null);

        if(todo == null) return false;

        todo.setBody(body);
        return true;
    }


}
