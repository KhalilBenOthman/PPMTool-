import { SET_CURRENT_USER } from "../actions/types";

const initialstate = {
  user: {},
  validToken: false,
};
const booleanActionPayload = (payload) => {
  if (payload) {
    return true;
  } else {
    return false;
  }
};

// eslint-disable-next-line import/no-anonymous-default-export
export default function (state = initialstate, action) {
  switch (action.type) {
    case SET_CURRENT_USER:
      return {
        ...state,
        validToken: booleanActionPayload(action.payload),
        user: action.payload,
      };

    default:
      return state;
  }
}
