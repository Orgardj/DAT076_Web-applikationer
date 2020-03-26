import React from 'react';
import axios from 'axios';


export default class loginPage extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            users: []
        };
    }

    componentDidMount() {
        axios.get("http://localhost:8080/lab3/resources/ws/users").then(response => {
            this.setState({users: response.data}, () => {
                console.log(this.state);
            });
        });
    }

    render() {
        return (
                <section className="accountPage">
                
                    
                    
                    
                
                    dfgfddffgd
                
                </section>
                );
    }
}