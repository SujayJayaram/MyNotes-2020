import React from 'react';

import { useState, useEffect } from 'react';

import {Link} from 'react-router-dom';

function Shop() {


    useEffect(() => {
        fetchItems();
    }, []); // Empty second param means effect is just called on component load (i.e. every time it's re-rendered.

    const [items, setItems] = useState([]); // initially an empty array.

    const fetchItems = async () => {
        const data = await fetch('http://dummy.restapiexample.com/api/v1/employees'); // From http://dummy.restapiexample.com/
        const myItems = await data.json();

        console.log(myItems);
        console.log(myItems.status);
        console.log(myItems.data[1]);
        console.log(myItems.data.length);

        setItems(myItems.data);
    }

    // Look at how backticks, $ and {} are used below
    return (
        <div>
            { items.map(item => (
                <h3 key={item.id}>
                    <Link to={`shop/${item.id}`}>{item.employee_name}</Link>
                </h3>
            ))}
        </div>
    );
}

export default Shop;
