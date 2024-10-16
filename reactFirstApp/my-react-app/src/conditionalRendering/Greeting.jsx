function Greeting ({ isLoggedIn}){

    if (isLoggedIn) {
        return <h1>Welcome back!</h1>;
    }
    return <h1>Please sign up.</h1>;
}


function Greeting2  ({isLoggedIn}) {
    return isLoggedIn ? <h1>Welcome back!</h1> : <h1>Please sign up.</h1>;
}

export default Greeting;
export { Greeting2 };