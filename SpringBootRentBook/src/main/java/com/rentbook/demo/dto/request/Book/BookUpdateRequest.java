package com.rentbook.demo.dto.request.Book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookUpdateRequest {

    private String title;

    private String author;

    private int publicationYear;

    private String category;

    private double rentalPriceDay;

    private int stock;

   private Long category_Id;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public Long getCategory_Id() {
        return category_Id;
    }

    public void setCategory_Id(Long category_Id) {
        this.category_Id = category_Id;
    }

    @Override
    public String toString() {
        return "BookUpdateRequest{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publicationYear=" + publicationYear +
                ", category='" + category + '\'' +
                ", rentalPriceDay=" + rentalPriceDay +
                ", stock=" + stock +
                '}';
    }
}
