class StockList extends React.Component {
    constructor( props ) {
        super( props )
        this.state = {
            error: null,
            isLoaded: false,
            stocks: []
        }
    }
    componentDidMount() {
        fetch( "http://localhost:8080/User/stock/list.ajax" )
            .then( res => res.json() )
            .then(
            ( result ) => {
                this.setState( {
                    isLoaded: true,
                    stocks: result.stocks
                } )
            },
            ( error ) => {
                this.setState( {
                    isLoaded: true,
                    error
                } )
            }
            )
    }
    render() {
        const { error, isLoaded, stocks } = this.state
        if ( error ) {
            return (
                <div>Error: {error.message}</div>
            )
        } else if ( !isLoaded ) {
            return (
                <div>Loading...</div>
            )
        } else {
            return (
                <ul>
                    {stocks.map(( stock ) =>
                        <li key={stock.st_id}>
                            {stock.st_SecuritiesEntity.se_no} {stock.st_SecuritiesEntity.se_name}
                        </li>
                    )}
                </ul>
            )
        }
    }
}

window.addEventListener( "load", () => {
    ReactDOM.render(
        <StockList />,
        document.getElementById( 'stock-list' )
    )
} )