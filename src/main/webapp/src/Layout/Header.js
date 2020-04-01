import React from 'react';
import Navigation from './Navigation';


import AccountIcon from '../Image/accountIcon.png';
import SearchIcon from '../Image/searchIcon.png';


        class header extends React.Component {
        render() {
        return (
    
            <header className="header">
    

                <h1 className="">Forum</h1>
                {/*
                 <div className="searchBar">                               
                 <img src={searchIcon} alt="Search icon"></img>
                 <input type="text" placeholder="Search..."></input>
                 </div>
                 */}
                <div className="iconHolder">

                    <img src={AccountIcon} className="iconHolderIcon" alt="" onClick={() => { this.setState({ visibleForm: !this.state.visibleForm, type: "LogAndReg" }); }}></img>

                    <div className="searchBar">                               
                        <img src={SearchIcon} alt=""></img>
                        <input type="text" placeholder="Search..."></input>
                    </div>
                    {/*
                     <img src={searchIcon} className="iconHolderIcon searchIcon" alt="Login or Register icon"></img>
                     */}
                </div>
                <Navigation />

            </header>




                )
        }
        }

export default header;
