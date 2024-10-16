package com.example.finalproject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateExistingSellerRequest {

        private String name;
        private String email;
        private String phone;
        private String address;
}
