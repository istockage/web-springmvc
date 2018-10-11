// @ts-nocheck
class StockList extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            error: null,
            isLoaded: false,
            stocks: []
        }
    }
    componentDidMount() {
        fetch("http://localhost:8080/User/stock/list.ajax")
            .then(response => response.json())
            .then(
                (result) => {
                    this.setState({
                        isLoaded: true,
                        stocks: result.stocks
                    })
                },
                (error) => {
                    this.setState({
                        isLoaded: true,
                        error
                    })
                }
            )
    }
    render() {
        const { error, isLoaded, stocks } = this.state
        if (error) {
            return (
                <div>Error: {error.message}</div>
            )
        } else if (!isLoaded) {
            return (
                <div>Loading...</div>
            )
        } else {
            const fontSize = { fontSize: ".8rem" }
            const textAlign = { textAlign: "right" }
            return (
                <div id="stock-list-table" className="table-responsive" style={fontSize}>
                    <table className="table table-sm mb-0">
                        <thead className="thead-">
                            <tr>
                                <th>編號</th>
                                <th>代號</th>
                                <th>股票</th>
                                <th>類別</th>
                                <th>買進時間</th>
                                <th>價格</th>
                                <th>股數</th>
                                <th>折扣</th>
                                <th>手續費</th>
                                <th>交割金</th>
                                <th>賣出時間</th>
                                <th>價格</th>
                                <th>股數</th>
                                <th>折扣</th>
                                <th>手續費</th>
                                <th>證交稅</th>
                                <th>交割金</th>
                                <th>漲幅</th>
                                <th>淨利</th>
                                <th>盈虧</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            {stocks.map((stock, index) =>
                                <tr key={stock.st_id}>
                                    <td>{index + 1}</td>
                                    <td>{stock.st_SecuritiesEntity.se_no}</td>
                                    <td>{stock.st_SecuritiesEntity.se_name}</td>
                                    <td>{stock.st_CodeEntity.co_name}</td>
                                    <td>{stock.st_buy_time}</td>
                                    <td style={textAlign}>{stock.st_buy_price}</td>
                                    <td style={textAlign}>{stock.st_buy_share}</td>
                                    <td style={textAlign}>{stock.st_buy_discount}</td>
                                    <td style={textAlign}>{stock.st_buy_fee}</td>
                                    <td style={textAlign}>{stock.st_buy_delivery}</td>
                                    <td>{stock.st_sell_time}</td>
                                    <td style={textAlign}>{stock.st_sell_price}</td>
                                    <td style={textAlign}>{stock.st_sell_share}</td>
                                    <td style={textAlign}>{stock.st_sell_discount}</td>
                                    <td style={textAlign}>{stock.st_sell_fee}</td>
                                    <td style={textAlign}>{stock.st_sell_tax}</td>
                                    <td style={textAlign}>{stock.st_sell_delivery}</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td>
                                        <a href="#" className="btn btn-sm btn-light" title="編輯"><i className="fa fa-edit"></i></a>
                                        <a href="#" className="btn btn-sm btn-light" title="刪除"><i className="fa fa-trash-o"></i></a>
                                    </td>
                                </tr>
                            )}
                        </tbody>
                    </table>
                </div>
            )
        }
    }
}

window.addEventListener("load", () => {
    ReactDOM.render(
        <StockList />,
        document.getElementById('stock-list')
    )
})