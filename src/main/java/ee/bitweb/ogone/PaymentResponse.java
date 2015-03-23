package ee.bitweb.ogone;

interface PaymentResponse {

    public static final int STATUS_INCOMPLETE_OR_INVALID = 0;
    public static final int STATUS_CANCELLED_BY_CLIENT = 1;
    public static final int STATUS_AUTHORISATION_REFUSED = 2;
    public static final int STATUS_ORDER_STORED = 4;
    public static final int STATUS_STORE_WAIT_EXTERNAL_RESULT = 40;
    public static final int STATUS_WAITING_FOR_CLIENT_PAYMENT  = 41;
    public static final int STATUS_AUTHORISED = 5;
    public static final int STATUS_AUTHORISED_WAITING_EXTERNAL_RESULT = 50;
    public static final int STATUS_AUTHORISATION_WAITING = 51;
    public static final int STATUS_AUTHORISATION_NOT_KNOWN = 52;
    public static final int STATUS_STANDBY = 55;
    public static final int STATUS_OK_WITH_SCHEDULED_PAYMENTS = 56;
    public static final int STATUS_NOT_OK_WITH_SCHEDULED_PAYMENTS = 57;
    public static final int STATUS_AUTHORISATION_TO_BE_REQUESTED_MANUALLY = 59;
    public static final int STATUS_AUTHORISED_AND_CANCELED = 6;
    public static final int STATUS_AUTHORISED_DELETION_WAITING = 61;
    public static final int STATUS_AUTHORISED_DELETION_UNCERTAIN = 62;
    public static final int STATUS_AUTHORISED_DELETION_REFUSED = 63;
    public static final int STATUS_AUTHORISED_AND_CANCELED2 = 64;
    public static final int STATUS_PAYMENT_DELETED = 7;
    public static final int STATUS_PAYMENT_DELETION_PENDING = 71;
    public static final int STATUS_PAYMENT_DELETION_UNCERTAIN = 72;
    public static final int STATUS_PAYMENT_DELETION_REFUSED = 73;
    public static final int STATUS_PAYMENT_DELETED2 = 74;
    public static final int STATUS_DELETION_HANDLED_BY_MERCHANT = 75;
    public static final int STATUS_REFUND = 8;
    public static final int STATUS_REFUND_PENDING = 81;
    public static final int STATUS_REFUND_UNCERTAIN = 82;
    public static final int STATUS_REFUND_REFUSED = 83;
    public static final int STATUS_REFUND2 = 84;
    public static final int STATUS_REFUND_HANDLED_BY_MERCHANT = 85;
    public static final int STATUS_PAYMENT_REQUESTED = 9;
    public static final int STATUS_PAYMENT_PROCESSING = 91;
    public static final int STATUS_PAYMENT_UNCERTAIN = 92;
    public static final int STATUS_PAYMENT_REFUSED = 93;
    public static final int STATUS_REFUND_DECLINED_BY_ACQUIRER = 94;
    public static final int STATUS_PAYMENT_HANDLED_BY_MERCHANT = 95;
    public static final int STATUS_REFUND_REVERSED = 96;
    public static final int STATUS_BEING_PROCESSED = 99;

}
