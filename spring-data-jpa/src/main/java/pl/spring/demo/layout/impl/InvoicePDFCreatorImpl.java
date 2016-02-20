package pl.spring.demo.layout.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.xml.xmp.PdfAXmpWriter;
import com.itextpdf.text.zugferd.InvoiceDOM;
import com.itextpdf.text.zugferd.profiles.BasicProfile;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.spring.demo.layout.InvoicePDFCreator;
import pl.spring.demo.to.CustomerTo;
import pl.spring.demo.to.InvoiceBasicProfileTo;
import pl.spring.demo.to.InvoiceTo;
import pl.spring.demo.to.ItemTo;
import pl.spring.demo.to.ProductTo;
import pl.spring.demo.to.TaxRateTo;


/**
 * Created by Paweł on 27.01.2016.
 */
@Service
//@ComponentScan("pl.spring.demo.mapper")
public class InvoicePDFCreatorImpl implements InvoicePDFCreator {
    public static final String DEST = "results/pdf/basic%05d.pdf";
    public static final String ICC = "C:\\Users\\Paweł\\Documents\\GitHub\\faktura\\spring-data-jpa\\src\\main\\resources\\fonts\\sRGB_CS_profile.icm";
    public static final String FONT_REGULAR = "/fonts/Calibri.ttf";
    public static final String FONTS_CALIBRI_BOLD = "/fonts/Calibri Bold.ttf";
    public static final String FONTS_CALIBRI_ITALIC = "/fonts/Calibri Italic.ttf";
    private static final String PLACE_OF_CREATION = "Turek";
    private final static int PARAGRAPH_LEADING_SMALL = 10;
    private final static int PARAGRAPH_INDENTION = 10;
    private final static int PARAGRAPH_LEADING_AVERAGE = 15;
    private final static int PARAGRAPH_LEADING_BIG = 20;
    private ResourceBundle resource;
    private final static float[] relativeColumnWidthsForItemsTable = {1, 8, 1.5f, 1.75f, 1.75f, 3, 3, 1.2f, 2.5f, 3};

    @Autowired
    private MapperFacade mapper;

    protected Font font10;
    protected Font font10b;
    protected Font font12;
    protected Font font12b;
    protected Font font14;


    private void prepareFonts() throws DocumentException, IOException {
        BaseFont bf = BaseFont.createFont(FONT_REGULAR, BaseFont.CP1250, BaseFont.EMBEDDED);
        BaseFont bfb = BaseFont.createFont(FONTS_CALIBRI_BOLD, BaseFont.CP1250, BaseFont.EMBEDDED);
        font10 = new Font(bf, 10);
        font10b = new Font(bfb, 10);
        font12 = new Font(bf, 12);
        font12b = new Font(bfb, 12);
        font14 = new Font(bf, 14);
        resource = ResourceBundle.getBundle("i18n.invoice-text");
    }

