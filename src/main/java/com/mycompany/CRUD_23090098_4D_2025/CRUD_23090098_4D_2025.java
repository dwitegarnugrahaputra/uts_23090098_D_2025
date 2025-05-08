package com.mycompany.CRUD_23090098_4D_2025;

import com.mongodb.client.*; // Import MongoDB Client
import org.bson.Document; // Import Document class untuk membuat dokumen
import com.mongodb.client.model.Filters; // Import Filters untuk pencarian dan update

public class CRUD_23090098_4D_2025 {

    public static void main(String[] args) {
        // Membuat koneksi ke MongoDB pada localhost port 27017
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");

        // Memilih database 'belajar'
        MongoDatabase database = mongoClient.getDatabase("belajar");

        // Memilih koleksi 'belajar_crud'
        MongoCollection<Document> collection = database.getCollection("belajar_crud");

        // ======== CREATE ========

        // Membuat document pertama dengan 3 field
        Document doc1 = new Document("nama", "Tegar Rajaiblis")
                .append("nim", "12345678")
                .append("jurusan", "Informatika");

        // Membuat document kedua dengan 4 field (dimensi berbeda)
        Document doc2 = new Document("nama", "Mukhlis")
                .append("nim", "87654321")
                .append("jurusan", "Sistem Informasi")
                .append("angkatan", "2023");

        // Membuat document ketiga dengan 5 field (dimensi berbeda)
        Document doc3 = new Document("nama", "Nadia")
                .append("nim", "11223344")
                .append("jurusan", "Teknik Komputer")
                .append("angkatan", "2024")
                .append("kelas", "4D");

        // Menyisipkan ketiga dokumen ke dalam koleksi
        collection.insertMany(java.util.Arrays.asList(doc1, doc2, doc3));
        System.out.println("[CREATE] 3 data berhasil ditambahkan.\n");

        // ======== READ ========

        // Menampilkan semua dokumen dalam koleksi
        FindIterable<Document> semuaData = collection.find();
        System.out.println("[READ] Seluruh data:");
        for (Document d : semuaData) {
            System.out.println(d.toJson()); // Menampilkan data dalam format JSON
        }

        // ======== UPDATE ========

        // Melakukan update jurusan pada document yang nama = "Tegar Rajaiblis"
        collection.updateOne(Filters.eq("nama", "Tegar Rajaiblis"),
                new Document("$set", new Document("jurusan", "Data Science")));
        System.out.println("\n[UPDATE] Data jurusan Tegar Rajaiblis berhasil diubah menjadi Data Science.");

        // ======== DELETE ========

        // Menghapus document yang nama = "Nadia"
        collection.deleteOne(Filters.eq("nama", "Nadia"));
        System.out.println("\n[DELETE] Data dengan nama Nadia berhasil dihapus.");

        // ======== SEARCHING ========

        // Melakukan pencarian data dengan kata kunci 'Sistem'
        System.out.println("\n[SEARCHING] Pencarian data dengan kata kunci 'Sistem':");
        FindIterable<Document> hasilCari = collection.find(Filters.regex("jurusan", "Sistem"));

        // Menampilkan hasil pencarian
        for (Document hasil : hasilCari) {
            System.out.println(hasil.toJson());
        }

        // Menutup koneksi MongoDB
        mongoClient.close();
    }
}
