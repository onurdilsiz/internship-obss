import React, { useState } from 'react';

const FormExample = () => {
    const [formData, setFormData] = useState({
        name: '',
        email: '',
        gender: '',
    });

    const options = [
        { value:1, label:"Female"},
        { value:2, label: "Male"}   
    ]
    

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        // Handle form submission logic here
        console.log(formData);
    };

    return (
        <form onSubmit={handleSubmit}>
            <label>
                Name:
                <input type="text" name="name" value={formData.name} onChange={handleChange} />
            </label>
            <br />
            <label>
                Email:
                <input type="email" name="email" value={formData.email} onChange={handleChange} />
            </label>
            <br />
            <label>
                Gender:
                <select name="gender" value={formData.gender} onChange={handleChange}>
                {
                    
                        options.map((option) => (
                            <option key={option.label} value={option.value}>
                                {option.label}
                            </option>
                        ))
                    
                }
                </select>
            </label>
            <br />
            
            <button type="submit">Submit</button>
        </form>
    );
};

export default FormExample;