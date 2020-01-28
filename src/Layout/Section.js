import React from 'react';
import { BrowserRouter as Router, Switch, Route, Link, Redirect } from 'react-router-dom';


class section extends React.Component {
    render() {
        return (
            <section className={this.props.id}>

                {this.props.show === "true" ?
                <h3>{this.props.value}</h3>
                : null }


                {this.props.children}

            </section>


        )
    }
}

export default section;
