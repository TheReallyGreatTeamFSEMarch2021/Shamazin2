import React from 'react';
import TestChildComponent from "./TestChildComponent.jsx";
// import Client from 'client';


class Shamazin extends React.Component{
    constructor(props){
        super(props);
        this.state={
            title: "ORIGINAL TITLE IN SHAMAZIN PARENT COMPONENT",
            photoURL: ""
        }
    }

    componentDidMount(){
//         $.ajax({
//             method: 'GET',
//             url: 'api/productPhotos/3'
//         })
    }

    render(){
        return(
            <div>
                HELLO SHAMAZIN ITEM COMPONENT!!!!HELLO
                PRODUCT TITLE: {this.state.photoURL}
                <img src="https://upload.wikimedia.org/wikipedia/commons/d/de/Amazon_icon.png"/>
                <br/>
                <br/>
                <TestChildComponent
                    passedProp={this.state.title}
                />
            </div>
        )
    }
}


export default Shamazin;