    @Override
    public void createPdf(InvoiceTo invoice) throws Exception {
        prepareFonts();
        String dest = String.format(DEST, invoice.getid());
        BasicProfile basic = mapper.map(invoice, InvoiceBasicProfileTo.class);
        File file = new File(DEST);
        file.getParentFile().mkdirs();

        // step 1
        Document document = new Document();
        // step 2
        PdfAWriter writer = PdfAWriter.getInstance(document, new FileOutputStream(dest), PdfAConformanceLevel.ZUGFeRDBasic);
        writer.setPdfVersion(PdfWriter.VERSION_1_7);
        writer.createXmpMetadata();
        writer.getXmpWriter().setProperty(PdfAXmpWriter.zugferdSchemaNS, PdfAXmpWriter.zugferdDocumentFileName, "ZUGFeRD-invoice.xml");
        // step 3
        document.open();
        // step 4
        ICC_Profile icc = ICC_Profile.getInstance(new FileInputStream(ICC));
        writer.setOutputIntents("Custom", "", "http://www.color.org", "sRGB IEC61966-2.1", icc);

        // header
        Paragraph p;
        p = new Paragraph(PARAGRAPH_LEADING_SMALL, resource.getString("invoice.placeOfCraetion") + ", " + convertDate(invoice.getCreationDate(), "yyyy-MM-dd"), font10);
        p.setAlignment(Element.ALIGN_RIGHT);
        document.add(p);
        p = new Paragraph(PARAGRAPH_LEADING_SMALL, resource.getString("invoice.header.saleDate") + convertDate(invoice.getSaleDate(), "yyyy-MM-dd"), font10);
        p.setAlignment(Element.ALIGN_RIGHT);
        document.add(p);
        p = new Paragraph(PARAGRAPH_LEADING_SMALL, resource.getString("invoice.invoiceHeader"), font10);
        p.setAlignment(Element.ALIGN_CENTER);
        document.add(p);
        p = new Paragraph(PARAGRAPH_LEADING_SMALL, String.format("%2d/%s", invoice.getid(), convertDate(invoice.getCreationDate(), "MM/yyyy")), font12b);
        p.setAlignment(Element.ALIGN_CENTER);
        document.add(p);
        p = new Paragraph(PARAGRAPH_LEADING_SMALL, resource.getString("invoice.description.original"), font10b);
        p.setAlignment(Element.ALIGN_CENTER);
        p.setSpacingAfter(5);
        document.add(p);

        // Header seller / buyer
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        PdfPCell sellerHeader = new PdfPCell(new Phrase(resource.getString("invoice.header.seller"), font10b));
        sellerHeader.setBorder(PdfPCell.NO_BORDER);
        sellerHeader.setCellEvent(new MyBorder(SIDE.LEFT));
        table.addCell(sellerHeader);
        PdfPCell buyerHeader = new PdfPCell(new Phrase(resource.getString("invoice.header.buyer"), font10b));
        buyerHeader.setBorder(PdfPCell.NO_BORDER);
        buyerHeader.setCellEvent(new MyBorder(SIDE.LEFT));
        table.addCell(buyerHeader);
        document.add(table);

        // CompanyData seller / buyer
        table = new PdfPTable(2);
        table.setWidthPercentage(100);
        CustomerTo customer = invoice.getCustomer();
        PdfPCell sellerCell = getCompanyData(customer.getName(), customer.getStreet(), customer.getPostCode(),
                customer.getCity(), customer.getNIP());
        table.addCell(sellerCell);
        PdfPCell buyerCell = getCompanyData(customer.getName(), customer.getStreet(), customer.getPostCode(),
                customer.getCity(), customer.getNIP());
        table.addCell(buyerCell);
        document.add(table);

        // Bank account header/ receiver header
        table = new PdfPTable(2);
        table.setWidthPercentage(100);
        PdfPCell accountHeader = new PdfPCell(new Phrase(resource.getString("invoice.header.account"), font10b));
        accountHeader.setBorder(PdfPCell.NO_BORDER);
        accountHeader.setCellEvent(new MyBorder(SIDE.LEFT));
        table.addCell(accountHeader);
        PdfPCell receiverHeader = new PdfPCell(new Phrase(resource.getString("invoice.header.receiver"), font10b));
        receiverHeader.setBorder(PdfPCell.NO_BORDER);
        receiverHeader.setCellEvent(new MyBorder(SIDE.LEFT));
        table.addCell(receiverHeader);
        document.add(table);

        // Bank account / receiver
        table = new PdfPTable(2);
        table.setWidthPercentage(100);
        PdfPCell bankAccount = getBankAccountData("Ewa Szlagor", "PKOBP Oddział 1 w Kole", customer.getAccount());
        table.addCell(bankAccount);
        PdfPCell receiverCell = getCompanyData(customer.getName(), customer.getStreet(), customer.getPostCode(),
                customer.getCity(), customer.getNIP());
        table.addCell(receiverCell);
        table.addCell(buyerCell);
        document.add(table);

        // PaymentWay header
        table = new PdfPTable(1);
        table.setWidthPercentage(50);
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        PdfPCell paymentWay = new PdfPCell(new Phrase(resource.getString("invoice.header.payment.way"), font10b));
        paymentWay.setBorder(PdfPCell.NO_BORDER);
        paymentWay.setCellEvent(new MyBorder(SIDE.LEFT));
        table.addCell(paymentWay);
        table.addCell(new PdfPCell());
        document.add(table);

        // PaymentWay data
        table = new PdfPTable(2);
        table.setWidthPercentage(100);
        PdfPCell cell = new PdfPCell();
        cell.setBorder(PdfPCell.NO_BORDER);
        Paragraph paymentWayParagraph = new Paragraph(PARAGRAPH_LEADING_SMALL, "przelew", font10);
        paymentWayParagraph.setIndentationLeft(PARAGRAPH_INDENTION);
        cell.addElement(paymentWayParagraph);
        table.addCell(cell);
        cell = new PdfPCell();
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);
        document.add(table);

