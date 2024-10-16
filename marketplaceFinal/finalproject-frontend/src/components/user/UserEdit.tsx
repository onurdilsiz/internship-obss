import React, { useState } from 'react';
import { UserDetails } from './UserCard';
import { req } from '../../utils/client';
import FormInput from '../FormInput';

function UserEdit({ isOpen, setIsOpen, user, refreshFriends }: { isOpen: boolean, setIsOpen: (isOpen: boolean) => void, user: UserDetails, refreshFriends: () => void }) {
    const [error, setError] = useState("");
    const [name, setName] = useState("");
    const [surname, setSurname] = useState("");
    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [role, setRole] = useState(false);

    const handleEditUser = async (e: React.FormEvent) => {
        e.preventDefault();
        setError("");

        try {
            let url = `users/` + user.user_id;
            // Update the request payload with the new state values
            await req(url, "put", {
                name: name ? name : user.name,
                surname: surname ? surname : user.surname,
                username: username ? username : user.username,
                email: email ? email : user.email
                        });
            console.log("User updated successfully");
            if (role) {
                await req(`users/${user.user_id}/assign-as-admin`, "put", {});
                console.log("User assigned as admin successfully");
                
            }
            refreshFriends(); // Call refreshFriends after successful update
        } catch (error: any) {
            setError(error.message);
        }

        // Close the popup after posting
        setIsOpen(false);
    };

    if (!isOpen) return null;
    return (
        <div className="user-popup">
            {(
                <div className="card bg-base-100 shadow-xl">
                    <h2 className="card-title">Edit User</h2>
                    <FormInput type="text" placeholder={user.name} value={name} onChange={(e: any) => setName(e.target.value)} />
                    <FormInput type="text" placeholder={user.surname} value={surname} onChange={(e: any) => setSurname(e.target.value)} />
                    <FormInput type="text" placeholder={user.username} value={username} onChange={(e: any) => setUsername(e.target.value)} />
                    <FormInput type="text" placeholder={user.email} value={email} onChange={(e: any) => setEmail(e.target.value)} />
                     <div className="form-control">
                        <label className="label cursor-pointer">
                            <span className="label-text">Assign as admin</span>
                            <input type="checkbox" className="checkbox checkbox-primary" checked={role} onChange={(e: any) => setRole(e.target.checked)} />
                        </label>
                    </div>
                    <div className="button-container">
                        <button className="btn btn-primary mt-4" onClick={handleEditUser}>Save</button>
                        <button className="btn btn-primary mt-4" onClick={() => setIsOpen(false)}>Cancel</button>
                    </div>
                    <p className="text-red-500">{error}</p>
                </div>
            )}
        </div>
    );
}

export default UserEdit;
