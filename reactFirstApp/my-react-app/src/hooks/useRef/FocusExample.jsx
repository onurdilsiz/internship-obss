import React, { useRef } from 'react';

function FocusExample() {
    const inputRef = useRef();

    const handleClick = () => {
        inputRef.current.focus();
    }

    return (
        <div>
            <input ref={inputRef} type="text" />
            <button onClick={handleClick}>Focus</button>
        </div>
    )
}

export default FocusExample;