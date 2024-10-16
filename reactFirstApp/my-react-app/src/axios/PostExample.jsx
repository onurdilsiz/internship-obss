
import React, { useState } from 'react';
import axios from 'axios';
const PostExample = () => {
    const [title, setTitle] = useState('');
    const [body, setBody] = useState('');
    const [userId, setUserId] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('https://jsonplaceholder.typicode.com/users', {
                title,
                body,
                userId
            });
            console.log(response.data);
        } catch (error) {
            console.error(error);
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <input type="text" value={title} onChange={(e) => setTitle(e.target.value)} placeholder="Title" />
            <input type="text" value={body} onChange={(e) => setBody(e.target.value)} placeholder="Body" />
            <input type="text" value={userId} onChange={(e) => setUserId(e.target.value)} placeholder="User ID" />
            <button type="submit">Submit</button>
        </form>
    );
};

export default PostExample;