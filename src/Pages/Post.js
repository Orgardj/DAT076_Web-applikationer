import React from 'react';
import { BrowserRouter as Router, Switch, Route, Link, Redirect } from 'react-router-dom';

import '../CSS/bootstrap-4.4.1-dist/css/bootstrap.css';

import Image from '../Image/Background_1.jpg';

export default class loginPage extends React.Component  {
    render() {
        return (
            <section className="accountPage">
                <header className="header">
                    <div className="headerController">
                 
                        <div>
                            <Link to="/">Home</Link>
                        </div> 
                            
                    </div>

                    <h1>Forum</h1>


                </header>
                {/*
                <h1>Account Page</h1>
                */}


                <h1 className="text-center">Post titel</h1>
                <textarea className="col-sm-12" disabled>Lorem Ipsum is simply dummy text of the printing and typesetting industry. 
                Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, 
                when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, 
                but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of 
                Letraset sheets containing Lorem Ipsum passages, 
                and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</textarea>

                <img src={Image} className="mx-auto d-block"></img>

                <h3>Comments:</h3>

                <p>Very najs post!</p>

            </section>
        );
    }
}