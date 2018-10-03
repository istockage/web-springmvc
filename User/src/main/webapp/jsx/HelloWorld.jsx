class HelloWorld extends React.Component {
    render() {
        return <h1>{this.props.text}</h1>;
    }
}
window.addEventListener( "load", () => {
    ReactDOM.render(
        <HelloWorld text="Hello, world!" />,
        document.getElementById( 'hello-world' )
    );
} );