package com.rentbook.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rentbook.demo.busines.abstracts.IRentalService;
import com.rentbook.demo.core.modelMapper.IModelMapperService;
import com.rentbook.demo.dto.request.Book.BookUpdateRequest;
import com.rentbook.demo.dto.request.Rental.RentalSaveRequest;
import com.rentbook.demo.dto.request.Rental.RentalUpdateRequest;
import com.rentbook.demo.dto.response.RentalReturnResponse;
import com.rentbook.demo.entity.Book;
import com.rentbook.demo.entity.Rental;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/rentals")
public class RentalController {

    private final IRentalService iRentalService;
    private final IModelMapperService modelMapper;

    public RentalController(IRentalService iRentalService, IModelMapperService modelMapper) {
        this.iRentalService = iRentalService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Rental saveRental(@RequestBody RentalSaveRequest rentalSaveRequest) {
        return iRentalService.saveRental(rentalSaveRequest);
    }

    @PutMapping("/return/{rentalId}")
    @ResponseStatus(HttpStatus.OK)
    public RentalReturnResponse returnBook(@PathVariable("rentalId") Long rentalId) {
        double totalCost = iRentalService.returnBook(rentalId);  // Ceza tutarını döndürüyoruz
        RentalReturnResponse response = new RentalReturnResponse(totalCost);
        return response;
    }




    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Rental updateRental(@PathVariable("id") Long id, @RequestBody RentalUpdateRequest rentalUpdateRequest) throws JsonProcessingException {
        // Önce kiralamayı bul
        Rental existingRental = this.iRentalService.getRentalById(id);

        // Güncelleme işlemi
        this.modelMapper.forRequest().map(rentalUpdateRequest, existingRental);

        // Güncellenmiş kiralamayı kaydet
        Rental updatedRental = this.iRentalService.updateRental(existingRental);

        // JSON olarak düzgün dönüp dönmediğini görmek için log ekleyelim
        System.out.println(new ObjectMapper().writeValueAsString(updatedRental));

        return updatedRental;
    }




    @GetMapping("/findAll")
    public List<Rental> getAllRentals(){
        return iRentalService.getAllRentals();
    }

    @GetMapping("/{id}")
    public Rental getRentalById(@PathVariable Long id){
        return iRentalService.getRentalById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteRental(@PathVariable Long id) {
        iRentalService.deleteRental(id);
        return "kiralama başarıyla silindi";
    }
}
