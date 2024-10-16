import React, { useState } from 'react';
import FormInput from './../FormInput';
import { req } from '../../utils/client';
import { UserDetails } from './UserCard';


function UserPopUp({ isOpen, setIsOpen }: { isOpen: boolean, setIsOpen: (isOpen: boolean) => void }) {

    const [error, setError] = useState("");
    const [password, setPassword] = useState("");

    const [user, setUser] = useState<UserDetails>({} as UserDetails);

    const handleAdd = async (e: React.FormEvent) => {
        e.preventDefault();
        setError("");
        try {
            console.log(user);
            const resp = await req("users", "post", {
                name: user.name,
                surname: user.surname,
                username: user.username,
                email: user.email,
                password: password,
                // Add other user properties here
            });
            console.log("User response:", resp.data);
            console.log('User submitted');

        } catch (error: any) {
            setError(error.message);
        }

        setIsOpen(false);
    };

    if (!isOpen) return null;
    return (
        <div className="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-50">
            <div className="card bg-base-100 shadow-xl p-5">
                <h1 className="text-2xl font-bold">Create a User</h1>
                <FormInput type="text" placeholder="Name" value={user.name} onChange={(e: any) => setUser({ ...user, name: e.target.value })} />
                <FormInput type="text" placeholder="Surname" value={user.surname} onChange={(e: any) => setUser({ ...user, surname: e.target.value })} />
                <FormInput type="text" placeholder="Username" value={user.username} onChange={(e: any) => setUser({ ...user, username: e.target.value })} />
                <FormInput type="email" placeholder="Email" value={user.email} onChange={(e: any) => setUser({ ...user, email: e.target.value })} />
                <FormInput type="password" placeholder="Password" value={password} onChange={(e: any) => setPassword(e.target.value)} />
                {/* Add other user input fields here */}
                <div className="button-container">
                    <button className="btn btn-primary mt-4" onClick={handleAdd}>Add a User</button>
                    <button className="btn btn-primary mt-4" onClick={() => setIsOpen(false)}>Cancel</button>
                </div>
                <p className="text-red-500">{error}</p>
            </div>
        </div>
    );
}

export default UserPopUp;