        // Payment deadline/ gross calculatingway headers
        table = new PdfPTable(2);
        table.setWidthPercentage(100);
        PdfPCell paymentDeadlineHeader = new PdfPCell(new Phrase(resource.getString("invoice.header.payment.date"), font10b));
        paymentDeadlineHeader.setBorder(PdfPCell.NO_BORDER);
        paymentDeadlineHeader.setCellEvent(new MyBorder(SIDE.LEFT));
        table.addCell(paymentDeadlineHeader);
        PdfPCell grossCalculatingWayHeader = new PdfPCell(new Phrase(resource.getString("invoice.header.vat.calculationway"), font10b));
        grossCalculatingWayHeader.setBorder(PdfPCell.NO_BORDER);
        grossCalculatingWayHeader.setCellEvent(new MyBorder(SIDE.LEFT));
        table.addCell(grossCalculatingWayHeader);
        document.add(table);

        // Payment deadline/ gross calculatingway
        table = new PdfPTable(2);
        table.setWidthPercentage(100);
        cell = new PdfPCell();
        cell.setBorder(PdfPCell.NO_BORDER);
        Paragraph paymentDeadlineParagraph = new Paragraph(PARAGRAPH_LEADING_SMALL,
                convertDate(invoice.getPaymentDeadline(), "yyyy-MM-dd"), font10);
        paymentDeadlineParagraph.setIndentationLeft(PARAGRAPH_INDENTION);
        cell.addElement(paymentDeadlineParagraph);
        table.addCell(cell);
        cell = new PdfPCell();
        cell.setBorder(PdfPCell.NO_BORDER);
        Paragraph grossCalculatingWayParagraph = new Paragraph(PARAGRAPH_LEADING_SMALL,
                resource.getString("invoice.vat.calculationway.text"), font10);
        grossCalculatingWayParagraph.setIndentationLeft(PARAGRAPH_INDENTION);
        cell.addElement(grossCalculatingWayParagraph);
        table.addCell(cell);
        table.setSpacingAfter(20);
        document.add(table);

        //Items table

        // column headers
        table = new PdfPTable(10);
        table.setWidthPercentage(100);
        table.setSplitLate(true);

        table.setWidths(relativeColumnWidthsForItemsTable);
        table.addCell(getTableHeader(resource.getString("invoice.items.table.column.header.lp")));
        table.addCell(getTableHeader(resource.getString("invoice.items.table.column.header.product")));
        table.addCell(getTableHeader(resource.getString("invoice.items.table.column.header.measureunit")));
        table.addCell(getTableHeader(resource.getString("invoice.items.table.column.header.quantity")));
        table.addCell(getTableHeader(resource.getString("invoice.items.table.column.header.discount")));
        table.addCell(getTableHeader(resource.getString("invoice.items.table.column.header.unitprice")));
        table.addCell(getTableHeader(resource.getString("invoice.items.table.column.header.nettovalue")));
        PdfPCell vatValueCell = getCell(resource.getString("invoice.items.table.column.header.vat"));
        vatValueCell.setColspan(2);
        table.addCell(vatValueCell);
        table.addCell(getTableHeader(resource.getString("invoice.items.table.column.header.grossvalue")));
        table.addCell(getCell(resource.getString("invoice.items.table.column.header.vat.percentage"), Element.ALIGN_CENTER, font10b));
        table.addCell(getCell(resource.getString("invoice.items.table.column.header.vat.value"), Element.ALIGN_CENTER, font10b));
        document.add(table);

        //items rows
        table = new PdfPTable(10);
        table.setWidthPercentage(100);
        table.setSplitLate(true);
        table.setWidths(relativeColumnWidthsForItemsTable);
        for (int i = 0; i < invoice.getItems().size(); i++) {
            ItemTo item = invoice.getItems().get(i);
            ProductTo product = item.getProduct();
            table.addCell(getCell(String.valueOf(i + 1), Element.ALIGN_CENTER, font10));
            table.addCell(getCell(product.getName(), Element.ALIGN_LEFT, font10));
            table.addCell(getCell(product.getMeasureUnit().name(), Element.ALIGN_CENTER, font10));
            table.addCell(getCell(String.valueOf(item.getQuantity()), Element.ALIGN_CENTER, font10));
            table.addCell(getCell("0", Element.ALIGN_CENTER, font10));
            table.addCell(getCell(String.valueOf(product.getUnitPriceNetto().getAmount()), Element.ALIGN_RIGHT, font10));
            table.addCell(getCell(String.valueOf(item.getTotalNetto().getAmount()), Element.ALIGN_RIGHT, font10));
            table.addCell(getCell(String.valueOf(product.getTaxRate().getDesc()), Element.ALIGN_RIGHT, font10));
            table.addCell(getCell(String.valueOf(item.getTaxValue().getAmount()), Element.ALIGN_CENTER, font10));
            table.addCell(getCell(String.valueOf(item.getTotalGross().getAmount()), Element.ALIGN_CENTER, font10));
        }
        document.add(table);

