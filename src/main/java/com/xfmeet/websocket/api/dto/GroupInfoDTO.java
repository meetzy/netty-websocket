package com.xfmeet.websocket.api.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author meetzy
 */
@Data
public class GroupInfoDTO {
    
    @NotBlank
    private String ownerId;
    
    @NotBlank
    private String ownerName;
    
    private String groupName;
    
    private Short canJoin;
    
    private Integer maxPeople;
    
    private String description;
    
}
