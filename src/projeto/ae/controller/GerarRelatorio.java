package projeto.ae.controller;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.filechooser.FileSystemView;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.List;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javafx.scene.control.Cell;
import projeto.ae.model.Aluno;
import projeto.ae.service.ConectaAluno;

public class GerarRelatorio {
	// INSTANCIANDO CLASSES ---------------
	ConectaAluno conexao = new ConectaAluno();
	
	
	// PREFERENCIAS DE FONTES
    public static final Font BOLD_UNDERLINED = new Font(FontFamily.TIMES_ROMAN, 14, Font.BOLD | Font.UNDERLINE);
    public static final Font TITLE_BOLD = new Font(FontFamily.TIMES_ROMAN, 14, Font.BOLD);
    public static final Font TITLE = new Font(FontFamily.TIMES_ROMAN, 14);
    public static final Font NORMAL = new Font(FontFamily.TIMES_ROMAN, 12);
    public static final Font NORMAL_BOLD = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD);

	public void GerarPDF(){
		Document document = new Document();
		ArrayList<Aluno> resultados = new ArrayList<Aluno>();
		float[] headerwidths = { 80, 20};
		FileSystemView desktop = FileSystemView.getFileSystemView();
		
	    try {
	       
	    	// CRIANDO DOCUMENTO EM PDF
	        PdfWriter.getInstance(document, new FileOutputStream(desktop.getHomeDirectory().getPath() + "\\Relatorio.pdf"));
	        document.open();
	        
	        document.setPageSize(PageSize.A4);
	       
	        // CRIANDO TABELA
	        PdfPTable table = new PdfPTable(2);
	        
	        // CRIANDO PARAGRAFOS E ADICIONANDO NO DOCUMENTO
	        Paragraph titulo = new Paragraph("ANEXO IV", TITLE_BOLD);
	        titulo.setAlignment(Element.ALIGN_CENTER);
	        Paragraph titulo2 =new Paragraph("RESULTADO FINAL DE ATIVIDADES COMPLEMENTARES REALIZADAS", TITLE_BOLD);
	        titulo2.setAlignment(Element.ALIGN_CENTER);
	        Paragraph titulo3 =new Paragraph("Semestre/Ano:______/_______", NORMAL_BOLD);
	        titulo3.setAlignment(Element.ALIGN_RIGHT);
	        Paragraph titulo4 =new Paragraph("Após o recebimento dos relatórios dos(as) estudantes do ___º período/ano, segue abaixo o resultado final com a respectiva carga horária cumprida pelo(a) estudante.", NORMAL);
	        titulo4.setAlignment(Element.ALIGN_JUSTIFIED);
	         
	        document.add(titulo);
	        document.add(titulo2);
	        document.add(new Paragraph("   "));
	        document.add(titulo3);
	        document.add(new Paragraph("   "));
	        document.add(titulo4);


	        document.add(new Paragraph("   "));
	        document.add(new Paragraph("   "));
	        
//	        // CRIANDO PARAGRAFOS 
//	        Paragraph text = new Paragraph("Relatorio 1 dajshdkjahskjdhaskj");
//	        Paragraph text2 = new Paragraph("Relatorio 2");
//	        Paragraph text3 = new Paragraph("Relatorio 3");
//	        Paragraph text4 = new Paragraph("Relatorio 4");
//	            
//	        // INSERINDO PARAGRAFOS NAS COLUNAS(CELULAS) DA TABELA
//	        PdfPCell cell = new PdfPCell(text);
//	        PdfPCell cell2 = new PdfPCell(text2);
//	        PdfPCell cell3 = new PdfPCell(text3);
//	        PdfPCell cell4 = new PdfPCell(text4);
	        
//	        cell.setMinimumHeight(20);
//	        cell2.setMinimumHeight(20);
	        
	        
	        // CRIANDO PARAGRAFOS	        
	        Paragraph nome = new Paragraph("NOME DO(A) ESTUDANTE", NORMAL_BOLD);
	        nome.setAlignment(Element.ALIGN_CENTER);
	        Paragraph horas = new Paragraph("C.H. CUMPRIDA (HORAS)", NORMAL_BOLD);
	        horas.setAlignment(Element.ALIGN_CENTER);
	        
	  
	       	        
	        // PREFERÊNCIAS DAS COLUNAS DA TABELA
	        table.setTotalWidth(530);
	        table.setLockedWidth(true);
	        table.setWidths(headerwidths);
	        table.setWidthPercentage(100);
	        
	        // ADICIONANDO DADOS NA TABELA
	        
	        table.addCell(nome);
	        table.addCell(horas);
	        	        
	        
	        resultados = conexao.geraRelatorio();

	        
	        // ADICIONANDO DADOS NA LISTA 
	        for(int i = 0; i < resultados.size(); i++){
	        	table.addCell(resultados.get(i).getNome().toString());
	        	table.addCell(Integer.toString(resultados.get(i).getChTotal()));	
	        	
	        }
	        
	        document.add(table);
	        document.close();
	        
	    }
	    catch(DocumentException de) {
	        System.err.println(de.getMessage());
	    }
	    catch(IOException ioe) {
	        System.err.println(ioe.getMessage());
	    }
	    
	    // ABRINDO DOCUMENTO PDF CRIADO
	    try {
	    	
			Desktop.getDesktop().open(new File(desktop.getHomeDirectory().getPath() + "\\Relatorio.pdf"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
