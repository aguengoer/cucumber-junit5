Feature: Task management

  Scenario: Create a new task
    Given there are no tasks
    When I create a task with the title "Sample task"
    Then the task list should have 1 task with the title "Sample task"

  Scenario: Retrieve all tasks
    Given the following tasks exist:
      | title        | completed |
      | Task 1       | false     |
      | Task 2       | true      |
    When I request all tasks
    Then the task list should have 2 tasks
