package ee.bitweb.ogone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public abstract class AbstractPaymentRequest extends AbstractRequest {

    protected HashMap<String, String> brandsMap;

    {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("Acceptgiro", "Acceptgiro");
        map.put("AIRPLUS", "CreditCard");
        map.put("American Express", "CreditCard");
        map.put("Aurora", "CreditCard");
        map.put("Aurore", "CreditCard");
        map.put("Bank transfer", "Bank transfer");
        map.put("BCMC", "CreditCard");
        map.put("Belfius Direct Net", "Belfius Direct Net");
        map.put("Billy", "CreditCard");
        map.put("cashU", "cashU");
        map.put("CB", "CreditCard");
        map.put("CBC Online", "CBC Online");
        map.put("CENTEA Online", "CENTEA Online");
        map.put("Cofinoga", "CreditCard");
        map.put("Dankort", "CreditCard");
        map.put("Dexia Direct Net", "Dexia Direct Net");
        map.put("Diners Club", "CreditCard");
        map.put("Direct Debits AT", "Direct Debits AT");
        map.put("Direct Debits DE", "Direct Debits DE");
        map.put("Direct Debits NL", "Direct Debits NL");
        map.put("eDankort", "eDankort");
        map.put("EPS", "EPS");
        map.put("Fortis Pay Button", "Fortis Pay Button");
        map.put("giropay", "giropay");
        map.put("iDEAL", "iDEAL");
        map.put("ING HomePay", "ING HomePay");
        map.put("InterSolve", "InterSolve");
        map.put("JCB", "CreditCard");
        map.put("KBC Online", "KBC Online");
        map.put("Maestro", "CreditCard");
        map.put("MaestroUK", "CreditCard");
        map.put("MasterCard", "CreditCard");
        map.put("MiniTix", "MiniTix");
        map.put("MPASS", "MPASS");
        map.put("NetReserve", "CreditCard");
        map.put("Payment on Delivery", "Payment on Delivery");
        map.put("PAYPAL", "PAYPAL");
        map.put("paysafecard", "paysafecard");
        map.put("PingPing", "PingPing");
        map.put("PostFinance + card", "PostFinance Card");
        map.put("PostFinance e-finance", "PostFinance e-finance");
        map.put("PRIVILEGE", "CreditCard");
        map.put("Sofort Uberweisung", "DirectEbanking");
        map.put("Solo", "CreditCard");
        map.put("TUNZ", "TUNZ");
        map.put("UATP", "CreditCard");
        map.put("UNEUROCOM", "UNEUROCOM");
        map.put("VISA", "CreditCard");
        map.put("Wallie", "Wallie");
        this.brandsMap = map;
    }

    public ArrayList<String> allowedCurrencies = new ArrayList<String>(Arrays.asList(
        "AED", "ANG", "ARS", "AUD", "AWG", "BGN", "BRL", "BYR", "CAD", "CHF", "CNY", "CZK",
        "DKK", "EEK", "EGP", "EUR", "GBP", "GEL", "HKD", "HRK", "HUF", "ILS", "ISK", "JPY",
        "KRW", "LTL", "LVL", "MAD", "MXN", "NOK", "NZD", "PLN", "RON", "RUB", "SEK", "SGD",
        "SKK", "THB", "TRY", "UAH", "USD", "XAF", "XOF", "XPF", "ZAR"
    ));

    public void setOrderId(String orderId) {
        if (orderId.length() > 30) {
            throw new IllegalArgumentException("Orderid cannot be longer than 30 characters");
        }
        if (!orderId.matches("^[a-zA-Z0-9_-]+")) {
            throw new IllegalArgumentException("Order id cannot contain special characters");
        }
        setParameter("orderid", orderId);
    }

    public void setOrderDescription(String orderDescription) {
        setCom(orderDescription);
    }

    public void setCom(String com) {
        if (com.length() > 100) {
            throw new IllegalArgumentException("Order description cannot be longer than 100 characters");
        }
        setParameter("com", com);
    }

    /**
     * Set amount in cents, eg EUR 12.34 is written as 1234
     */
    public void setAmount(Integer amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be a positive number");
        }
        if (amount >= 2147483647) {
            throw new IllegalArgumentException("Amount is too high");
        }
        setParameter("amount", amount);
    }

    public void setEmail(String email) {
        if (email.length() > 50) {
            throw new IllegalArgumentException("Email is too long");
        }
        setParameter("email", email);
    }
    public void setOwnedAddress(String ownerAddress) {
        if (ownerAddress.length() > 35) {
            throw new IllegalArgumentException("Owner address is too long");
        }
        setParameter("owneraddress", ownerAddress);
    }

    public void setOwnerZip(String ownerZip) {
        if (ownerZip.length() > 10) {
            throw new IllegalArgumentException("Owner Zip is too long");
        }
        setParameter("ownerzip", ownerZip);
    }

    public void setOwnerTown(String ownerTown) {
        setParameter("ownertown", ownerTown);
    }

    public void setOwnerCountry(String ownerCountry) {
        setOwnerCty(ownerCountry);
    }

    public void setOwnerCty(String ownerCountry) {
        if (!(ownerCountry.matches("^[A-Z]{2}$"))) {
            throw new IllegalArgumentException("Illegal country code");
        }
        setParameter("ownercty", ownerCountry);
    }

    public void setOwnerPhone(String ownerPhone) {
        setOwnerTelNo(ownerPhone);
    }

    public void setOwnerTelNo(String ownerTelNo) {
        if (ownerTelNo.length() > 30) {
            throw new IllegalArgumentException("Owner phone is too long");
        }
        setParameter("ownertelno", ownerTelNo);
    }

    public void setFeedbackMessage(String feedbackMessage) {
        setComplus(feedbackMessage);
    }

    public void setComplus(String complus) {
        setParameter("complus", complus);
    }

    public void setBrand(String brand) {
        if (!brandsMap.containsKey(brand)) {
            throw new IllegalArgumentException("Unknown Brand: " + brand);
        }
        setPaymentMethod(brandsMap.get(brand));
        setParameter("brand", brand);
    }

    public void setPaymentMethod(String paymentMethod) {
        setPm(paymentMethod);
    }

    public void setPm(String pm) {
        if (!brandsMap.containsValue(pm)) {
            throw new IllegalArgumentException("Unknown Payment method: " + pm);
        }
        setParameter("pm", pm);
    }

    public void setDynamicTemplateUri(String uri) {
        setTp(uri);
    }

    public void setTp(String tp) {
        validateUri(tp);
        setParameter("tp", tp);
    }
}
