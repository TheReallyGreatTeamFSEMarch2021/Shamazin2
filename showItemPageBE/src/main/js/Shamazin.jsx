import React from 'react';
import TestChildComponent from "./TestChildComponent.jsx";

class Shamazin extends React.Component{
    constructor(props){
        super(props);
        this.state={
            title: "ORIGINAL TITLE IN SHAMAZIN PARENT COMPONENT"
        }
    }

    render(){
        return(
            <div>
                HELLO SHAMAZIN ITEM COMPONENT!!!!HELLO
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