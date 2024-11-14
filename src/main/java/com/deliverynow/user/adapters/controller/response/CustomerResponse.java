package com.deliverynow.user.adapters.controller.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {
    @Schema(description = "Client name", example = "Mario Word")
    private String name;
    @Schema(description = "Client phone number", example = "1234567890")
    private String phone;
    @Schema(description = "Client email", example = "mario.word@example.com")
    private String email;
    @Schema(description = "Client document", example = "08197588479")
    private String document;
    private AddressResponse address;
}
