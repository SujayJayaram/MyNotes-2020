import React from 'react';

// {match} comes from the <Route> in App.js
function ItemDetail( {match} ) {

    // Look at how backticks, $ and {} are used below
    // Could use
    // const { id } = match.params
    // <h1>Test {id}</h1>
    //return (
    //    <div>
    //        <h1>Test {match.params.id}</h1>
    //    </div>
    //);

    const { id } = match.params
    return (
        <div>
            <h1>Tests {id}</h1>
        </div>
    );
}

export default ItemDetail;
