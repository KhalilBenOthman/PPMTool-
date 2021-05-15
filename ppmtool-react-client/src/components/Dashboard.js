import React, { Component } from "react";
import CreateProjectButton from "./Project/CreateProjectButton";
import Projectitem from "./Project/Projectitem";

class Dashboard extends Component {
  render() {
    return (
      <div className="projects">
        <div className="container">
          <div className="row">
            <div className="col-md-12">
              <h1 className="display-4 text-center">Projects</h1>
              <br />
              <CreateProjectButton />
              <br />
              <hr />
              <Projectitem />
            </div>
          </div>
        </div>
      </div>
    );
  }
}
export default Dashboard;
