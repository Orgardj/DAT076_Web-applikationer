import React from 'react';
import axios from 'axios';
import Header from '../Layout/Header';

import { Link } from 'react-router-dom';


export default class addPost extends React.Component {

    onSubmit() {

        var title = document.getElementById("title").value;
        var text = document.getElementById("text").value;

        axios.post("http://localhost:8080/lab3/resources/ws/createpost/11",
                {
                    title: title,
                    text: text
                })
                .then(function (response) {
                    console.log(response);
                })
                .then(res => console.log(res.data));


        //alert(title + "  " + text);
    }

    render() {


        return (
                <section className="accountPage">
                
                    <Header/>
                    <Link to="/">Home</Link>
                    <form>
                        <input
                            type="text"
                            name="title"
                            id="title"
                            placeholder="title"
                            />
                
                        <input
                            type="text"
                            name="text"
                            id="text"
                            placeholder="text"
                            />
                
                        <button onClick={this.onSubmit}>new post</button>
                    </form>
                
                </section>
                );
    }
}