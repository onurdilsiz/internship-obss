import React, { useState } from "react";
import FormInput from "../components/FormInput";
import { req } from "../utils/client";
import { useUser } from "../providers/UserContext";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const LoginPage = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const { setUsername: setGlobalUsername } = useUser();
  
  const { setRole: setGlobalRole } = useUser();
  const navigate = useNavigate();

  const handleLogin = async (e: React.FormEvent) => {
    e.preventDefault();
    try {

        const axiosParams = {
            method: "post",
            url: process.env.REACT_APP_BACKEND_URL + "login",
            data: new URLSearchParams({
                username: username,
                password: password,
              }),
            withCredentials: true,
            withXSRFToken: true,
            xsrfCookieName: "csrftoken",
            xsrfHeaderName: "X-CSRFToken",
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
        
            validateStatus: () => true,
          
          };
          console.log("REQ>", axiosParams);
          const resp = await axios(axiosParams);
          if (resp.status === 403) {
            throw new Error("Username or password is incorrect");
          }
          if (resp.data.error) {
            throw new Error(resp.data.error);
          }
          console.log("Login Successful", resp.data);
          setGlobalUsername(username);
        const axiosParams2 = {
            method: "get",
            url: process.env.REACT_APP_BACKEND_URL + "users/userrole/"+username,
            withCredentials: true,
            validateStatus: () => true,   
          };
            console.log("REQ>", axiosParams2);
            const resp2 = await axios(axiosParams2);
            console.log("GetRole Successful", resp2.data);
            if(resp2.data.payload==true){
                setGlobalRole(true);
            }
            

      
 
      navigate("/");
    } catch (error: any) {
      console.error("Login failed:", error);
      setError(error.message);
    }
  };

  return (
    <form className="flex flex-col gap-4 p-4" onSubmit={handleLogin}>
      <FormInput
        icon="user"
        type="user"
        placeholder="username"
        value={username}
        onChange={(e: any) => setUsername(e.target.value)}
      />
      <FormInput
        icon="password"
        type="password"
        placeholder="password"
        value={password}
        onChange={(e: any) => setPassword(e.target.value)}
      />
      <button type="submit" className="btn btn-primary mt-4">
        Login
      </button>
      {error && <p className="text-red-500">{error}</p>}
    </form>
  );
};

export default LoginPage;