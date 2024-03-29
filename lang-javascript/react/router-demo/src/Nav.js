import React from 'react';

import { Link } from 'react-router-dom'

function Nav() {
    const navStyle = {
        color: 'white',
        textDecoration: 'underline'
    }

    return (
        <nav>
            <h3>Logo</h3>
            <ul className="nav-links">
                <Link style={navStyle} to="/about">
                    <li>About</li>
                </Link>
                <Link style={navStyle} to="/shop">
                    <li>Shop</li>
                </Link>
                <Link style={navStyle} to="/redux">
                    <li>Redux</li>
                </Link>
            </ul>
        </nav>
    );
}

export default Nav;
