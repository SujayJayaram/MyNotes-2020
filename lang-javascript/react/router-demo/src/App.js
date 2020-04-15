import React from 'react';
import './App.css';

import Nav from './Nav';
import Shop from './Shop';
import About from './About';
import ItemDetail from './ItemDetail';
import Test from './redux/Test';

import {BrowserRouter as Router, Switch, Route, Redirect} from 'react-router-dom';
import { useState, useEffect } from 'react';


function App() {
    // USE OF REACT HOOKS - https://reactjs.org/docs/hooks-state.html
    // **** HOOKS DON'T WORK INSIDE CLASSES
    // Declare a new state variable, which we'll call "count"
    // When calling useState():
    // RHS is initial value
    // LHS is a tuple - a variable, and a set() method to change state
    // which causes the element to be re-rendered.
    const [count, setCount] = useState(0); // This is JS array 'destructuring'

    // If you’re familiar with React class lifecycle methods, you can think of
    // useEffect Hook as componentDidMount, componentDidUpdate, and
    // componentWillUnmount combined
    // Similar to componentDidMount and componentDidUpdate:
    useEffect(() => {
        // Update the document title using the browser API
        document.title = `You clicked ${count} times`;
    });

    return (
        <Router>
            <div className="App">
                <Nav></Nav>
                <Switch>
                    <Route path="/" exact component={Home}/>
                    <Route path="/about" component={About}/>
                    <Route path="/shop" exact component={Shop}/>
                    <Route path="/shop/:id" component={ItemDetail}/>
                    <Route path="/redux" component={Test}/>
                    <Redirect to="/" />
                </Switch>
            </div>

            <p>You clicked {count} times</p>
            <button onClick={() => {setCount(count + 2); console.log('Clicked Just Now');} }>
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
