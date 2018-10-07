const data = [
    {
        id: 1,
        name: "Simon Bailey"
    },
    {
        id: 2,
        name: "Thomas Burleson"
    },
    {
        id: 3,
        name: "Will Button"
    }
]

class HelloWorld extends React.Component {
    render() {
        const PersonRow = ( props ) =>
            <tr>
                <td>{props.data.id}</td>
                <td>{props.data.name}</td>
            </tr>
        return (
            <div>
                <h3>Hello, {this.props.text}</h3>
                <table>
                    <tbody>
                        {data.map(( data ) =>
                            <PersonRow key={data.id} data={data} />
                        )}
                    </tbody>
                </table>
            </div>
        )
    }
}

window.addEventListener( "load", () => {
    ReactDOM.render(
        <HelloWorld text="table!!" />,
        document.getElementById( 'hello-world' )
    )
} )