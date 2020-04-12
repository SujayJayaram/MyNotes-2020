import React from 'react';

import './MyButton.css';

import Car from './Car';

// https://www.w3schools.com/react/react_events.asp
class MyButton extends React.Component {

    constructor() {
        super();
        this.state = {color: "red"};
    }

    shoot() {
        alert("Great Shot!");
    }

    shoot2= () => {
        alert(this);
        /*
         The 'this' keyword refers to the component object
         */
    }

    // <!-- https://www.w3schools.com/react/react_components.asp -->
    render() {
        return (
            <div class="myButtonDiv">
                <h2>{this.state.color}</h2>


                <h2>I am a {this.props.mood} Button!</h2>
                <button onClick={this.shoot}>Take the shot!</button>
                <button onClick={this.shoot2}>Take the shot2!</button>
                <Car />
            </div>
        );
    }
}

export default MyButton;
