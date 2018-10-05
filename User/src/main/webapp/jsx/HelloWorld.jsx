class HelloWorld extends React.Component {
    render() {
        return <h1>Hello, {this.props.text}</h1>;
    }

    constructor() {
        super();
        this.state = {
            data: [{
                id: 1,
                name: "Simon Bailey"
            }, {
                id: 2,
                name: "Thomas Burleson"
            }, {
                id: 3,
                name: "Will Button"
            }]
        }
    }
    render() {
        const PersonRow = ( props ) => {
            return (
                <tr>
                    <td>{props.data.id}</td>
                    <td>{props.data.name}</td>
                </tr>
            );
        }
        let rows = this.state.data.map( person => {
            return <PersonRow key={person.id} data={person} />
        } );
        return (
            <table>
                <tbody>{rows}</tbody>
            </table>
        );
    }
}

window.addEventListener( "load", () => {
    ReactDOM.render(
        <HelloWorld text="world!" />,
        document.getElementById( 'hello-world' )
    );
} );