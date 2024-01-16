package at.technikum.springrestbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GetLawyerProfilesRequest {
    private String searchTerm;
    private int page;
    private int size;
}
