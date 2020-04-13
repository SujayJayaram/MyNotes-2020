import React from 'react';

class Items extends React.Component {

    // https://reactjs.org/docs/lists-and-keys.html
    render() {
        const numbers = [1, 2, 3, 4, 5];
        const listItems = numbers.map((number) =>
            <li>{number}</li>
        );

        return (
            <div>
                <ul>{listItems}</ul>
            </div>
        );
    }
}

export default Items;
