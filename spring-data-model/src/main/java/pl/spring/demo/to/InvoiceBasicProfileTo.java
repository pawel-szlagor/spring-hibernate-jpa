package pl.spring.demo.to;

import java.util.Date;
import java.util.List;

import com.itextpdf.text.zugferd.checkers.basic.DateFormatCode;
import com.itextpdf.text.zugferd.checkers.basic.DocumentTypeCode;
import com.itextpdf.text.zugferd.profiles.BasicProfile;
import org.joda.money.Money;

/**
 * Created by Paweł on 30.01.2016.
 */
public class InvoiceBasicProfileTo extends InvoiceTo implements BasicProfile {


    @Override
    public boolean getTestIndicator() {
        return true;
    }

    @Override
    public String getId() {
        return Long.toString(id);
    }

    @Override
    public String getName() {
        return "InvoiceName";
    }

    @Override
    public String getTypeCode() {
        return DocumentTypeCode.COMMERCIAL_INVOICE;
    }

    @Override
    public Date getDateTime() {
        return creationDate;
    }

    @Override
    public String getDateTimeFormat() {
        return DateFormatCode.YYYYMMDD;
    }

    @Override
    public String[][] getNotes() {
        return new String[0][];
    }

    @Override
    public String getSellerName() {
        return customer.getName();
    }

    @Override
    public String getSellerPostcode() {
        return customer.getPostCode();
    }

    @Override
    public String getSellerLineOne() {
        return customer.getName();
    }

    @Override
    public String getSellerLineTwo() {
        return customer.getStreet();
    }

    @Override
    public String getSellerCityName() {
        return customer.getName();
    }

    @Override
    public String getSellerCountryID() {
        return "PL";
    }

    @Override
    public String[] getSellerTaxRegistrationID() {
        return new String[0];
    }

    @Override
    public String[] getSellerTaxRegistrationSchemeID() {
        return new String[0];
    }

    @Override
    public String getBuyerName() {
        return "BuyerName";
    }

    @Override
    public String getBuyerPostcode() {
        return "BUyerPostCode";
    }

    @Override
    public String getBuyerLineOne() {
        return "BuyerName1";
    }

    @Override
    public String getBuyerLineTwo() {
        return "BuyerName2";
    }

    @Override
    public String getBuyerCityName() {
        return "BuyerCityName";
    }

    @Override
    public String getBuyerCountryID() {
        return "PL";
    }

    @Override
    public String[] getBuyerTaxRegistrationID() {
        return new String[0];
    }

    @Override
    public String[] getBuyerTaxRegistrationSchemeID() {
        return new String[0];
    }

    @Override
    public Date getDeliveryDateTime() {
        return saleDate;
    }

    @Override
    public String getDeliveryDateTimeFormat() {
        return DateFormatCode.YYYYMMDD;
    }

    @Override
    public String getPaymentReference() {
        return "PaymentRef";
    }

    @Override
    public String getInvoiceCurrencyCode() {
        return "EUR";
    }

    @Override
    public String[] getPaymentMeansID() {
        return new String[0];
    }

    @Override
    public String[] getPaymentMeansSchemeAgencyID() {
        return new String[0];
    }

    @Override
    public String[] getPaymentMeansPayeeAccountIBAN() {
        return new String[0];
    }

    @Override
    public String[] getPaymentMeansPayeeAccountAccountName() {
        return new String[0];
    }

    @Override
    public String[] getPaymentMeansPayeeAccountProprietaryID() {
        return new String[0];
    }

    @Override
    public String[] getPaymentMeansPayeeFinancialInstitutionBIC() {
        return new String[0];
    }

    @Override
    public String[] getPaymentMeansPayeeFinancialInstitutionGermanBankleitzahlID() {
        return new String[0];
    }

    @Override
    public String[] getPaymentMeansPayeeFinancialInstitutionName() {
        return new String[0];
    }

    @Override
    public String[] getTaxCalculatedAmount() {
        return new String[0];
    }

    @Override
    public String[] getTaxCalculatedAmountCurrencyID() {
        return new String[0];
    }

    @Override
    public String[] getTaxTypeCode() {
        return new String[0];
    }

    @Override
    public String[] getTaxBasisAmount() {
        return new String[0];
    }

    @Override
    public String[] getTaxBasisAmountCurrencyID() {
        return new String[0];
    }

    @Override
    public String[] getTaxApplicablePercent() {
        return new String[0];
    }

    @Override
    public String getLineTotalAmount() {
        return "10.10";
    }

    @Override
    public String getLineTotalAmountCurrencyID() {
        return "EUR";
    }

    @Override
    public String getChargeTotalAmount() {
        return "10.10";
    }

    @Override
    public String getChargeTotalAmountCurrencyID() {
        return "EUR";
    }

    @Override
    public String getAllowanceTotalAmount() {
        return "10.10";
    }

    @Override
    public String getAllowanceTotalAmountCurrencyID() {
        return "EUR";
    }

    @Override
    public String getTaxBasisTotalAmount() {
        return "10.10";
    }

    @Override
    public String getTaxBasisTotalAmountCurrencyID() {
        return "EUR";
    }

    @Override
    public String getTaxTotalAmount() {
        return "10.10";
    }

    @Override
    public String getTaxTotalAmountCurrencyID() {
        return "EUR";
    }

    @Override
    public String getGrandTotalAmount() {
        return "10.10";
    }

    @Override
    public String getGrandTotalAmountCurrencyID() {
        return "EUR";
    }

    @Override
    public String[] getLineItemBilledQuantity() {
        return new String[]{"1.0000", "10.1000"};
    }

    @Override
    public String[] getLineItemBilledQuantityUnitCode() {
        return new String[]{"LTR", "LTR"};
    }

    @Override
    public String[] getLineItemSpecifiedTradeProductName() {
        return new String[]{"cos", "coś2"};
    }

    public void setid(Long id) {
        this.id = id;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getPaymentDeadline() {
        return paymentDeadline;
    }

    public void setPaymentDeadline(Date paymentDeadline) {
        this.paymentDeadline = paymentDeadline;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public CustomerTo getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerTo customer) {
        this.customer = customer;
    }

    public List<ItemTo> getItems() {
        return items;
    }

    public void setItems(List<ItemTo> items) {
        this.items = items;
    }

    public Money getTotalNetto() {
        return totalNetto;
    }

    public void setTotalNetto(Money totalNetto) {
        this.totalNetto = totalNetto;
    }
}
