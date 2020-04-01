import React from 'react';
import { HashRouter, Switch, Route, Link } from 'react-router-dom';
import './App.css';
import Section from './Layout/Section';
import Footer from './Layout/Footer';

import Header from './Layout/Header';

import Post from './Pages/Post';
import addPost from './Pages/addPost';
//import axios from 'axios';


        function App() {

        return (
                <HashRouter>
    <Switch>

    
    <Route path="/Post" component={Post} />

    <Route path="/addPost" component={addPost} />


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

            <Header/>


        <Section id="Start" value="Start">

            {/*    
             <ImageSlider />
             */}

        </Section>

        <Section id="Posts" value="Posts">
            <Link to="/Post">Post</Link>




        </Section>

        <Section id="New_Post" value="New_Post" >
            {/* 
             <input type="text" placeholder="Titel"></input>
             
             <select>
             <option value="Choose_catigory" selected> -- Choose catigory -- </option> 
             <option value="cars">Cars</option>
             <option value="furniture">Furniture</option>
             <option value="news">News</option>
             <option value="Computers">Computers</option>
             </select>
             
             <textarea className="col-sm-12"></textarea>
             <button className="postSubmit">Submit post</button>
             */}

            <Link to="/addPost">Add Post</Link>


        </Section>

        <Section id="About" value="About">
            <p>
                Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
            </p>
        </Section>

        <Footer />

    </section>

    </Switch>
</HashRouter>
                );
        }

export default App;
