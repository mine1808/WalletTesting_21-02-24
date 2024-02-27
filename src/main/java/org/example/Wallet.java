package org.example;

import java.util.ArrayList; // Mengimpor kelas ArrayList untuk menyimpan data dalam daftar.

public class Wallet {

    String owner; // Nama pemilik wallet.
    ArrayList<String> listKartu; // Daftar nama kartu dalam wallet.
    ArrayList<Integer> listUangKoin; // Daftar nilai uang koin dalam wallet.
    ArrayList<Integer> listUangLembaran; // Daftar nilai lembaran uang dalam wallet.

    public Wallet(String owner) { // Constructor - Menginisialisasi dompet dengan nama pemilik.
        this.owner = owner;
        this.listKartu = new ArrayList<>();
        this.listUangKoin = new ArrayList<>();
        this.listUangLembaran = new ArrayList<>();
    }

    //Method Getter
    public String getOwner() { // Mengembalikan nama pemilik.
        return owner;
    }

    public ArrayList<String> getListKartu() { // Mengembalikan daftar nama kartu.
        return listKartu;
    }

    public ArrayList<Integer> getListUangKoin() { // Mengembalikan daftar nilai koin.
        return listUangKoin;
    }

    public ArrayList<Integer> getListUangLembaran() { // Mengembalikan daftar nilai lembaran uang.
        return listUangLembaran;
    }

    // Method Setter
    public void setOwner(String owner){ // Mengubah nama pemilik.
        this.owner = owner;
    }

    // Method untuk mengelola kartu
    public void tambahKartu(String namaKartu){ // Menambahkan kartu baru ke daftar.
        this.listKartu.add(namaKartu);
    }

    public String ambilKartu(String namaKartu){ // Menghapus kartu dari daftar dan mengembalikan namanya.
        boolean isDeleted = this.listKartu.remove(namaKartu);
        if (isDeleted){
            return namaKartu;
        }
        return null;
    }

    // Method untuk mengelola uang
    public void tambahUang(int jumlahUang){ // Menambahkan uang ke wallet.
        if (jumlahUang < 0 ){
            throw new Error("Jumlah uang tidak boleh minus"); // Melempar error jika jumlah uang negatif.
        }
        if (jumlahUang > 1000) {
            listUangLembaran.add(jumlahUang); // Menambahkan ke listUangLembaran jika lebih dari 1000.
        }
        else {
            listUangKoin.add(jumlahUang); // Menambahkan ke listUangKoin jika kurang dari atau sama dengan 1000.
        }
    }
    public int ambilUang(int jumlahUang) { // Mengambil uang dari wallet dan mengembalikan jumlahnya.
        boolean isUangLembaranTaken = this.listUangLembaran.remove(Integer.valueOf(jumlahUang));
        if (isUangLembaranTaken) {
            return jumlahUang;  // Mencoba mengambil dari listUangLembaran terlebih dahulu.
        }
        boolean isUangKoinTaken = this.listUangKoin.remove(Integer.valueOf(jumlahUang)); // Mencoba mengambil dari listUangKoin jika tidak ada di listUangLembaran.
        if (isUangKoinTaken) {
            return jumlahUang;
        }
        return 0; // Mengembalikan 0 jika tidak ada uang yang cukup.
    }
    public int tampilkanUang(){ // Menghitung dan mengembalikan total uang dalam wallet.
        int totalUang = 0;
        for (Integer uang : listUangKoin){
            totalUang += uang;
        }
        for (Integer uang : listUangLembaran){
            totalUang += uang;
        }
        return totalUang;
    }
}