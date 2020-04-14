import React from 'react';

import { useState, useEffect } from 'react';


// {match} comes from the <Route> in App.js
function ItemDetail( {match} ) {

    // Look at how backticks, $ and {} are used below
    return (
        <div>
            <h1>Test {match.params.id}</h1>
        </div>
    );
}

export default ItemDetail;
