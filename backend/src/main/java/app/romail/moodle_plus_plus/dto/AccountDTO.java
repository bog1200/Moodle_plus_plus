package app.romail.moodle_plus_plus.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@lombok.Getter
@lombok.Setter
public class AccountDTO {
	private Long id;
	private String username;
	private Long person_id;
	private Set<String> roles;



}