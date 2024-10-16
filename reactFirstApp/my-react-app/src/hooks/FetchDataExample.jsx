import { useEffect,useState } from "react";


function FetchDataExample (){
    const [data, setData] = useState(null);

    useEffect(() => {
        fetch("https://jsonplaceholder.typicode.com/users")
        .then((response) => response.json())
        .then((data) => setData(data)).catch((error) => console.log(error));
    }, []);


    return (
        <div>
            <h1>Fetch Data Example</h1>
            <ul>
                {data && data.map((user) => (
                    <li key={user.id}>{user.name}</li>
                ))}
            </ul>
        </div>
    )


}
export default FetchDataExample;