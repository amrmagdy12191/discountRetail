package com.xische.storediscount.api.validator;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.xische.storediscount.api.request.BillRequest;
import com.xische.storediscount.model.ItemCategory;


@Component
public class BillRequestValidator implements Validator<BillRequest> {
	
	private static final Set<String> VALID_CATEGORIES = new HashSet<>();

	public BillRequestValidator() {
		for (ItemCategory itemCategory: ItemCategory.values()) {
			VALID_CATEGORIES.add(itemCategory.name());
		}
	}
	
	@Override
	public boolean validate(BillRequest request) {
        if (request == null || request.getItems() == null || request.getItems().isEmpty() ||
            request.getUser() == null || request.getDate() == null || request.getAmount() <= 0) {
            return false;
        }

        // Validate item categories
        return request.getItems().stream()
            .allMatch(requestItem -> 
                requestItem.getItemCategory() != null &&       
                VALID_CATEGORIES.contains(requestItem.getItemCategory().name())
            );
	}

}
