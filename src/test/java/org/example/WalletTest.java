package org.example;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WalletTest {
    private Wallet wallet; // Mendeklarasikan variabel wallet yang akan digunakan dalam pengujian.
    private final String ownerName = "Salwa Jasmine"; // Mendeklarasikan nama pemilik dompet.
    private final String bankName = "BNI"; //  Mendeklarasikan nama bank.
    private final String cardAccountNumber = "1234-8765-4321"; // Mendeklarasikan nomor akun kartu.
    @BeforeAll
    void initClass() { // Metode ini dijalankan sekali sebelum semua tes dimulai. Digunakan untuk menginisialisasi objek Wallet.
        wallet = new Wallet(ownerName);
        System.out.println("@BeforeAll is called");
    }
    @BeforeEach
    void initMethod() { // Metode ini dijalankan sebelum setiap tes. Digunakan untuk mengatur nama pemilik dompet dan menambahkan kartu ke dalam dompet.
        wallet.setOwnerName(ownerName);
        wallet.addCard(bankName, cardAccountNumber);
        System.out.println("@BeforeEach is called");
    }
    @AfterEach
    void cleanMethod() { // Metode ini dijalankan setelah setiap tes. Digunakan untuk menghapus semua kartu dari dompet.
        wallet.removeAllCards();
        System.out.println("@AfterEach is called");
    }
    @AfterAll
    void cleanClass() { // Metode ini dijalankan sekali setelah semua tes selesai. Digunakan untuk membersihkan sumber daya atau melakukan tindakan bersih.
        System.out.println("@AfterAll is called");
    }
    @Test
    public void testSetDataOwner() { // Menguji metode setOwnerName untuk memastikan data pemilik dapat diatur dengan benar.
        assertAll(
                () ->  assertNotNull(wallet.getOwnerName()),
                () -> assertEquals(ownerName, wallet.getOwnerName())
        );
    }
    @Test
    public void testChangeOwnerName() { // Menguji pengubahan nama pemilik dompet.
        wallet.setOwnerName("Aaliyah");
        assertAll(
                () ->  assertNotNull(wallet.getOwnerName()),
                () -> assertEquals("Aaliyah", wallet.getOwnerName())
        );
        wallet.setOwnerName("");
        assertAll(
                () ->  assertNotNull(wallet.getOwnerName()),
                () -> assertEquals("", wallet.getOwnerName())
        );
    }
    @Test
    public void testAddCard() { // Menguji penambahan kartu ke dalam dompet.
        Card addedCard = wallet.getCards().get(0);

        // Assert
        assertAll(
                () -> assertEquals(1, wallet.getCards().size()),
                () -> assertEquals(ownerName, addedCard.getOwnerName()),
                () -> assertEquals(bankName, addedCard.getBank()),
                () -> assertEquals(cardAccountNumber, addedCard.getAccountNumber())
        );
    }
    @Test
    public void testRemoveCard() { // Menguji penghapusan kartu dari dompet.
        // Act
        wallet.removeCard(cardAccountNumber);

        // Assert
        assertAll(
                () -> assertEquals(0, wallet.getCards().size()),
                () -> assertNull(findCardByAccountNumber(wallet))
        );
    }
    private Card findCardByAccountNumber(Wallet wallet) {
        for (Card card : wallet.getCards()) {
            if (card.getAccountNumber().equals("1234-8765-4321")) {
                return card;
            }
        }
        return null;
    }

    @Test
    public void testDeposit() { // Menguji metode depositCash untuk memastikan uang tunai dapat disetor dengan benar.
        // Act & Assert: Deposit a valid amount
        int validAmount = 5000;
        int depositedAmount = wallet.depositCash(validAmount);

        assertEquals(validAmount, depositedAmount);
        assertEquals(1, wallet.getCashMap().get(validAmount).intValue());
        System.out.println("Current Cash Map: " + wallet.getCashMap());


        // Act & Assert: Deposit an invalid amount
        int invalidAmount = 0;
        depositedAmount = wallet.depositCash(invalidAmount);

        assertEquals(0, depositedAmount);
        assertNull(wallet.getCashMap().get(invalidAmount));
        System.out.println("Current Cash Map: " + wallet.getCashMap());


        // Act & Assert: Deposit using an invalid denomination
        int invalidDenomination = 3000;
        depositedAmount = wallet.depositCash(invalidDenomination);

        assertEquals(0, depositedAmount);
        assertNull(wallet.getCashMap().get(invalidDenomination));
        System.out.println("Current Cash Map: " + wallet.getCashMap());
    }

    @Test
    public void testWithdrawCash() { // Menguji metode withdrawCash untuk memastikan uang tunai dapat ditarik dengan benar.
        int validAmount = 1000;
        wallet.depositCash(validAmount);

        // Act & Assert: Withdraw a valid amount
        int withdrawnAmount = wallet.withdrawCash(validAmount);

        assertEquals(validAmount, withdrawnAmount);
        assertEquals(0, wallet.getCashMap().get(validAmount).intValue());
        System.out.println("1 - Current Cash Map: " + wallet.getCashMap());


        // Act & Assert: Withdraw an invalid amount
        int invalidAmount = 0;
        withdrawnAmount = wallet.withdrawCash(invalidAmount);

        assertEquals(0, withdrawnAmount);
        assertNull(wallet.getCashMap().get(invalidAmount));
        System.out.println("2 - Current Cash Map: " + wallet.getCashMap());


        // Act & Assert: Withdraw using an invalid denomination
        int invalidDenomination = 3000;
        withdrawnAmount = wallet.withdrawCash(invalidDenomination);

        assertEquals(0, withdrawnAmount);
        assertNull(wallet.getCashMap().get(invalidDenomination));
        System.out.println("3 - Current Cash Map: " + wallet.getCashMap());


        // Act & Assert: Withdraw from a valid but empty denomination
        int emptyDenomination = 100000;
        withdrawnAmount = wallet.withdrawCash(emptyDenomination);

        assertEquals(0, withdrawnAmount);
        assertNotNull(wallet.getCashMap().get(emptyDenomination));
        System.out.println("4 - Current Cash Map: " + wallet.getCashMap());
    }

    @Test
    public void testCalculateTotalCash() { // Menguji perhitungan total uang tunai dalam dompet.
        Integer amount1 = 10000;
        wallet.depositCash(amount1);

        // Assert
        assertEquals(amount1, wallet.calculateTotalCash());

        // Act & Assert: Sum multiple cash
        Integer amount2 = 20000;
        Integer amount3 = 1000;
        Integer amount4 = 100;
        wallet.depositCash(amount2);
        wallet.depositCash(amount3);
        wallet.depositCash(amount4);
        assertEquals(31100, wallet.calculateTotalCash());
    }
}