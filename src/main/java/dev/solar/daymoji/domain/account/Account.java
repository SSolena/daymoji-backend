package dev.solar.daymoji.domain.account;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    private Long id;
    private String name;
    private String socialId;
    private boolean policyAgreement;

}
