function HandlerExample (){


    const handleClick = (event) => {
        console.log(event)


    }


    return (
        <div>   <button onClick={handleClick}> bas</button></div>
    )
}

export default HandlerExample;