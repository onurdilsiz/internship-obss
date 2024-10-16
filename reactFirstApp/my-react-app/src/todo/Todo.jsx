import React from "react";
import { useState } from "react"

function ToDo(){
   const [todo, setTodo] = useState("");
   const [todos,setTodos] = useState([]);


   const handleAdd =  async (e ) => {
    e.preventDefault();
    if (!todo ) {
        console.log("todo is blank")        ;
        return;
    }
    setTodos([...todos, todo]); 
    setTodo(""); 
    console.log(todos)
   }


    const handleDelete = (indexToDelete) => {
        setTodos((prevTodos) => prevTodos.filter((_, index) => index !== indexToDelete));
      };
   

    return (
        <div>
             <form onSubmit={handleAdd}>
    <input
        type="text"
        placeholder="Add to do "
        value={todo}
        onChange ={(e) => setTodo(e.target.value)}
        required
      />
      <button type="submit">Add</button>
      </form>
      <h2>ToDo List</h2>
      <ul>
        {todos.map((item, index) => (
            <div>
          <li key={index+item}>{item}
           <button  style={{backgroundColor: "red" , marginLeft:100} } onClick={() => handleDelete(index)}>Delete</button>
           </li>
           </div>
        ))}
      </ul>
        </div>

    )
}



export default ToDo;