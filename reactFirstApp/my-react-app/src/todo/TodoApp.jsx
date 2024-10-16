import React, { useState } from 'react';
import AddTodo from './AddTodo';
import TodoList from './TodoList';

function TodoApp() {

    const [todos, setTodos] = useState([]);

    const addTodo = (todo) => {
      setTodos([...todos, todo]);
    };
  
    const deleteTodo = (indexToDelete) => {
      setTodos((prevTodos) => prevTodos.filter((_, index) => index !== indexToDelete));
    };
  return (
    <div>
      <h1>Todo App</h1>
      <AddTodo addTodo={addTodo} />
      <TodoList todos={todos} deleteTodo={deleteTodo}/>
    </div>
  );
}
export default TodoApp; 