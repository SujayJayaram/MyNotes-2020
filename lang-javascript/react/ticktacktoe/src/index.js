import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import * as serviceWorker from './serviceWorker';

ReactDOM.render(<App />, document.getElementById("root"));

window.addEventListener('mousedown', function(e) {
    document.body.classList.add('mouse-navigation');
    document.body.classList.remove('kbd-navigation');
});
window.addEventListener('keydown', function(e) {
    if (e.keyCode === 9) {
        document.body.classList.add('kbd-navigation');
        document.body.classList.remove('mouse-navigation');
    }
});
window.addEventListener('click', function(e) {
    if (e.target.tagName === 'A' && e.target.getAttribute('href') === '#') {
        e.preventDefault();
    }
});

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
