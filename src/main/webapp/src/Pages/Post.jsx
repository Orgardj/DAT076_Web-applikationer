import React from 'react';
import axios from 'axios';
import Header from '../Layout/Header';

import { Link } from 'react-router-dom';

export default class Post extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            posts: []
        };


    }

    componentWillMount() {
        axios.get("http://localhost:8080/lab3/resources/ws/getallposts").then(response => {
            this.setState({posts: response.data}, () => {
                console.log(this.state);


            });
        });
        
        

    }
    
    
     updatePost() {
         
         
        var editString = document.getElementById("updatedText").value;
        
        alert(editString);
         
         axios.post("http://localhost:8080/lab3/resources/ws/editpost/1", editString)
                .then(function (response) {
                    console.log(response);
                })
                .then(res => console.log(res.data));
     }

    render() {
        return (
                <section className="showposts">
                
                
                    <Header/>
                    
                    <Link to="/">Home</Link>
                    
                    <table>
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Title</th>
                                <th>Text</th>
                            </tr>
                        </thead>
                        <tbody>
                
                            {this.state.posts.map(post => {
                                            return (
                                                <tr key={post.id}>
                                                    <td>{post.title}</td>
                                                    <td>{post.text}</td>
                                                </tr>
                                                    )
                                        })}
                
                
                
                        </tbody>
                    </table>
                
                
                
                    <textarea id="updatedText" ></textarea>
                    <button onClick={this.updatePost}>Uppdatera</button>
                
                </section>
                );
    }
}