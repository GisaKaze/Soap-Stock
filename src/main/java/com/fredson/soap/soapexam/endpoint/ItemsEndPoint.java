package com.fredson.soap.soapexam.endpoint;

import com.fredson.soap.soapexam.bean.ItemsModel;
import com.fredson.soap.soapexam.repository.ItemRepository;
import fredson.soap.soapexam.items.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;
import java.util.Optional;

@Endpoint
public class ItemsEndPoint {

    @Autowired
    ItemRepository itemRepository;

    @PayloadRoot(namespace = "http://soapexam.soap.fredson/items", localPart = "GetItemRequest")
    @ResponsePayload
    public GetItemResponse findById(@RequestPayload GetItemRequest request) {

        ItemsModel itemsModel =itemRepository.findById(request.getId()).get();
        GetItemResponse itemResponse = mapItemsDetails(itemsModel);

        return itemResponse;
    }

    @PayloadRoot(namespace = "http://soapexam.soap.fredson/items", localPart = "GetAllItemsRequest")
    @ResponsePayload
    public GetAllItemsResponse findAll(@RequestPayload GetAllItemsRequest request) {

        GetAllItemsResponse allItemsResponse = new GetAllItemsResponse();
        List<ItemsModel> itemsModels = itemRepository.findAll();
        for (ItemsModel itemsModel : itemsModels) {
            GetItemResponse itemResponse = mapItemsDetails(itemsModel);
            allItemsResponse.getItems().add(itemResponse.getItems());
        }
        return allItemsResponse;
    }

    @PayloadRoot(namespace = "http://soapexam.soap.fredson/items", localPart = "CreateItemRequest")
    @ResponsePayload
    public CreateItemResponse save(@RequestPayload CreateItemRequest request){
        itemRepository.save(new ItemsModel(request.getItem().getId(),
                request.getItem().getName(),
                request.getItem().getItemCode(),
                request.getItem().getStatus(),
                request.getItem().getPrice(),
                request.getItem().getSupplier()
        ));
        CreateItemResponse itemResponse = new CreateItemResponse();
        itemResponse.setItems(request.getItem());
        itemResponse.setMessage("Created Successfully");

        return itemResponse;
    }



    @PayloadRoot(namespace = "http://soapexam.soap.fredson/items", localPart = "UpdateItemsRequest")
    @ResponsePayload
    public UpdateItemsResponse update(@RequestPayload UpdateItemsRequest request) {
        UpdateItemsResponse itemsResponse = null;
        Optional <ItemsModel> existingItem = this.itemRepository.findById(request.getItems().getId());
        if(existingItem.isEmpty() || existingItem == null) {
            itemsResponse = mapItemDetails(null, "Id not found");
        }
        if(existingItem.isPresent()) {

            ItemsModel itemsModel = existingItem.get();
            itemsModel.setName(request.getItems().getName());
            itemsModel.setItemCode(request.getItems().getItemCode());
            itemsModel.setStatus(request.getItems().getStatus());
            itemsModel.setPrice(request.getItems().getPrice());
            itemsModel.setSupplier(request.getItems().getSupplier());
            itemRepository.save(itemsModel);
            itemsResponse = mapItemDetails(itemsModel, "Updated successfully");

        }
        return itemsResponse;
    }

    @PayloadRoot(namespace = "http://soapexam.soap.fredson/items", localPart = "DeleteItemsRequest")
    @ResponsePayload
    public DeleteItemsResponse save(@RequestPayload DeleteItemsRequest request) {

        itemRepository.deleteById(request.getId());

        DeleteItemsResponse itemsResponse = new DeleteItemsResponse();
        itemsResponse.setMessage("Deleted Successfully");
        return itemsResponse;
    }


    private GetItemResponse mapItemsDetails(ItemsModel itemsModel){
        Items items = mapItem(itemsModel);

        GetItemResponse itemResponse = new GetItemResponse();

        itemResponse.setItems(items);

        return itemResponse;
    }

    private UpdateItemsResponse mapItemDetails(ItemsModel itemsModel, String message){
        Items items = mapItem(itemsModel);
        UpdateItemsResponse itemsResponse = new UpdateItemsResponse();

        itemsResponse.setItems(items);
        itemsResponse.setMessage(message);

        return itemsResponse;
    }

    private Items mapItem(ItemsModel itemsModel){
        Items items = new Items();
        items.setId(itemsModel.getId());
        items.setName(itemsModel.getName());
        items.setItemCode(itemsModel.getItemCode());
        items.setStatus(itemsModel.getStatus());
        items.setPrice(itemsModel.getPrice());
        items.setSupplier(itemsModel.getSupplier());

        return items;
    }
}
