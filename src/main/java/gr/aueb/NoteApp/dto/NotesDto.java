package gr.aueb.NoteApp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class NotesDto {
    private Long id;
    private String title;
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private UserDto userDto;
}
