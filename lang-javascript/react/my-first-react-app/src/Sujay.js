import React from 'react';
import logo from './logo.svg';
import './Sujay.css';

import MyButton from './mydir/MyButton';
import Items from './mydir/Items';

// For rest calls see https://pusher.com/tutorials/consume-restful-api-react
// https://github.com/fisayoafolayan/consuming-restful-api-in-react
function Sujay() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload now.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React Sujay!
        </a>
        <MyButton mood="happy"/>
        <Items />
      </header>
    </div>
  );
}

export default Sujay;
