import React, { useState } from 'react';

function AddTodo({ addTodo }){
    const [todo, setTodo] = useState("");

    const handleAdd =  async (e ) => {
        e.preventDefault();
        if (!todo ) {
            console.log("todo is blank")        ;
            return;
        }
        addTodo(todo);
        setTodo(""); 
       }

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
        </div>
    )
}

export default AddTodo;