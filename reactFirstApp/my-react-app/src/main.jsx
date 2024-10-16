import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import App from './App.jsx'
import './index.css'
import Greeting from './conditionalRendering/Greeting.jsx'
import Greeting2 from './conditionalRendering/Greeting.jsx'
import Counter from './hooks/Counter.jsx'
import HandlerExample from './eventHandler/HandlerExample.jsx'
import ToDo from './todo/Todo.jsx'
import FetchDataExample from './hooks/FetchDataExample.jsx'
import TodoApp from './todo/TodoApp.jsx'
import LanguageProvider from './hooks/useContext/LanguageProvider.jsx'

import  LanguageSelector  from './hooks/useContext/LanguageSelector.jsx'
import LanguageDisplay from './hooks/useContext/LanguageDisplay.jsx'
import Calculation from './hooks/useMemo/Calculation.jsx'
import FocusExample from './hooks/useRef/FocusExample.jsx'
import FormExample from './form/FormExample.jsx'
import GetExample from './axios/getExample.jsx'
import PostExample from './axios/PostExample.jsx'
import UIRenderExample from './uiRendering/UIRenderexample.jsx'
import FormExampleClearOnSubmit from './form/UIFormExample.jsx'


const elem = <h1>hello</h1>

const staj = {
  name : "staj",
  week : "3"
}

const element = <h1>{staj.name}</h1>

const styleElement = <h1 style={{color: "red" }}>Hello</h1>

const numberArray = [1,2,3,4,5];

const listItems = numberArray.map((number) => <li>{number}</li>)

const numberElement = <ul>{listItems}</ul>

const divelement = <><h1>asfds</h1> <h2> sdfasdads</h2></>
function Test (){
  return <h1>test functional component</h1>
}



createRoot(document.getElementById('root')).render(
<FormExampleClearOnSubmit/>)