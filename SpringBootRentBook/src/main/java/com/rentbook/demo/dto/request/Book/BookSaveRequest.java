package com.rentbook.demo.dto.request.Book;

import com.rentbook.demo.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class BookSaveRequest {

    private String title;

    private String author;

    private int publicationYear;

    private double rentalPriceDay;

    private int stock;

   private Category category;

    public BookSaveRequest() {
    }

    public BookSaveRequest(String title, String author, int publicationYear, double rentalPriceDay, int stock, Category category) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.rentalPriceDay = rentalPriceDay;
        this.stock = stock;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public double getRentalPriceDay() {
        return rentalPriceDay;
    }

    public void setRentalPriceDay(double rentalPriceDay) {
        this.rentalPriceDay = rentalPriceDay;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "BookSaveRequest{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publicationYear=" + publicationYear +
                ", rentalPriceDay=" + rentalPriceDay +
                ", stock=" + stock +
                ", category=" + category +
                '}';
    }
}