        //summary
        table = new PdfPTable(10);
        table.setWidthPercentage(100);
        table.setWidths(relativeColumnWidthsForItemsTable);
        for(int i =0; i< 7; i++){
            table.addCell(getEmptyCell());
        }
        Set<TaxRateTo> taxRatesInInvoice = invoice.getItems().stream().map(ItemTo::getProduct).map(ProductTo::getTaxRate).collect(Collectors.toSet());
        for (TaxRateTo taxRate : taxRatesInInvoice) {
            for(int i =0; i< 7; i++){
                table.addCell(getEmptyCell());
            }
            double taxValueSum = invoice.getItems().stream().filter(item -> item.getProduct().getTaxRate().equals(taxRate)).map(ItemTo::getQuantity).reduce((i, j) -> i + j).get();
        }
        // XML version
        InvoiceDOM dom = new InvoiceDOM(basic);
        PdfDictionary parameters = new PdfDictionary();
        parameters.put(PdfName.MODDATE, new PdfDate());
        PdfFileSpecification fileSpec = writer.addFileAttachment(
                "ZUGFeRD invoice", dom.toXML(), null,
                "ZUGFeRD-invoice.xml", "application/xml",
                AFRelationshipValue.Alternative, parameters);
        PdfArray array = new PdfArray();
        array.add(fileSpec.getReference());
        writer.getExtraCatalog().put(PdfName.AF, array);
        document.close();
    }

    public PdfPCell getCompanyData(String name, String street, String postCode, String city, String NIP) {
        PdfPCell cell = new PdfPCell();
        cell.setBorder(PdfPCell.NO_BORDER);
        List<Paragraph> paragraphsToAdd = Lists.newArrayList(new Paragraph(PARAGRAPH_LEADING_SMALL, name, font10),
                new Paragraph(PARAGRAPH_LEADING_SMALL, street, font10),
                new Paragraph(PARAGRAPH_LEADING_SMALL, String.format("%s %s", postCode, city), font10),
                new Paragraph(PARAGRAPH_LEADING_SMALL, String.format("%s: %s", resource.getString("invoice.header.NIP"), NIP), font10));

        for (Paragraph paragraph : paragraphsToAdd) {
            paragraph.setIndentationLeft(PARAGRAPH_INDENTION);
            cell.addElement(paragraph);
        }
        return cell;
    }

    public PdfPCell getBankAccountData(String name, String branch, String accountNumber) {
        PdfPCell cell = new PdfPCell();
        cell.setBorder(PdfPCell.NO_BORDER);
        List<Paragraph> paragraphsToAdd = Lists.newArrayList(new Paragraph(PARAGRAPH_LEADING_SMALL, name, font10),
                new Paragraph(PARAGRAPH_LEADING_SMALL, branch, font10),
                new Paragraph(PARAGRAPH_LEADING_SMALL, accountNumber, font10));

        for (Paragraph paragraph : paragraphsToAdd) {
            paragraph.setIndentationLeft(PARAGRAPH_INDENTION);
            cell.addElement(paragraph);
        }
        return cell;
    }

    public PdfPCell getPartyTax(String[] taxId, String[] taxSchema) {
        PdfPCell cell = new PdfPCell();
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.addElement(new Paragraph("Tax ID(s):", font10b));
        if (taxId.length == 0) {
            cell.addElement(new Paragraph("Not applicable", font10));
        } else {
            int n = taxId.length;
            for (int i = 0; i < n; i++) {
                cell.addElement(new Paragraph(String.format("%s: %s", taxSchema[i], taxId[i]), font10));
            }
        }
        return cell;
    }

    public PdfPTable getTotalsTable(String tBase, String tTax, String tTotal, String tCurrency,
                                    String[] type, String[] percentage, String base[], String tax[], String currency[]) throws DocumentException {
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.setWidths(new int[]{1, 1, 3, 3, 3, 1});
        table.addCell(getCell("TAX", Element.ALIGN_LEFT, font12b));
        table.addCell(getCell("%", Element.ALIGN_RIGHT, font12b));
        table.addCell(getCell("Base amount:", Element.ALIGN_LEFT, font12b));
        table.addCell(getCell("Tax amount:", Element.ALIGN_LEFT, font12b));
        table.addCell(getCell("Total:", Element.ALIGN_LEFT, font12b));
        table.addCell(getCell("", Element.ALIGN_LEFT, font12b));
        int n = type.length;
        for (int i = 0; i < n; i++) {
            table.addCell(getCell(type[i], Element.ALIGN_RIGHT, font12));
            table.addCell(getCell(percentage[i], Element.ALIGN_RIGHT, font12));
            table.addCell(getCell(base[i], Element.ALIGN_RIGHT, font12));
            table.addCell(getCell(tax[i], Element.ALIGN_RIGHT, font12));
            double total = Double.parseDouble(base[i]) + Double.parseDouble(tax[i]);
            table.addCell(getCell("roundedTotal", Element.ALIGN_RIGHT, font12));
            table.addCell(getCell(currency[i], Element.ALIGN_LEFT, font12));
        }
        PdfPCell cell = getCell("", Element.ALIGN_LEFT, font12b);
        cell.setColspan(2);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);
        table.addCell(getCell(tBase, Element.ALIGN_RIGHT, font12b));
        table.addCell(getCell(tTax, Element.ALIGN_RIGHT, font12b));
        table.addCell(getCell(tTotal, Element.ALIGN_RIGHT, font12b));
        table.addCell(getCell(tCurrency, Element.ALIGN_LEFT, font12b));
        return table;
    }

    public PdfPCell getCell(String value, int alignment, Font font) {
        PdfPCell cell = new PdfPCell();
        cell.setUseAscender(true);
        cell.setUseDescender(true);
        Paragraph p = new Paragraph(value, font);
        p.setAlignment(alignment);
        cell.addElement(p);
        return cell;
    }

    public PdfPCell getCell(String value) {
        PdfPCell cell = new PdfPCell();
        cell.setUseAscender(true);
        cell.setUseDescender(true);
        Paragraph p = new Paragraph(value, font10);
        p.setAlignment(Element.ALIGN_CENTER);
        cell.addElement(p);
        return cell;
    }

    public PdfPCell getEmptyCell() {
        PdfPCell cell = new PdfPCell();
        cell.setUseAscender(true);
        cell.setUseDescender(true);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    public PdfPCell getTableHeader(String value) {
        PdfPCell cell = getCell(value, Element.ALIGN_CENTER, font10b);
        cell.setRowspan(2);
        return cell;
    }

    public Paragraph getPaymentInfo(String ref, String[] bic, String[] iban) {
        Paragraph p = new Paragraph(String.format(
                "Please wire the amount due to our bank account using the following reference: %s",
                ref), font12);
        int n = bic.length;
        for (int i = 0; i < n; i++) {
            p.add(Chunk.NEWLINE);
            p.add(String.format("BIC: %s - IBAN: %s", bic[i], iban[i]));
        }
        return p;
    }

    public String convertDate(Date d, String newFormat) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(newFormat);
        return sdf.format(d);
    }

    public class MyBorder implements PdfPCellEvent {
        private SIDE relativePosition;

        public MyBorder(SIDE relativePosition) {
            this.relativePosition = relativePosition;
        }

        @Override
        public void cellLayout(PdfPCell cell, Rectangle position,
                               PdfContentByte[] canvases) {
            float x1 = position.getLeft();
            float x2 = position.getRight();
            float y1 = position.getTop() - 2;
            float y2 = position.getBottom();
            switch (relativePosition) {
                case RIGHT:
                    x1 = x1 + 5;
                    break;
                case LEFT:
                    x2 = x2 - 5;
                    break;
                default:
                    break;
            }
            PdfContentByte canvas = canvases[PdfPTable.LINECANVAS];
            canvas.rectangle(x1, y1, x2 - x1, y2 - y1);
            canvas.stroke();
        }

    }

    public enum SIDE {
        LEFT,
        RIGHT
    }
}
