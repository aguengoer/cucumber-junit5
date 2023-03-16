package com.ara.cucumber.stepdefinitions;

import com.ara.cucumber.TaskController;
import com.ara.cucumber.TaskEntity;
import com.ara.cucumber.TaskRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class TaskManagementSteps {

    @Autowired
    private TaskController taskController;

    @Autowired
    private TaskRepository taskRepository;

    private List<TaskEntity> tasks;

    @Given("there are no tasks")
    public void there_are_no_tasks() {
        taskRepository.deleteAll();
    }

    @When("I create a task with the title {string}")
    public void i_create_a_task_with_the_title(String title) {
        TaskEntity task = new TaskEntity();
        task.setTitle(title);
        task.setCompleted(false);
        taskController.createTask(task);
    }

    @Then("the task list should have {int} task with the title {string}")
    public void the_task_list_should_have_task_with_the_title(int count, String title) {
        tasks = taskController.getAllTasks();
        Assertions.assertEquals(count, tasks
                .stream()
                .filter(task -> task.getTitle().equals(title))
                .count());
    }

    @Given("the following tasks exist:")
    public void the_following_tasks_exist(io.cucumber.datatable.DataTable dataTable) {
        taskRepository.deleteAll();
        List<TaskEntity> tasksToSave = dataTable.asMaps().stream().map(map -> {
            TaskEntity task = new TaskEntity();
            task.setTitle(map.get("title"));
            task.setCompleted(Boolean.parseBoolean(map.get("completed")));
            return task;
        }).collect(Collectors.toList());
        taskRepository.saveAll(tasksToSave);
    }

    @When("I request all tasks")
    public void i_request_all_tasks() {
        tasks = taskController.getAllTasks();
    }

    @Then("the task list should have {int} tasks")
    public void the_task_list_should_have_tasks(int count) {
        Assertions.assertEquals(count, tasks.size());
    }

}
