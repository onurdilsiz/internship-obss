import React, {createContext} from "react";

export const LanguageContext = createContext();

const LanguageProvider = ({children}) => {
    const [language, setLanguage] = React.useState("en");

    const changeLanguage = (e) => {
        setLanguage(e.target.value);
    }

    return (
        <LanguageContext.Provider value={{language, changeLanguage}}>
            {children}
        </LanguageContext.Provider>
    )
};

export default LanguageProvider;
