import React, {useState, useMemo} from "react";

function Calculation(){
    const [count, setCount] = useState(0);
    const [count2, setCount2] = useState(0);

    const calculate= (num) => {
        console.log("calculte");
        return num * 2;
    }

    const memorizedValue = useMemo(() => calculate(count), [count]);

    return (
        <div>
            <h1>Count: {count}</h1>
            <h1>Count2: {count2}</h1>
            <h1>Memorized Value: {memorizedValue}</h1>
            <button onClick={() => setCount(count + 1)}>Increment Count</button>
            <button onClick={() => setCount2(count2 + 1)}>Increment Count2</button>
        </div>    )

}

export default Calculation;