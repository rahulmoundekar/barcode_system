package com.jarvis;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.TabSettings;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class BarCodeGenerator {

	public static void main(String[] args) throws FileNotFoundException, DocumentException {

		Document document = new Document(new Rectangle(com.itextpdf.text.PageSize.A4));

		PdfWriter writer = PdfWriter.getInstance(document,
				new FileOutputStream("F:\\JavaByRahul\\inventory\\pocs\\barcode_system\\martbarcode.pdf"));
		document.open();

		PdfPTable mainTable = new PdfPTable(4);
		mainTable.setHorizontalAlignment(Element.ALIGN_CENTER);
		mainTable.setWidthPercentage(100);
		mainTable.setSpacingBefore(15);
		mainTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

		Paragraph paragraph = null;

		PdfPTable leftTable = new PdfPTable(1);
		leftTable.setWidthPercentage(25f);
		leftTable.setSpacingBefore(5);

		PdfPTable middleTable = new PdfPTable(1);
		middleTable.setWidthPercentage(25f);
		middleTable.setSpacingBefore(5);

		PdfPTable rightTable = new PdfPTable(1);
		rightTable.setWidthPercentage(25f);
		rightTable.setSpacingBefore(5);
		
		PdfPTable rightTable1 = new PdfPTable(1);
		rightTable1.setWidthPercentage(25f);
		rightTable1.setSpacingBefore(5);

		for (int i = 0; i < 12; i++) {
			Barcode128 code128 = new Barcode128();
			code128.setGenerateChecksum(true);
			code128.setBarHeight(12);
			code128.setCode("1234569870");
			Image image = code128.createImageWithBarcode(writer.getDirectContent(), null, null);

			paragraph = new Paragraph();
			paragraph.setTabSettings(new TabSettings(46f));
			paragraph.add(new Chunk("       Basmati Rice", FontFactory.getFont(FontFactory.TIMES, 8)));
			paragraph.add(new Chunk(" 1Kg", FontFactory.getFont(FontFactory.TIMES, 8)));
			paragraph.add(new Chunk(" 90 Rs.", FontFactory.getFont(FontFactory.TIMES, 8)));
			paragraph.add("\n");
			paragraph.add("      ");
			paragraph.add(new Chunk(image, 0, 0, true));
			leftTable.addCell(paragraph);
			middleTable.addCell(paragraph);
			rightTable.addCell(paragraph);
			rightTable1.addCell(paragraph);
		}
		mainTable.addCell(leftTable);
		mainTable.addCell(middleTable);
		mainTable.addCell(rightTable);
		mainTable.addCell(rightTable1);
		document.add(mainTable);

		document.close();

		System.out.println("Document Generated...");

	}
}
