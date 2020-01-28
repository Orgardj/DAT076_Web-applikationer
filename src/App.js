import React, { Component } from 'react';
import $ from "jquery";
import { BrowserRouter as Router, Switch, Route, Link, Redirect } from 'react-router-dom';

import './App.css';
import './CSS/bootstrap-4.4.1-dist/css/bootstrap.css';

import Section from './Layout/Section';
import Navigation from './Layout/Navigation';
import Footer from './Layout/Footer';

import ImageSlider from './Components/ImageSlider';

import Post from './Pages/Post';


function App() {
  return (
    
    <Router>
        <Switch>
            <Route path="/Post" exact strict component={Post} />

            
            <section>
              

{/*                        
                        {this.state.isAuthenticated ?
                            <div>
                                <img src={LogoutIcon} className="loginIMG" onClick={() => { Cookies.remove("session"); Cookies.remove("access_token"); window.location.reload(); }} />
                                <p className="manageAccount"><Link to="/Account">Account</Link></p>
                            </div>
                        : 
                            <img src={LoginIcon} className="loginIMG" onClick={() => { this.setState({ visibleForm: !this.state.visibleForm, type: "LogAndReg" }); }} />
                        }
*/}
                        

                        <header className="header">

                            <div className="headerController">
                                <div>
                                    <Link to="/">Account</Link>
                                </div> 
                            </div>
                            
                            <h1>Forum</h1>

                            <Navigation />

                        </header>

                        <Section id="Start" value="Start">
                            <ImageSlider />
                        </Section>

                        <Section id="Posts" value="Posts">
                            <Link to="/Post">Post</Link>

                        </Section>

                        <Section id="New_Post" value="New_Post" >
                            <button>Make new post</button>
                        </Section>

                        <Section id="About" value="About">
                            <p>
                                Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
                            </p>
                        </Section>

                        <Footer />

            </section>



        </Switch>
    </Router>



  );
}

export default App;
