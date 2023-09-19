package kr.co.studyhubinu.studyhubserver.user.dto.data;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class UserId {

    @ApiModelProperty(hidden = true)
    private final Long id;

}