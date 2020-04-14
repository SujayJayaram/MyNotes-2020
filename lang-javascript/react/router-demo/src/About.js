import React, { Component } from 'react';

import Modal from './Modal';

class About extends Component {
    constructor(props) {
        super(props);

        this.state = { isOpen: false };
    }

    toggleModal = () => {
        this.setState({
            isOpen: !this.state.isOpen
        });
    }

    render() {
        return (
            <div>
                <h1>About Page3</h1>
                <button onClick={this.toggleModal}>
                    Open the modal
                </button>

                <Modal show={this.state.isOpen}
                       onClose={this.toggleModal}>
                    Here's some content for the modal
                </Modal>
            </div>
        );
    }
}

export default About;
