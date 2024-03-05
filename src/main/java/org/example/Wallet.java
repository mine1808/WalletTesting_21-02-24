package org.example;

import java.util.*;

public class Wallet {

    private String ownerName; // Memiliki atribut ownerName yang menyimpan nama pemilik dompet.
    private List<Card> cards = new ArrayList<>(); // Memiliki atribut cards yang merupakan daftar kartu yang dimiliki oleh pemilik dompet.
    private HashMap<Integer, Integer> cashMap = new HashMap<>(); // Memiliki atribut cashMap yang merupakan peta untuk menyimpan jumlah uang tunai dalam berbagai denominasi.

    public Wallet(String ownerName) {
        this.setOwnerName(ownerName);
        initializeCash();
    }
    public Wallet(String ownerName, List<Card> cards, HashMap<Integer, Integer> cash) {
        this.setOwnerName(ownerName);
        this.setCards(cards);
        this.setCashMap(new HashMap<>());
        initializeCash();
    }
    private void initializeCash() { // Menginisialisasi peta cashMap dengan denominasi uang tunai dan jumlah awal nol.
        this.cashMap.put(100, 0);
        this.cashMap.put(200, 0);
        this.cashMap.put(500, 0);
        this.cashMap.put(1000, 0);
        this.cashMap.put(2000, 0);
        this.cashMap.put(5000, 0);
        this.cashMap.put(10000, 0);
        this.cashMap.put(20000, 0);
        this.cashMap.put(50000, 0);
        this.cashMap.put(100000, 0);
    }

    public String getOwnerName() {
        return ownerName;
    }
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void addCard(String bank, String accountNumber) { // Menambahkan kartu baru ke dalam dompet.
        Card card = new Card();
        card.setOwnerName(this.ownerName);
        card.setBank(bank);
        card.setAccountNumber(accountNumber);
        this.cards.add(card);
    }
    public void removeCard(String accountNumber) { // Menghapus kartu dari dompet berdasarkan nomor akun.
        Iterator<Card> iterator = getCards().iterator();
        while (iterator.hasNext()) {
            Card card = iterator.next();
            if (card.getAccountNumber().equals(accountNumber)) {
                iterator.remove();
            }
        }

    }
    public void removeAllCards() { // Menyetorkan uang tunai ke dalam dompet
        cards.clear();
    }
    public Integer depositCash(Integer amount) {
        if (amount <= 0) {
            System.out.println("Jumlah setoran tidak valid. Jumlahnya harus lebih besar dari nol.");
            return 0;
        }

        Integer currentCashAmount = cashMap.get(amount);
        if (currentCashAmount == null) {
            System.out.println("Denominasi setoran tidak valid. Silakan menyetorkan denominasi tunai yang valid.");
            return 0;
        }

        cashMap.put(amount, currentCashAmount + 1);
        System.out.println("Berhasil menyetor Rp." + amount);
        return amount;
    }
    public Integer withdrawCash(Integer amount) { // Menarik uang tunai dari dompet.
        if (amount <= 0) {
            System.out.println("Jumlah penarikan tidak valid. Jumlahnya harus lebih besar dari nol.");
            return 0;
        }

        if (cashMap.containsKey(amount) && cashMap.get(amount) > 0) {
            Integer currentCashAmount = cashMap.get(amount);
            cashMap.put(amount, currentCashAmount - 1);
            System.out.println("Penarikan berhasil sebesar Rp." + amount);
            return amount;
        } else {
            System.out.println("Uang tidak tersedia untuk penarikan atau dana tidak mencukupi.");
            return 0;
        }
    }

    public Integer calculateTotalCash() { // Menghitung total uang tunai yang ada dalam dompet.
        Integer totalCash = 0;

        for (Map.Entry<Integer, Integer> entry : cashMap.entrySet()) {
            Integer denomination = entry.getKey();
            Integer count = entry.getValue();
            totalCash += denomination * count;
        }

        System.out.println("Total uang: Rp." + totalCash);
        return totalCash;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
    public HashMap<Integer, Integer> getCashMap() {
        return cashMap;
    }
    public void setCashMap(HashMap<Integer, Integer> cashMap) {
        this.cashMap = cashMap;
    }
}