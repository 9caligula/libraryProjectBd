package com.ssu.libraryProjectBd.service;

import com.ssu.libraryProjectBd.entity.BooksInDelivery;
import com.ssu.libraryProjectBd.entity.SupplierEntity;
import com.ssu.libraryProjectBd.entity.SupplyEntity;
import com.ssu.libraryProjectBd.entity.view.AdminToolsView;
import com.ssu.libraryProjectBd.repository.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class AdminToolsService {

    AdminToolsViewRepository adminToolsViewRepository;
    BooksInDeliveryRepository booksInDeliveryRepository;
    BooksRepository booksRepository;
    SupplierRepository supplierRepository;
    SupplyRepository supplyRepository;

    public List<AdminToolsView> findAll() {
        return adminToolsViewRepository.findAll();
    }

    public void createView(String nameBook, Date dateOfSupply, String nameSupplier, Integer quantity) {
        if (nameBook != null && dateOfSupply != null && nameSupplier != null && quantity != null) {
           if (!supplierRepository.existByName(nameSupplier).equals(1)) {
               supplierRepository.saveAndFlush(SupplierEntity.makeDefault(nameSupplier));
           }
            SupplyEntity supplyEntity = supplyRepository.saveAndFlush(SupplyEntity.makeDefault(
                    supplierRepository.findByName(nameSupplier), dateOfSupply
            ));
            booksInDeliveryRepository.saveAndFlush(BooksInDelivery.makeDefault(
                    supplyEntity,
                    booksRepository.getBookByName(nameBook),
                    quantity
            ));
        }
    }

    public void deleteById(String nameSupplier, String nameBook, Date dateOfSupply) {
        SupplierEntity supplier = supplierRepository.findByName(nameSupplier);
        SupplyEntity supply = supplyRepository.findByParams(dateOfSupply, supplier.getId());
        booksInDeliveryRepository.deleteByIds(supply.getId(), booksRepository.getBookByName(nameBook).getId());
        supplyRepository.deleteById(supply.getId());
    }

    public AdminToolsView findByID(BigInteger id) {
        return adminToolsViewRepository.findById(id).get();
    }

    public void save(AdminToolsView adminToolsView) {
        Optional<AdminToolsView> oldAdminView = adminToolsViewRepository.findById(adminToolsView.getId());
        SupplierEntity supplier = supplierRepository.findByName(oldAdminView.get().getNameSupplier());
        SupplyEntity supply = supplyRepository.findByParams(oldAdminView.get().getDateOfSupply(), supplier.getId());
        BooksInDelivery book = booksInDeliveryRepository.findByParams(supply.getId(),
                booksRepository.getBookByName(oldAdminView.get().getBookName())
                        .getId());

        //supplier.setName(adminToolsView.getNameSupplier());
        //supplierRepository.saveAndFlush(supplier);

        supply.setSupplierEntity(supplier);
        supply.setDateOfSupply(adminToolsView.getDateOfSupply());
        supplyRepository.saveAndFlush(supply);

        book.setSupplyEntity(supply);
        book.setBooksEntity(booksRepository.getBookByName(adminToolsView.getBookName()));
        book.setQuantity(adminToolsView.getQuantity());
        booksInDeliveryRepository.saveAndFlush(book);
    }
}
