import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import NavbarAccount from "./NavbarAccount";
import { NavLink } from 'react-router-dom';

import { useUser } from "../providers/UserContext";

const Header = () => {
    const { username } = useUser();
    const [query, setQuery] = useState("");
    const navigate = useNavigate();
    const { role } = useUser();
    const [activeTab, setActiveTab] = useState("products"); // Add state for active tab

    const handleSearch = (e: React.FormEvent) => {
        e.preventDefault();
        if (activeTab === "users") {
            navigate(`/search-user/${query}`);
        } else if (activeTab === "sellers") {
            navigate(`/search-seller/${query}`);
        } else {
            navigate(`/search/${query}`);
        }
    };

    return (
        <div className="navbar bg-base-100 shadow-xl rounded-box">
            
              <figure className="w-12 h-12">
                                <img
                                src={"https://media.licdn.com/dms/image/D4D0BAQHsi1ID7MtsyQ/company-logo_200_200/0/1698823482613?e=2147483647&v=beta&t=Xvzt_iryN4uF0qyi7V0xi5lFVjLv_NqoXTKBWA5eD9o"}
                                alt="Logo"
        className="w-full h-full object-contain"
                                />
            </figure>
            <div className="flex-1">
                <Link className="btn btn-ghost text-xl" to= {username ? "/": "/login"}>
                    MarketPlace
                </Link>
            </div>
            <div className="tabs tabs-boxed ">
                {role && (
                    <>
                        <NavLink
                            to="/"
                            role="tab"
                            className={({ isActive }) =>
                                `tab ${isActive ? 'tab-active' : ''}`
                            }
                            end // This ensures the root path is only active for the exact path '/'
                            onClick={() => setActiveTab("products")} // Add onClick handler to set active tab
                        >
                            Products
                        </NavLink>
                        <NavLink
                            to="/users"
                            role="tab"
                            className={({ isActive }) =>
                                `tab ${isActive ? 'tab-active' : ''}`
                            }
                            onClick={() => setActiveTab("users")} // Add onClick handler to set active tab
                        >
                            Users
                        </NavLink>
                        <NavLink
                            to="/sellers"
                            role="tab"
                            className={({ isActive }) =>
                                `tab ${isActive ? 'tab-active' : ''}`
                            }
                            onClick={() => setActiveTab("sellers")} // Add onClick handler to set active tab
                        >
                            Sellers
                        </NavLink>
                    </>
                )}
            </div>
             <form onSubmit={handleSearch} className="flex-none gap-2">
             {username &&           

                <div className="form-control">
                    <input
                        type="text"
                        placeholder={`Search ${activeTab}`} // Modify placeholder based on active tab
                        className="input input-bordered w-24 md:w-auto"
                        value={query}
                        onChange={(e) => setQuery(e.target.value)}
                    />
                </div>
            }
                        </form>

                <NavbarAccount username={username} />
           

        </div>
    );
};

export default Header;
