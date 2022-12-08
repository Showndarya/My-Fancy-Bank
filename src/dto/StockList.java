package dto;

public class StockList {
    private Object[] columnsName;
    private Object[][] rowData;


    public Object[] getColumnsName() {
        return columnsName;
    }

    public void setColumnsName(Object[] columnsName) {
        this.columnsName = columnsName;
    }

    public Object[][] getRowData() {
        return rowData;
    }

    public void setRowData(Object[][] rowData) {
        this.rowData = rowData;
    }

    public StockList() {
    }
}
