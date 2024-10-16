import { createContext, useContext, useState } from "react";

const UserContext = createContext<{
  setError: (message: string) => void;
  curError: string | null;
  username: string;
  setUsername: (username: string) => void;
  role: boolean;
  setRole: (role: boolean) => void;
}>({
  setError: (error: string) => {},
  curError: "",
  username: "",
  setUsername: (username: string) => {},
  role: false,
  setRole: (role: boolean) => {},
});

export const useUser = () => useContext(UserContext);

export const UserProvider = ({ children }: { children: any }) => {
  const [username, setUsername] = useState("");
  const [curError, setError] = useState("");
  const [role, setRole] = useState(false);

  return (
    <UserContext.Provider value={{ username, setUsername, curError, setError ,role, setRole}}>
      {children}
    </UserContext.Provider>
  );
};
