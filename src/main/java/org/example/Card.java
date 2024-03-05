package org.example;

public class Card {
    private String ownerName;
    private String accountNumber;
    private String bank;

    public String getAccountNumber() { // Metode ini mengembalikan nilai dari atribut accountNumber. Ini adalah metode getter untuk accountNumber.
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) { // Metode ini digunakan untuk mengatur nilai atribut accountNumber dengan nilai yang diberikan sebagai argumen. Ini adalah metode setter untuk accountNumber.
        this.accountNumber = accountNumber;
    }

    public String getBank() { // Metode ini mengembalikan nilai dari atribut bank. Ini adalah metode getter untuk bank.
        return bank;
    }

    public void setBank(String bank) { //  Metode ini digunakan untuk mengatur nilai atribut bank dengan nilai yang diberikan sebagai argumen. Ini adalah metode setter untuk bank.
        this.bank = bank;
    }

    public String getOwnerName() { // Metode ini mengembalikan nilai dari atribut ownerName. Ini adalah metode getter untuk ownerName.
        return ownerName;
    }

    public void setOwnerName(String ownerName) { // Metode ini digunakan untuk mengatur nilai atribut ownerName dengan nilai yang diberikan sebagai argumen. Ini adalah metode setter untuk ownerName.
        this.ownerName = ownerName;
    }
}
