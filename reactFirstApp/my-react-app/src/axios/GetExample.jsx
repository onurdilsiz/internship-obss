import React, { useEffect, useState } from 'react';
import axios from 'axios';

const GetExample = () => {
    const [data, setData] = useState(null);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await axios.get('https://jsonplaceholder.typicode.com/users');
                console.log(response.data);
                setData(response.data);
            } catch (error) {
                console.error(error);
            }
        };

        fetchData();
    }, []);

    return (
        <div>
            {data ? (
                <ul>
                    {data.map((item) => (
                        <li key={item.id}>{item.name}</li>
                    ))}
                </ul>
            ) : (
                <p>Loading...</p>
            )}
        </div>
    );
};

export default GetExample;