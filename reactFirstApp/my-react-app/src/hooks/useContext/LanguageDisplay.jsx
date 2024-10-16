import React,{useContext} from "react";
import {LanguageContext} from "./LanguageProvider";

const LanguageDisplay = () => {

    const {language} = useContext(LanguageContext);

    return (
        <div>
            {language === "en" ? "Hello" : "Merhaba"}
        </div>
    )
}
export default LanguageDisplay;