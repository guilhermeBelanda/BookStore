package com.belanda.jpa.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

public record BookRecordDTO(String title,
                           UUID publisherId,
                           Set<UUID> authorIds,
                           String reviewComment) {
}
