public class DataRow {

    private String[] data;
    private boolean isHeaderRow;

    public DataRow(String[] data, boolean isHeaderRow){
        this.data = data;
        this.isHeaderRow = isHeaderRow;
    }

    public int columnCount(){
        return data.length;
    }

    public boolean isHeader(){
        return isHeaderRow;
    }

    public int columnWidth(int index){
        return data[index].trim().length();
    }

    public String getColumnData(int index){
        if(index < data.length){
            return this.isHeaderRow ? data[index].toUpperCase() : data[index];
        }
        return "";
    }
}
