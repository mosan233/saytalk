package com.sf.skytalk.dto;

import lombok.Data;

@Data
public class GithubUserDTO {
    private String name;
    private Long id;
    private String bio;
    private String avatarUrl;
}
