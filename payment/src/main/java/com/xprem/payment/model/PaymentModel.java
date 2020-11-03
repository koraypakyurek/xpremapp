package com.xprem.payment.model;

public class PaymentModel {
    private String nameLastName;
    private String creditCardNo;
    private int expMont;
    private int expYear;
    private int cvc;
    private String mail;

    public PaymentModel() {
    }

    public String getNameLastName() {
        return nameLastName;
    }

    public void setNameLastName(String nameLastName) {
        this.nameLastName = nameLastName;
    }

    public String getCreditCardNo() {
        return creditCardNo;
    }

    public void setCreditCardNo(String creditCardNo) {
        this.creditCardNo = creditCardNo;
    }

    public int getExpMont() {
        return expMont;
    }

    public void setExpMont(int expMont) {
        this.expMont = expMont;
    }

    public int getExpYear() {
        return expYear;
    }

    public void setExpYear(int expYear) {
        this.expYear = expYear;
    }

    public int getCvc() {
        return cvc;
    }

    public void setCvc(int cvc) {
        this.cvc = cvc;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
