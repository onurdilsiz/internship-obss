import React, { useState, useEffect } from 'react';
import { req } from '../utils/client';
import UserCard ,{UserDetails}from '../components/user/UserCard';
import { useUser } from '../providers/UserContext';
import UserPopUp from '../components/user/UserPopUp';

const UsersPage = () => {
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState("");
    const [users, setUsers] = useState<UserDetails[]>([]);
    const [showPopup, setShowPopup] = useState(false);
    const { role } = useUser();

    const refreshUsers = async () => {
        setIsLoading(true);
        setError("");
        setUsers([]);
        try {
            const usersQuery = `users`;
            const response = await req(usersQuery, "get", {});
            console.log("Users response:", response.data.payload);
            const users: UserDetails[] = response.data.payload.map(
                (user: any, idx: number) => ({
                    id: idx,
                    user_id: user.id,
                    username: user.username,
                    name: user.name,
                    surname: user.surname,
                    email: user.email,
                    role: user.role,
                })
            );
            if (users.length === 0) {
                throw new Error("No users found");
            }
            setUsers(users);
        } catch (error: any) {
            console.error("Get failed:", error);
            setError(error.message);
        }
        setIsLoading(false);
    };


    useEffect(() => {
        refreshUsers();
    }, [showPopup]);

    if (isLoading) {
        return (
            <div className="flex flex-col justify-center items-center pt-10">
                <span className="loading loading-spinner loading-lg"></span>
            </div>
        );
    }

    return (
        <div className="flex flex-col justify-center items-center pt-5">
            {error && <p className="text-red-500">{error}</p>}
            <div className="flex flex-col gap-4">
                {users.map((user) => (
                    <UserCard key={user.id} user={user}  refreshUsers ={refreshUsers} />
                ))}
            </div>
            {role && (
                <button className="btn btn-primary fixed bottom-4 right-4" onClick={() => setShowPopup(true)}>
                    Create a User
                </button>
            )}
            {showPopup && <UserPopUp isOpen={showPopup} setIsOpen={setShowPopup} />}
        </div>
    );
};

export default UsersPage;
