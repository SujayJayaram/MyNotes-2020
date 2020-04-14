import React from 'react';
import './App.css';

import Nav from './Nav';
import Shop from './Shop';
import About from './About';
import {BrowserRouter as Router, Switch, Route} from 'react-router-dom';
import { useState } from 'react';


function App() {
    // USE OF REACT HOOKS - https://reactjs.org/docs/hooks-state.html
    // **** HOOKS DON'T WORK INSIDE CLASSES
    // Declare a new state variable, which we'll call "count"
    // When calling useState():
    // RHS is initial value
    // LSH is a tuple - a variable, and a set() method to change state
    // which causes the element to be re-rendered.
    const [count, setCount] = useState(0);

    return (
        <Router>
            <div className="App">
                <Nav></Nav>
                <Switch>
                    <Route path="/" exact component={Home}/>
                    <Route path="/about" component={About}/>
                    <Route path="/shop" component={Shop}/>
                </Switch>
            </div>

            <p>You clicked {count} times</p>
            <button onClick={() => setCount(count + 1)}>
                Click me
            </button>

        </Router>
    );
}

// Another way of creating a component
const Home = () => (
    <div>
        <h1>Home Page: React Version = {React.version}</h1>
    </div>
)

export default App;
