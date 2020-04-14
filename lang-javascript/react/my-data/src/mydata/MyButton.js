import React from 'react';


import diGetCount from './DIData'

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
                <button onClick={diGetCount}>Take the shot!</button>
                <button onClick={diGetCount}>Take the shot2!</button>
            </div>
        );
    }
}

export default MyButton;
