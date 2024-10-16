import React from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';

// Import your components for different routes
import Home from '../components/Home';
import About from '../components/About';
import Contact from '../components/Contact';

function RouterExample() {
    return (
        <Router>
                <Route exact path="/" component={Home} />
                <Route path="/about" component={About} />
                <Route path="/contact" component={Contact} />
        </Router>
    );
}

export default RouterExample;