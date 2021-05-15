import React from "react";
import { Link } from "react-router-dom";

const CreateProjectButton = () => {
  return (
    <Link to="/addProject" className="btn btn-primary btn-lg">
      Create a Project
    </Link>
  );
};
export default CreateProjectButton;
