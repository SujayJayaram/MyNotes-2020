import React, { Component } from 'react';

class ChildPassedObjectProps extends Component {
    // e.g. const myProps = {fname: 'Joe', lname: 'Blogs!', callMe: this.callMe};
    constructor(props) {
        super(props);
    }

    componentDidMount() {
        // Just called oonce
        console.log(this.props);
        this.props.myProps.callMe();
    }

    render() {
        return (
            <div>
                <h1>Child Passed Object Props</h1>
                {this.props.myProps.fname} {this.props.myProps.lname}
            </div>
        );
    }
}

export default ChildPassedObjectProps;
