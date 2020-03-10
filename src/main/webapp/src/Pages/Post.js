import React from 'react';
import Client from '../Client/Client.js';


export default class loginPage extends React.Component  {
        constructor(props) {
            super(props);
            this.client = new Client();
            this.state = { sayhello: [] };
            this.client.sayhello().then(r => this.setState({sayhello:r}));
        }
    render() {
        return (
                
                <section className="accountPage">
                
            {
            this.state.sayhello.map(n => <div>{n}</div>) 
            }

            </section>
        );
    }
}