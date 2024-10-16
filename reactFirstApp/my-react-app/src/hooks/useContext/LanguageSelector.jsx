import React, { useContext } from 'react';
import { LanguageContext } from './LanguageProvider';

const LanguageSelector = ()=>{
    const {language,changeLanguage}  = useContext(LanguageContext);

    return (
        <select onChange={changeLanguage} value={language}>
            <option value="en">English</option>
            <option value="tr">Turkish</option>
        </select>
    )


}

export default LanguageSelector;