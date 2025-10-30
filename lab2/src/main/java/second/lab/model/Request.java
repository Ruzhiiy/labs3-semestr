package second.lab.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    @NotBlank(message = "uid не может быть пустым")
    @Size(max = 32, message = "uid обязательно, длина не более 32 символов")
    private String uid;
    
    @NotBlank(message = "operationUid не может быть пустым")
    @Size(max = 32, message = "operationUid обязательно, длина не более 32 символов")
    private String operationUid;

    private String systemName;
    
    private String systemTime;
    
    private String source;
    
    @NotNull
    @Min(value = 1, message = "comuncationId минимальное значение: 1")
    @Max(value = 100000, message = "communicationId не может быть более: 100 000")
    private int communicationId;

    private int templateId;
    private int productCode;
    private int smsCode;

}
