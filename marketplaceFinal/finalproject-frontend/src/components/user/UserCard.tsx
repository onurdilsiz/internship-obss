import React , {useEffect, useState}from 'react';
import { useUser } from '../../providers/UserContext';
import { req } from '../../utils/client';
import UserEdit from './UserEdit';

export type UserDetails = {
        id: string;
        user_id: string;
        name: string;
        surname: string;
        username: string;
        email: string;
        role: string;
        };

type UserCardProps = {
  user: UserDetails;
  refreshUsers: () => void;
};

const UserCard: React.FC<UserCardProps> = ({ user,refreshUsers }) => {
  const { role } = useUser();
  const [isOpen, setIsOpen] = useState(false);
  const { username } = useUser();
  const [userRole, setRole] = useState<string>("");

  const handleDelete = async (id: string) => {
    try {
      console.log(`Deleting user with ID: ${id}`);
      let url = `users/${id}`;
      await req(url, "delete", {});
      console.log("User deleted successfully");

      // Refresh user list
      refreshUsers();
    } catch (error: any) {
      console.error("Delete failed:", error);
    }
  };
  const getRole = async () => {
      const resp2 = await req("users/userrole/"+user.username, "get", {});
      // console.log("GetRole Successful", resp2.data);
      setRole(resp2.data.payload);
      return resp2.data.payload;
  }
 useEffect(() => {

      getRole();
  
 }, []);

  return (
    <div className="card bg-base-100 shadow-xl">
      <div className="card-body">
        <h2 className="card-title text-2xl font-bold text-gray-800 mb-2">
          {user.name} {user.surname}
        </h2>
        <div className="flex flex-col gap-2 mb-4">
          <div className="text-sm text-gray-600">
            <span className="font-semibold">Username: </span>
            {user.username}
          </div>
          <div className="text-sm text-gray-600">
            <span className="font-semibold">Email: </span>
            {user.email}
          </div>
          <div className="text-sm text-gray-600">
            <span className="font-semibold">Role: </span>
            {userRole? "Admin": "User"}
          </div>
        </div>
        <div className="card-actions justify-end">
          {role && (
            <>
              <button className="btn btn-secondary" onClick={() => setIsOpen(true)}>
                Edit
              </button>
              <button className="btn btn-danger" onClick={() => handleDelete(user.user_id)}>
                Delete
              </button>
            </>
          )}
        </div>
        {isOpen && <UserEdit isOpen={isOpen} setIsOpen={setIsOpen} user={user}  refreshFriends={refreshUsers}/>}
      </div>
      </div>

    
  );
};

export default UserCard;
