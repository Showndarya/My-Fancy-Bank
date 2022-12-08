package BusinessLogicLayer;

import DataAccessLayer.StockRepository;
import DataAccessLayer.StockRepositoryImpl;
import Models.Stock;
import dto.StockList;

import java.sql.SQLException;
import java.util.List;

public class StockServiceImpl implements StockService{
    private StockRepository stockRepository;
    public StockServiceImpl(){
        stockRepository = new StockRepositoryImpl();
    }
    @Override
    public StockList getAllStock() {
        List<Stock> list;
        try {
            list = stockRepository.getAllStocks();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        StockList stockList = new StockList();
        System.out.println(list.size());
        stockList.setColumnsName(new Object[]{"Name","Tag","Price"});
        Object[][] rowData = new Object[list.size()][];
        for(int i=0;i<list.size();i++){
            Stock stock = list.get(i);
            rowData[i] = new Object[]{stock.getName(), stock.getTag(), stock.getPrice()};
        }
        stockList.setRowData(rowData);
        return stockList;
    }

    public static void main(String[] args) {
        StockServiceImpl stockService = new StockServiceImpl();
        StockList allStock = stockService.getAllStock();
        System.out.println(allStock.getRowData().length);
    }
}
