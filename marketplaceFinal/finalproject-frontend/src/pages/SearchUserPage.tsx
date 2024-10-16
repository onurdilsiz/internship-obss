import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { req } from "../utils/client";
import UserCard from "../components/user/UserCard";

import { UserDetails } from "../components/user/UserCard";

export const SearchUserPage = () => {
    const { query } = useParams();

    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState("");
    const [users, setUsers] = useState<UserDetails[]>([]);
    const handleQuery = async () => {
        setIsLoading(true);
        setError("");
        setUsers([]);
        try {
            const searchQuery = `users?search=${query}`;
            const response = await req(searchQuery, "get", {});
            console.log("Search response:", response.data);
            const users: UserDetails[] = response.data.payload.map(
                (user: any, idx: number) => ({
                    id: idx,
                    user_id: user.id,
                    name: user.name,
                    surname: user.surname,
                    username: user.username,
                    email: user.email,
                    // Add any other properties you need
                })
            );
            if (users.length === 0) {
                    throw new Error("No users found");
            }
            setUsers(users);
        } catch (error: any) {
            console.error("Search failed:", error);
            setError(error.message);
        }
        setIsLoading(false);
    };
    useEffect(() => {
        handleQuery();
    }, [query]);

    if (isLoading) {
        return (
            <div className="flex flex-col justify-center items-center pt-10">
                <span className="loading loading-spinner loading-lg"></span>
            </div>
        );
    }

    return (
        <div className="flex flex-col justify-center items-center pt-5">
            {/* <h1>Search Page {query}</h1> */}
            {error && <p className="text-red-500">{error}</p>}
            {/* {JSON.stringify(users, null, 2)} */}

            <div className="flex flex-col gap-4">
                {users.map((user) => (
                    <UserCard key={user.id} user={user} refreshUsers={handleQuery} />
                ))}
            </div>
        </div>
    );
};

export default SearchUserPage;
