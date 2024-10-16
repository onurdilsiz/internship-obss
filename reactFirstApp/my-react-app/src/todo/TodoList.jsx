import React, { useState } from 'react';

function TodoList({ todos, deleteTodo } ) {

  
  return (<><h2>ToDo List</h2>
    <ul>
      {todos.map((item, index) => (
          <div>
        <li key={index+item}>{item}
         <button  style={{backgroundColor: "red" , marginLeft:100} } onClick={() => deleteTodo(index)}>Delete</button>
         </li>
         </div>
      ))}
    </ul>
    </>);

}

export default TodoList;