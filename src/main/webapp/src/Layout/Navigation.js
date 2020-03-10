import React from 'react';
import { Link } from "react-scroll";


class navigation extends React.Component {
    render() {
        return (
            <nav className="NavigationBar">
                <Link activeClass="active"
                    to="Start"
                    spy={true}
                    smooth={true}
                    offset={-170}
                    duration={500}>
                    Home
                </Link>
                <Link activeClass="active"
                    to="Posts"
                    spy={true}
                    smooth={true}
                    offset={-150}
                    duration={500}>
                    Posts
                </Link>
                <Link activeClass="active"
                    to="New_Post"
                    spy={true}
                    smooth={true}
                    offset={-140}
                    duration={500}>
                    New Post
                </Link>
                <Link activeClass="active"
                    to="About"
                    spy={true}
                    smooth={true}
                    offset={-165}
                    duration={500}>
                    About
                </Link>
            </nav>
        )
    }
}

export default navigation;