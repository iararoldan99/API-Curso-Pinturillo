package ar.com.ada.api.cursos.models.response;

import java.util.ArrayList;
import java.util.List;

/**
 * RegistrationResponse
 */
public class RegistrationResponse {

    public boolean isOk = false;
    public String message = "";
    public Integer userId;

    public List<ErrorResponseItem> errors = new ArrayList<>();

} 