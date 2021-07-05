import axios from "axios";
import setJWTToken from "../securituUtils/setJWTToken";
import { GET_ERRORS, SET_CURRENT_USER } from "./types";
import jwt_decode from "jwt-decode";

export const createNewUser = (newUser, history) => async (dispatch) => {
  try {
    await axios.post("/api/users/register", newUser);
    history.push("/login");
    dispatch({
      type: GET_ERRORS,
      payload: {},
    });
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data,
    });
  }
};

export const login = (loginRequest) => async (dispatch) => {
  try {
    //post login res
    const res = await axios.post("/api/users/login", loginRequest);
    //extract token
    const { token } = res.data;
    //store token in localstorage
    localStorage.setItem("jwtToken", token);
    //set token in header
    setJWTToken(token);
    //decode token
    const decoded = jwt_decode(token);
    //dispactch
    dispatch({
      type: SET_CURRENT_USER,
      payload: decoded,
    });
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data,
    });
  }
};

export const logout = () => (dispatch) => {
  localStorage.removeItem("jwtToken");
  setJWTToken(false);
  dispatch({
    type: SET_CURRENT_USER,
    payload: {},
  });
};
