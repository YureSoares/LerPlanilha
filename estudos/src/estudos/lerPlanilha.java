package estudos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class lerPlanilha {

	// DEFINE O LOCAL DO ARQUIVO
	private static final String SheetName = "/home/arcus/Downloads/DoceSonho.xlsx";

	public static void main(String[] args) throws IOException {

		try {
			// ABRE A PLANILHA
			FileInputStream arquivo = new FileInputStream(new File(lerPlanilha.SheetName));

			// CRIA TXT
			File f = new File("doceSonho.txt");
			// INICIA ESCRITOR TXT
			PrintWriter imp;
			imp = new PrintWriter(f);

			// DEFINE PLANILHA
			XSSFWorkbook workbook = new XSSFWorkbook(arquivo);
			// DEFINE ABA
			XSSFSheet sheetProdutos = workbook.getSheetAt(0);

			// ITERATOR PARA PERCORRER LINHA
			Iterator<Row> rowIterator = sheetProdutos.iterator();

			while (rowIterator.hasNext()) {

				// CRIA UMA LINHA E ATRIBUI A PROXIMA LINHA DA PLANILHA
				Row row = rowIterator.next();
				// CRIA ITERATOR PARA PERCORRER AS CELULAS DA LINHA
				Iterator<Cell> cellIterator = row.cellIterator();

				// EQUANTO EXISTIR UMA PROXIMA CELULA EXECUTA COMANDOS ABAIXO
				while (cellIterator.hasNext()) {

					// CRIA UMA CELULA E ATRIBUI A PROXIMA CELULA DA LINHA
					Cell cell = cellIterator.next();

					//SE FOR O CABECALHO E A CELULA NAO FOR NULA IMPRIME E ESCREVE O TITULO EM 
					//LETRA MAISCULA
					if (row.getRowNum() == 0 && !cell.getCellType().name().equals("BLANK")) {
						System.out.printf("|%-25s|", cell.getStringCellValue().toUpperCase());
						imp.format("%-25s|", cell.toString() + " ");
						
					} else {
						// SE O TIPO FOR STRING IMPRIME E ESCREVE STRING
						if (cell.getCellType().name().equals("STRING")) {
							System.out.printf("|%-25s|", cell.getStringCellValue());
							imp.format("%-25s|", cell.toString());
							
						} else 
							// SE O TIPO FOR NUMERICO IMPRIME E ESCREVE STRING
							if (cell.getCellType().name().equals("NUMERIC")) {
								System.out.printf("%-25s|", cell.getNumericCellValue());
								imp.format("%-25s|", cell.toString());
							}
					}
					if (cell.getColumnIndex() == 4) {
						// QUANDO FOR A ULTIMA COLUNA ADICIONA UM TRACO SEPARADOR
						System.out.println(
								"\n---------------------------------------------------------------------------------------------------"
										+ "-----------------------------------");
						imp.append(
								"\n-----------------------------------------------------------------------------------------------"
										+ "-----------------------------------\n");
					}

				}
			}
			//FECHA ESCRITOR E O ARQUIVO
			imp.close();
			arquivo.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
