import React from 'react';
import axios from 'axios';

//import Client from '../Client/Client.js';

class footer extends React.Component {
    
    constructor(props) {
		super(props);
		this.state = {
                    users: []
                };
	}

	componentDidMount() {
	/*	
        fetch("http://localhost:8080/lab3/resources/ws/sayhello")
			.then(res => res.json())
			.then((data) => {
				this.setState({books: data})
			}).catch(console.log(this.state.books));
	
         * 
         */
           
           axios.get("http://localhost:8080/lab3/resources/ws/sayhello").then(response => {
               this.setState({books : response.data}, () => {
                   console.log(this.state);
               });
           });
           
           
           axios.get("http://localhost:8080/lab3/resources/ws/users").then(response => {
               this.setState({users : response.data}, () => {
                   console.log(this.state);
               });
           });
    
    }
        
    render() {
        return (
            <footer>
            
                This is the footer
            

                {this.state.books}
                
                pomjpsao
                        
                        {this.state.users[0]}
                
                </footer>
        )
    }
}

export default footer;