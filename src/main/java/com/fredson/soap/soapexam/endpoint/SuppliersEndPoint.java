package com.fredson.soap.soapexam.endpoint;

import com.fredson.soap.soapexam.bean.ItemsModel;
import com.fredson.soap.soapexam.bean.SuppliersModel;
import com.fredson.soap.soapexam.repository.SupplierRepository;
import fredson.soap.soapexam.items.GetItemRequest;
import fredson.soap.soapexam.items.GetItemResponse;
import fredson.soap.soapexam.items.Items;
import fredson.soap.soapexam.items.UpdateItemsResponse;
import fredson.soap.soapexam.suppliers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

public class SuppliersEndPoint {

    @Autowired
    private SupplierRepository supplierRepository;

    @PayloadRoot(namespace = "http://soapexam.soap.fredson/suppliers", localPart = "GetSupplierRequest")
    @ResponsePayload
    public GetSupplierResponse findById(@RequestPayload GetSupplierRequest request) {

        SuppliersModel suppliersModel = supplierRepository.findById(request.getId()).get();

        GetSupplierResponse supplierResponse = mapSuppliersDetails(suppliersModel);

        return supplierResponse;
    }


    @PayloadRoot(namespace = "http://soapexam.soap.fredson/suppliers", localPart = "CreateSupplierRequest")
    @ResponsePayload
    public CreateSupplierResponse save(@RequestPayload CreateSupplierRequest request) {
        supplierRepository.save(new SuppliersModel(request.getSupplier().getId(),
                request.getSupplier().getName(),
                request.getSupplier().getEmail(),
                request.getSupplier().getMobile()
        ));
        CreateSupplierResponse supplierResponse = new CreateSupplierResponse();
        supplierResponse.setSupplier(request.getSupplier());
        supplierResponse.setMessage("Created Successfully");
        return supplierResponse;
    }



    private GetSupplierResponse mapSuppliersDetails(SuppliersModel suppliersModel){
        Suppliers suppliers = mapSupplier(suppliersModel);

        GetSupplierResponse supplierResponse = new GetSupplierResponse();

        supplierResponse.setSuppliers(suppliers);

        return supplierResponse;
    }

    private UpdateSuppliersResponse mapSupplierDetails(SuppliersModel suppliersModel, String message){
        Suppliers suppliers = mapSupplier(suppliersModel);
        UpdateSuppliersResponse suppliersResponse = new UpdateSuppliersResponse();

        suppliersResponse.setSuppliers(suppliers);
        suppliersResponse.setMessage(message);

        return suppliersResponse;
    }

    private Suppliers mapSupplier(SuppliersModel suppliersModel){
        Suppliers suppliers = new Suppliers();
        suppliers.setId(suppliers.getId());
        suppliers.setName(suppliers.getName());
        suppliers.setEmail(suppliers.getEmail());
        suppliers.setMobile(suppliers.getMobile());

        return suppliers;
    }


}
