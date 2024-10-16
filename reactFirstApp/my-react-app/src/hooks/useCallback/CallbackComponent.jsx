
import React, { useState } from 'react';

const ParentComponent = () => {
  const [count, setCount] = useState(0);

  // Without useCallback
  const handleClickWithoutCallback = () => {
    setCount(count + 1);
  };

  // With useCallback
  const handleClickWithCallback = useCallback(() => {
    setCount(count + 1);
  }, [count]);

  return (
    <div>
      <button onClick={handleClickWithoutCallback}>
        Increment Without Callback
      </button>
      <button onClick={handleClickWithCallback}>
        Increment With Callback
      </button>
      <p>Count: {count}</p>
    </div>
  );
};