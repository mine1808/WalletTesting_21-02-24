package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WalletTest {

        @Test
        void TestSetOwner(){
            Wallet wallet = new Wallet("Salwa");
            // Mengecek bahwa nilai owner sama seperti saat awal membuat objek
            Assertions.assertEquals("Salwa", wallet.getOwner());

            // mengubah nilai owner dengan setOwner
            wallet.setOwner("Jasmine");

            Assertions.assertAll(
                    // mengecek bahwa nilai owner tetap ada setelah diubah
                    () -> Assertions.assertNotNull(wallet.getOwner()),
                    // mengecek bahwa nilai owner berhasil untuk diubah
                    () -> Assertions.assertEquals("Jasmine", wallet.getOwner())
            );
        }

        @Test
        void TestTambahKartu(){
            Wallet wallet = new Wallet("Salwa");
            // menambahkan kartu
            wallet.tambahKartu("MANDIRI");


            Assertions.assertAll(
                    // mengecek bahwa list tidak bernilai kosong
                    () -> Assertions.assertNotNull(wallet.getListKartu()),
                    // mengecek bahwa kartu yang ditambahkan telah masuk ke list
                    () -> Assertions.assertTrue(wallet.getListKartu().contains("MANDIRI"))

            );
        }

        @Test
        void TestAmbilKartu(){
            Wallet wallet = new Wallet("Salwa");
            wallet.tambahKartu("MANDIRI");

            // mengambil kartu yang ada di dalam list
            wallet.ambilKartu("MANDIRI");

            // mengecek bahwa kartu yang diambil sudah tidak ada lagi di dalam list
            Assertions.assertFalse(wallet.getListUangKoin().contains("MANDIRI"));
        }

        @Test
        void TestTambahUang(){
            Wallet wallet = new Wallet("Salwa");
            // menambahkan saldo uang
            wallet.tambahUang(200000);
            wallet.tambahUang(100000);

            // mengecek bahwa uang bernilai > 1000 masuk ke dalam list uang lembaran
            Assertions.assertTrue(wallet.getListUangLembaran().contains(200000));
            // mengecek bahwa uang bernilai < 1000 masuk ke dalam list uang koin
            Assertions.assertFalse(wallet.getListUangKoin().contains(100000));
        }

        @Test
        void TestAmbilUang(){
            Wallet wallet = new Wallet("Salwa");
            wallet.tambahUang(200000);
            // mengambil uang yang terdapat pada list
            wallet.ambilUang(200000);

            // mengecek bahwa uang yang diambil sudah tidak ada lagi di dalam list
            Assertions.assertFalse(wallet.getListUangLembaran().contains(200000));
        }

        @Test
        void TestTampilkanUang(){
            Wallet wallet = new Wallet("Salwa");
            // menambahkan uang
            wallet.tambahUang(200000);
            wallet.tambahUang(100000);
            wallet.tambahUang(200000);

            // mengecek bahwa jumlah uang yang terdapat pada wallet
            Assertions.assertEquals(500000, wallet.tampilkanUang());
        }
    }