import React from "react";
import { Link, useNavigate } from "react-router-dom";
import { req } from "../utils/client";
import { useUser } from "../providers/UserContext";

type NavbarAccountProps = {
  username: string;
};

const NavbarAccount = ({ username }: NavbarAccountProps) => {
  const navigate = useNavigate();
  const { setUsername, setError } = useUser();
  const { role, setRole } = useUser();

  const logout = async () => {
    try {
      await req("logout", "post", {});
    } catch (error: any) {
      console.error("Logout failed:", error);
      setError(error.message);
    }
    setUsername("");
    setRole(false);
    navigate("/login");
  };

  if (username !== "") {
    return (
      <div className="dropdown dropdown-end">
        <div
          tabIndex={0}
          role="button"
          className="btn btn-ghost btn-circle avatar"
        >
          <div className="w-10 rounded-full">
            <img alt={username} src="https://i.pravatar.cc/300" />
          </div>
        </div>
        <ul
          tabIndex={0}
          className="mt-3 z-[1] p-2 shadow menu menu-sm dropdown-content bg-base-100 rounded-box w-52"
        >
          <li>
            <Link to="/blacklist">Blacklisted Sellers</Link>
          </li>
         
          <li>
            <Link to="/favproducts">Favorite Products</Link>
          </li>
          <li>
          <button onClick={logout} className="w-full text-left">
              Logout
            </button>
          </li>
        </ul>
      </div>
    );
  } else {
    return (
      <ul className="menu menu-horizontal px-1">
        {/* <li>
          <Link to="/login">Login</Link>
        </li> */}
        {/* <li>
          <Link to="/register">Register</Link>
        </li> */}
      </ul>
    );
  }
};

export default NavbarAccount;