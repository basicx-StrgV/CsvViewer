import java.util.ArrayList;

public class DataTable {
    ArrayList<DataRow> dataRows = new ArrayList<>();
    
    public void addDataRow(DataRow dataRow){
        dataRows.add(dataRow);
    }

    public void render(){
        if(!dataRows.isEmpty()){
            int columnCount = headerRow().columnCount();
            int[] columnWidths = new int[columnCount];

            //Calculate column widths
            for(int i = 0; i < columnCount; i++){
                int width = 0;
                for(int j = 0; j < dataRows.size(); j++){
                    if(dataRows.get(j).columnWidth(i) > width){
                        width = dataRows.get(j).columnWidth(i);
                    }
                }
                columnWidths[i] = width;
            }

            String border = getBorder(columnCount, columnWidths);

            renderHeaderRow(columnCount, columnWidths, border);
            
            for(int i = 0; i < dataRows.size(); i++){
                if(!dataRows.get(i).isHeader()){
                    renderDataRow(dataRows.get(i), columnCount, columnWidths, border);
                }
            }
        }
    }

    private String getBorder(int columnCount, int[] columnWidths){
        StringBuilder rowBoarder = new StringBuilder();

        for(int i = 0; i < columnCount; i++){
            rowBoarder.append("+--");
            for(int j = 0; j < columnWidths[i]; j++){
                rowBoarder.append("-");
            }
        }
        rowBoarder.append("-+");

        return rowBoarder.toString();
    }

    private void renderHeaderRow(int columnCount, int[] columnWidths, String border){
        Console.writeLine(border);
        renderDataRow(headerRow(), columnCount, columnWidths, border);
    }

    private void renderDataRow(DataRow dataRow, int columnCount, int[] columnWidths, String border){
        StringBuilder dataLine =  new StringBuilder();

        for(int i = 0; i < columnCount; i++){
            dataLine.append("| ");
            dataLine.append(dataRow.getColumnData(i).trim());
            int fillCount = 
                columnWidths[i] - dataRow.getColumnData(i).trim().length();
            for(int j = 0; j < fillCount; j++){
                dataLine.append((char)32);
            }
            dataLine.append((char)32);
        }
        dataLine.append(" |");

        Console.writeLine(dataLine.toString());
        Console.writeLine(border);
    }

    private DataRow headerRow(){
        for(int i = 0; i < dataRows.size(); i++) {
            if(dataRows.get(i).isHeader()){
                return dataRows.get(i);
            }
        }
        return dataRows.get(0);
    }
}
