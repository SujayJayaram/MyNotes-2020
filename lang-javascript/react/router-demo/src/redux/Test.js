import React, { Component } from 'react';

import { createSlice, configureStore } from "@reduxjs/toolkit";

class Test extends Component {
    constructor(props) {
        super(props);

        // ***** Must be defined in ctr

        ///////////////// Redux Toolkit Plumbing - https://redux-toolkit.js.org/tutorials/basic-tutorial
        const counterSlice = createSlice({
            name: "counter",
            initialState: 104,
            reducers: {
                increment: state => state + 1,
                decrement: state => state - 1
            }
        });

        const { increment, decrement } = counterSlice.actions;
        this.increment = increment;
        this.decrement = decrement;

        const store = configureStore({ reducer: counterSlice.reducer });
        this.store = store;
        alert(this.store.getState().toString());

        // store.subscribe(render);
        // store.getState().toString();
        // store.dispatch(increment());
        // store.dispatch(decrement());

        ////////////////////////////////////////////////////
    }

    componentDidMount() {


    }

    render() {

        return (
            <div>
                <h1>Value of counter: {this.store.getState().toString()}</h1>
            </div>
        );
    }
}

export default Test;
