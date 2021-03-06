import React, { Component } from "react";
import ProjectsTask from "./ProjectTasks/ProjectsTask";

class Backlog extends Component {
  render() {
    const { project_tasks_prop } = this.props;
    const tasks = project_tasks_prop.map((project_task) => (
      <ProjectsTask key={project_task.id} project_task={project_task} />
    ));

    let todoItems = [];
    let inProgressItems = [];
    let doneItems = [];

    for (let i = 0; i < tasks.length; i++) {
      if (
        tasks[i].props.project_task.status === "TO_DO" ||
        tasks[i].props.project_task.status === "To_Do"
      ) {
        todoItems.push(tasks[i]);
      }
      if (
        tasks[i].props.project_task.status === "IN_PROGRESS" ||
        tasks[i].props.project_task.status === "In_Progress"
      ) {
        inProgressItems.push(tasks[i]);
      }
      if (
        tasks[i].props.project_task.status === "DONE" ||
        tasks[i].props.project_task.status === "Done"
      ) {
        doneItems.push(tasks[i]);
      }
    }

    return (
      <div className="container">
        <div className="row">
          <div className="col-md-4">
            <div className="card text-center mb-2">
              <div className="card-header bg-secondary text-white">
                <h3>TO DO</h3>
              </div>
            </div>
            {todoItems}
            {
              // tasks here
            }
          </div>
          <div className="col-md-4">
            <div className="card text-center mb-2">
              <div className="card-header bg-primary text-white">
                <h3>In Progress</h3>
              </div>
            </div>
            {inProgressItems}
          </div>
          <div className="col-md-4">
            <div className="card text-center mb-2">
              <div className="card-header bg-success text-white">
                <h3>Done</h3>
              </div>
            </div>
            {doneItems}
            {
              //<!-- SAMPLE PROJECT TASK STARTS HERE -->
              //<!-- SAMPLE PROJECT TASK ENDS HERE -->
            }
          </div>
        </div>
      </div>
    );
  }
}
export default Backlog;
